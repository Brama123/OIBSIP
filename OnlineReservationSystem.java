import java.util.*;

class OnlineReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Reservation> reservations = new HashMap<>();
    static String currentUser = "";

    public static void main(String[] args) {
        login();
    }

    // Login Functionality
    static void login() {
        System.out.println("Welcome to Online Reservation System");
        System.out.print("Enter Login ID: ");
        String loginId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Simple authentication
        if (loginId.equals("adminuser") && password.equals("adminuser123")) {
            currentUser = loginId;
            System.out.println("\nLogin Successful!");
            mainMenu();
        } else {
            System.out.println("\nInvalid credentials.please Try again.By giving correct details\n");
            login();
        }
    }

    // Main Menu Functionality
    static void mainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Reservation system");
        System.out.println("2. Cancellation system");
        System.out.println("3. Exit");
        System.out.print("Choose an option to proceed: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1 -> reservationForm();
            case 2 -> cancellationForm();
            case 3 -> exitSystem();
            default -> {
                System.out.println("Invalid option.please Try again.");
                mainMenu();
            }
        }
    }

    // Reservation Form
    static void reservationForm() {
        System.out.println("\n--- Reservation Form ---");
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter Class Type (Sleeper/AC): ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("From (Place): ");
        String from = scanner.nextLine();
        System.out.print("To (Destination): ");
        String to = scanner.nextLine();

        // Generate a unique PNR number
        String pnr = UUID.randomUUID().toString().substring(0, 8);
        reservations.put(pnr, new Reservation(name, trainNumber, classType, dateOfJourney, from, to));
        System.out.println("\nReservation Successful! Your PNR Number is: " + pnr);
        mainMenu();
    }

    // Cancellation Form
    static void cancellationForm() {
        System.out.println("\n--- Cancellation Form ---");
        System.out.print("Enter PNR Number: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            System.out.println("\nReservation Details:");
            Reservation reservation = reservations.get(pnr);
            System.out.println(reservation);
            System.out.print("\nDo you want to confirm cancellation? (Yes/No): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("Yes")) {
                reservations.remove(pnr);
                System.out.println("Ticket cancelled successfully!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("PNR not found.");
        }
        mainMenu();
    }

    // Exit System
    static void exitSystem() {
        System.out.println("\nThank you for using the Online Reservation System. Goodbye!");
        System.exit(0);
    }
}

// Reservation Class
class Reservation {
    private final String passengerName;
    private final String trainNumber;
    private final String classType;
    private final String dateOfJourney;
    private final String from;
    private final String to;

    public Reservation(String passengerName, String trainNumber, String classType, String dateOfJourney, String from, String to) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Passenger Name: " + passengerName + "\n" +
                "Train Number: " + trainNumber + "\n" +
                "Class Type: " + classType + "\n" +
                "Date of Journey: " + dateOfJourney + "\n" +
                "From: " + from + "\n" +
                "To: " + to;
    }
}
