package server;

import com.sun.net.httpserver.HttpExchange;
import dto.BookInfoDataModel;
import dto.BooksDataModel;
import dto.EmployeeDataModel;
import dto.EmployeeInfoDataModel;
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
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BookServer extends BasicServer {
    private MainService mainService;
    private final static Configuration freemarker = initFreeMarker();

    private final Map<String, Employee> userMap = new HashMap<>();

    public BookServer(String host, int port) throws IOException {
        super(host, port);
        this.mainService = new MainService();
        registerGet("/books", this::booksHandler);
        registerGet("/books/book_info", this::bookDetailsHandler);
        registerGet("/employee", this::employeesHandler);
        registerGet("/profile", this::employeeDetailHandler);
        registerGet("/register", this::registerPageHandler);
        registerPost("/register", this::registerHandler);
        registerGet("/login", this::loginPageHandler);
        registerPost("/login", this::loginHandler);
        registerPost("/books/issue", this::issueBookHandler);
        registerPost("/books/return", this::returnBookHandler);
    }

    private void returnBookHandler(HttpExchange exchange) {
        Employee user = getUserFromCookie(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        String raw = getBody(exchange);
        Map<String, String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        String stringBookId = parsed.get("bookId");
        int bookId;
        try {
            bookId = Integer.parseInt(stringBookId);
        } catch (NumberFormatException e) {
            renderTemplate(exchange, "return_book_failed.ftlh", null);
            return;
        }

        Optional<Book> maybeBook = mainService.getBookById(bookId);
        if (maybeBook.isEmpty()) {
            respond404(exchange);
            return;
        }

        Book book = maybeBook.get();
        if (!mainService.returnBookFromEmployee(user, book)) {
            renderTemplate(exchange, "return_book_failed.ftlh", null);
            return;
        }

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("employee", user);
        renderTemplate(exchange, "profile.ftlh", dataModel);
    }

    private void issueBookHandler(HttpExchange exchange) {
        Employee user = getUserFromCookie(exchange);
        if (user == null) {
            redirect303(exchange, "/login");
            return;
        }

        if (user.getCurrentBooks().size() >= 2) {
            renderTemplate(exchange, "issue_book_failed.ftlh", null);
            return;
        }

        String raw = getBody(exchange);
        Map<String, String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        String stringBookId = parsed.get("bookId");
        int bookId;
        try {
            bookId = Integer.parseInt(stringBookId);
        } catch (NumberFormatException e) {
            renderTemplate(exchange, "issue_book_failed.ftlh", null);
            return;
        }

        Optional<Book> maybeBook = mainService.getBookById(bookId);
        if (maybeBook.isEmpty()) {
            respond404(exchange);
            return;
        }

        Book book = maybeBook.get();
        if (!mainService.putBookToEmployee(user, book)) {
            renderTemplate(exchange, "issue_book_failed.ftlh", null);
            return;
        }
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("employee", user);
        renderTemplate(exchange, "profile.ftlh", dataModel);
    }

    private void registerHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        String password = parsed.get("password");

        boolean isExist = mainService.getEmployees().stream().anyMatch(employee -> employee.getEmail().equals(email));

        if (email == null || email.isEmpty() || password == null || password.isEmpty() || isExist) {
            renderTemplate(exchange, "registration_failed.ftlh", null);
            return;
        }

        String firstName = parsed.get("firstName");
        String lastName = parsed.get("lastName");

        Employee newEmployee = new Employee(firstName, lastName, email, password);
        mainService.registerUser(newEmployee);
        redirect303(exchange, "/");
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

        Optional<Employee> authenticatedEmployee = mainService.getEmployees().stream()
                .filter(employee -> employee.getEmail().equals(email) && employee.getPassword().equals(password))
                .findFirst();

        if (authenticatedEmployee.isPresent()) {
            String userId = "" + authenticatedEmployee.get().getId();
            userMap.put(userId, authenticatedEmployee.get());

            CookieServer cookie = CookieServer.make("user", userId);
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

    private Employee getUserFromCookie(HttpExchange exchange) {
        String cookieString = getCookies(exchange);
        Map<String, String> cookies = CookieServer.parse(cookieString);
        String sessionId = cookies.get("user");

        return userMap.get(sessionId);
    }

    private void loginPageHandler(HttpExchange exchange) {
        renderTemplate(exchange, "login.ftlh", null);
    }

    private void registerPageHandler(HttpExchange exchange) {
        renderTemplate(exchange, "register.ftlh", null);
    }

    private void booksHandler(HttpExchange exchange) {
        var model = new BooksDataModel(FileUtil.readBook());
        renderTemplate(exchange, "books.ftlh", model);
    }

    private void employeesHandler(HttpExchange exchange) {
        var model = new EmployeeDataModel(FileUtil.readEmployee());
        renderTemplate(exchange, "employee.ftlh", model);
    }

    private void bookDetailsHandler(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        if (query != null && query.startsWith("id=")) {
            try {
                int bookId = Integer.parseInt(query.substring(3));
                BookInfoDataModel dataModel = mainService.getBookInfoDataModel(bookId);
                if (dataModel != null) {
                    renderTemplate(exchange, "book_info.ftlh", dataModel);
                    return;
                }
            } catch (NumberFormatException ignored) {
            }
        }
        respond404(exchange);
    }

    private void employeeDetailHandler(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        if (query != null && query.startsWith("id=")) {
            try {
                int employeeId = Integer.parseInt(query.substring(3));
                EmployeeInfoDataModel dataModel = mainService.getEmployeeInfoDataModel(employeeId);
                if (dataModel != null) {
                    renderTemplate(exchange, "profile.ftlh", dataModel);
                    return;
                }
            } catch (NumberFormatException e) {

            }
        }
        respond404(exchange);
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
}
