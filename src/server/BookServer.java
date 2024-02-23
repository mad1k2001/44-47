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
import java.util.Map;

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
        registerGet("/employee/employee_info", this::employeeDetailHandler);
        registerGet("/journal", this::journalHandler);
        registerGet("/register", this::registerPageHandler);
        registerPost("/register", this::registerHandler);
    }

    private void registerHandler(HttpExchange exchange) {
        String raw = getBody(exchange);
        Map<String,String> parsed = FileUtil.parseUrlEncoded(raw, "&");
        employee = new Employee(parsed.get("firstName"), parsed.get("lastName"), parsed.get("email"), parsed.get("password"));
        redirect303(exchange, "/");
    }

    private void registerPageHandler(HttpExchange exchange){
        if (employee != null){
            renderTemplate(exchange, "register.ftlh", employee);
        }
        Path path = makeFilePath("register.ftlh");
        sendFile(exchange, path, ContentType.TEXT_HTML);
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
        renderTemplate(exchange, "employee_info.ftlh", mainService.getEmployeeInfoDataModel());
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
