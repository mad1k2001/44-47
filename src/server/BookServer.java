package server;

import com.sun.net.httpserver.HttpExchange;
import entities.Employee;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import services.MainService;
import utils.FileUtil;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BookServer extends BasicServer {
    private final MainService mainService;
    private final static Configuration freemarker = initFreeMarker();
    private Employee employee;

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
    }

    private void registerHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String, String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        String email = parsed.get("email");
        if (email == null || email.isEmpty() || employeeExists(email)) {
            renderTemplate(exchange, "registration_failed.ftlh", null);
            return;
        }

        String firstName = parsed.get("firstName");
        String lastName = parsed.get("lastName");
        String password = parsed.get("password");
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
        renderTemplate(exchange, "register.ftlh", employee);
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
}
