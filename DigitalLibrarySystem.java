import java.util.*;

class Book {
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Category: " + category + ", Status: " +
                (isIssued ? "Issued" : "Available");
    }
}

class Member {
    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Member Name: " + name + ", Email: " + email;
    }
}

public class DigitalLibrarySystem {
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Digital Library System!");

        // Adding some initial data
        initializeLibrary();

        while (true) {
            System.out.println("\nLogin as:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminModule();
                    break;
                case 2:
                    userModule();
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeLibrary() {
        books.add(new Book("The Alchemist", "Paulo Coelho", "Fiction"));
        books.add(new Book("Clean Code", "Robert C. Martin", "Programming"));
        books.add(new Book("Atomic Habits", "James Clear", "Self-Help"));

        members.add(new Member("Alice", "alice@example.com"));
        members.add(new Member("Bob", "bob@example.com"));
    }

    private static void adminModule() {
        System.out.println("\n--- Admin Module ---");
        System.out.println("1. Add Book");
        System.out.println("2. Update Book");
        System.out.println("3. Delete Book");
        System.out.println("4. View All Books");
        System.out.println("5. View All Members");
        System.out.println("6. Logout");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                updateBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                viewBooks();
                break;
            case 5:
                viewMembers();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void userModule() {
        System.out.println("\n--- User Module ---");
        System.out.println("1. View All Books");
        System.out.println("2. Search Book by Title or Category");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Query (Send Email)");
        System.out.println("6. Logout");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewBooks();
                break;
            case 2:
                searchBook();
                break;
            case 3:
                issueBook();
                break;
            case 4:
                returnBook();
                break;
            case 5:
                sendEmail();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book category: ");
        String category = scanner.nextLine();

        books.add(new Book(title, author, category));
        System.out.println("Book added successfully!");
    }

    private static void updateBook() {
        System.out.print("Enter the title of the book to update: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                System.out.print("Enter new category: ");
                String newCategory = scanner.nextLine();

                books.remove(book);
                books.add(new Book(newTitle, newAuthor, newCategory));
                System.out.println("Book updated successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void deleteBook() {
        System.out.print("Enter the title of the book to delete: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void viewMembers() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    private static void searchBook() {
        System.out.print("Enter title or category to search: ");
        String query = scanner.nextLine();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query) || book.getCategory().equalsIgnoreCase(query)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private static void issueBook() {
        System.out.print("Enter the title of the book to issue: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isIssued()) {
                book.issueBook();
                System.out.println("Book issued successfully!");
                return;
            }
        }
        System.out.println("Book not found or already issued.");
    }

    private static void returnBook() {
        System.out.print("Enter the title of the book to return: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isIssued()) {
                book.returnBook();
                System.out.println("Book returned successfully!");
                return;
            }
        }
        System.out.println("Book not found or not issued.");
    }

    private static void sendEmail() {
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.println("Query sent! We will get back to you at " + email + ".");
    }
}
