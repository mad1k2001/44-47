package entities;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Book currentBooks;
    private Book pastBooks;


    public Employee(int id, String firstName, String lastName, String email, String password, Book currentBooks, Book pastBooks) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.currentBooks = currentBooks;
        this.pastBooks = pastBooks;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Book getCurrentBooks() {
        return currentBooks;
    }

    public void setCurrentBooks(Book currentBooks) {
        this.currentBooks = currentBooks;
    }

    public Book getPastBooks() {
        return pastBooks;
    }

    public void setPastBooks(Book pastBooks) {
        this.pastBooks = pastBooks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
