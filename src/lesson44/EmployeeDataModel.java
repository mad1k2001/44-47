package lesson44;

import java.util.List;

public class EmployeeDataModel {
    private List<EmployeeData> employees;

    public EmployeeDataModel() {

    }

    public List<EmployeeData> getEmployees() {
        return employees;
    }

    public static class EmployeeData {
        private String name;
        private List<Integer> issuedBooks;

        public EmployeeData(String name, List<Integer> issuedBooks) {
            this.name = name;
            this.issuedBooks = issuedBooks;
        }

        public String getName() {
            return name;
        }

        public List<Integer> getIssuedBooks() {
            return issuedBooks;
        }

        public void addIssuedBook(int bookId) {
            issuedBooks.add(bookId);
        }

        public void returnBook(int bookId) {
            issuedBooks.remove(Integer.valueOf(bookId));
        }
    }
}