package server;

import com.sun.net.httpserver.HttpExchange;
import entities.Book;
import entities.Employee;
import enums.Status;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import services.MainService;
import utils.FileUtil;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BookServer extends BasicServer {
    private final MainService mainService;
    private final static Configuration freemarker = initFreeMarker();

    private final Map<String, Employee> userMap = new HashMap<>();

    public BookServer(String host, int port) throws IOException {
        super(host, port);
        this.mainService = new MainService();
        registerGet("/books", this::booksHandler);
        registerGet("/books/book_info", this::bookDetailsHandler);
        registerGet("/employee", this::employeesHandler);
        registerGet("/employee/profile", this::employeeDetailHandler);
        registerGet("/journal", this::journalHandler);
        registerGet("/register", this::registerPageHandler);
        registerPost("/register", this::registerHandler);
        registerGet("/login", this::loginPageHandler);
        registerPost("/login", this::loginHandler);
        registerPost("/books/issue", this::issueBookHandler);
        registerPost("/books/return", this::returnBookHandler);
    }

    private void returnBookHandler(HttpExchange exchange) {
        String userId = getUserIdFromCookie(exchange);
        if (userId == null) {
            redirect303(exchange, "/login");
            return;
        }

        Employee employee = userMap.get(userId);
        if (employee == null) {
            redirect303(exchange, "/login");
            return;
        }

        Book currentBook = employee.getCurrentBooks();
        if (currentBook == null) {
            renderTemplate(exchange, "return_book_failed.ftlh", null);
            return;
        }

        currentBook.setStatus(Status.FREE);
        currentBook.setEmployeeId(0);
        employee.setPastBooks(currentBook);
        employee.setCurrentBooks(null);

        List<Book> books = FileUtil.readBook();
        FileUtil.writeBook(books);

        renderTemplate(exchange, "return_book_success.ftlh", null);
    }

    private void issueBookHandler(HttpExchange exchange) {
        String userId = getUserIdFromCookie(exchange);
        if (userId == null) {
            redirect303(exchange, "/login");
            return;
        }

        Employee employee = userMap.get(userId);
        if (employee == null) {
            redirect303(exchange, "/login");
            return;
        }

        if (employee.getCurrentBooks() != null) {
            renderTemplate(exchange, "issue_book_failed.ftlh", null);
            return;
        }

        Map<String, String> params = getParamsFromUri(exchange.getRequestURI().getQuery());
        int bookId = Integer.parseInt(params.get("bookId"));

        List<Book> books = FileUtil.readBook();
        Optional<Book> optionalBook = books.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst();

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.getStatus() == Status.FREE) {
                book.setStatus(Status.ISSUED);
                book.setEmployeeId(employee.getId());
                employee.setCurrentBooks(book);
                FileUtil.writeBook(books);

                renderTemplate(exchange, "issue_book_success.ftlh", null);
                return;
            }
        }
        renderTemplate(exchange, "issue_book_failed.ftlh", null);
    }

    private void registerHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String password = parsed.get("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || employeeExists(email)) {
            renderTemplate(exchange, "registration_failed.ftlh", null);
            return;
        }

        String firstName = parsed.get("firstName");
        String lastName = parsed.get("lastName");
        Employee newEmployee = new Employee(firstName, lastName, email, password);

        List<Employee> employees = FileUtil.readEmployee();
        employees.add(newEmployee);
        FileUtil.writeEmployee(employees);
        redirect303(exchange, "/");
    }

    private boolean employeeExists(String email) {
        List<Employee> employees = FileUtil.readEmployee();
        return employees.stream().anyMatch(employee -> employee.getEmail().equals(email));
    }

    private void loginHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String password = parsed.get("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            renderTemplate(exchange, "login_failed.ftlh", null);
            return;
        }

        List<Employee> employees = FileUtil.readEmployee();
        Optional<Employee> authenticatedEmployee = employees.stream()
                .filter(employee -> employee.getEmail().equals(email) && employee.getPassword().equals(password))
                .findFirst();

        if (authenticatedEmployee.isPresent()) {
            String userId = "user-" + authenticatedEmployee.get().getId();
            userMap.put(userId, authenticatedEmployee.get());

            CookieServer cookie = CookieServer.make("user-id", userId);
            cookie.setMaxAge(600);
            cookie.setHttpOnly(true);
            setCookie(exchange, cookie);

            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("employee", authenticatedEmployee.get());
            renderTemplate(exchange, "profile.ftlh", dataModel);
        } else {
            renderTemplate(exchange, "login_failed.ftlh", null);
        }
    }

    private void loginPageHandler(HttpExchange exchange){
        renderTemplate(exchange, "login.ftlh", null);
    }

    private void registerPageHandler(HttpExchange exchange){
        renderTemplate(exchange, "register.ftlh", null);
    }

    private void booksHandler(HttpExchange exchange) {
        renderTemplate(exchange, "books.ftlh", mainService.getBooksDataModel());
    }

    private void bookDetailsHandler(HttpExchange exchange) {
        renderTemplate(exchange, "book_info.ftlh", mainService.getBookInfoDataModel());
    }

    private void employeesHandler(HttpExchange exchange){
        renderTemplate(exchange,"employee.ftlh", mainService.getEmployeeDataModel());
    }

    private void employeeDetailHandler(HttpExchange exchange){
        renderTemplate(exchange, "profile.ftlh", mainService.getEmployeeInfoDataModel());
    }

    private void journalHandler(HttpExchange exchange){
        renderTemplate(exchange, "journal.ftlh", mainService.getJournalDataModel());
    }

    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setDirectoryForTemplateLoading(new File("data/fonts"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            Template temp = freemarker.getTemplate(templateFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
                temp.process(dataModel, writer);
                writer.flush();
                var data = stream.toByteArray();
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private String getUserIdFromCookie(HttpExchange exchange) {
        String cookies = getCookies(exchange);
        Map<String, String> parsedCookies = CookieServer.parse(cookies);
        return parsedCookies.get("user-id");
    }

    private Map<String, String> getParamsFromUri(String query) {
        return Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> arr.length > 1 ? arr[1] : ""
                ));
    }
}
