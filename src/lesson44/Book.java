package lesson44;

public class Book {
    private String name;
    private String author;
    private boolean issued;
    private String issuedTo;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.issued = false;
        this.issuedTo = null;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }
}
