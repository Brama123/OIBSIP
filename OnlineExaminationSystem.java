import java.util.*;

class User {
    private String username;
    private String password;
    private String profileInfo;

    public User(String username, String password, String profileInfo) {
        this.username = username;
        this.password = password;
        this.profileInfo = profileInfo;
    }

    public boolean validate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void updateProfile(String newInfo) {
        this.profileInfo = newInfo;
        System.out.println("Profile updated successfully!");
    }

    public void updatePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Old password incorrect. Password update failed.");
        }
    }

    public String getProfileInfo() {
        return profileInfo;
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswer;

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void display() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User("student1", "password123", "Name: John Doe, Age: 21");

        System.out.println("Welcome to the Online Examination System!");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (user.validate(username, password)) {
            System.out.println("Login successful!\n");
            boolean sessionActive = true;

            while (sessionActive) {
                System.out.println("\nMenu:");
                System.out.println("1. Update Profile");
                System.out.println("2. Update Password");
                System.out.println("3. Take Exam");
                System.out.println("4. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: // Update Profile
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new profile info: ");
                        String newInfo = scanner.nextLine();
                        user.updateProfile(newInfo);
                        break;

                    case 2: // Update Password
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter old password: ");
                        String oldPassword = scanner.nextLine();
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        user.updatePassword(oldPassword, newPassword);
                        break;

                    case 3: // Take Exam
                        System.out.println("Starting the exam. You have 60 seconds to complete it.");
                        long startTime = System.currentTimeMillis();
                        long duration = 60 * 1000; // 60 seconds

                        Question[] questions = {
                            new Question("What is the capital of France?", new String[]{"Paris", "Berlin", "Rome", "Madrid"}, 1),
                            new Question("Which language is used for Android app development?", new String[]{"C", "Java", "Python", "Ruby"}, 2),
                            new Question("What is the largest ocean on Earth?", new String[]{"Atlantic", "Indian", "Pacific", "Arctic"}, 3)
                        };

                        int score = 0;
                        boolean timeUp = false;

                        for (int i = 0; i < questions.length && !timeUp; i++) {
                            long elapsedTime = System.currentTimeMillis() - startTime;
                            if (elapsedTime > duration) {
                                System.out.println("\nTime's up! Submitting your answers...");
                                timeUp = true;
                                break;
                            }
                            System.out.println("Question " + (i + 1) + ":");
                            questions[i].display();
                            System.out.print("Enter your answer (1-4): ");
                            int answer = scanner.nextInt();
                            if (questions[i].checkAnswer(answer)) {
                                score++;
                            }
                        }

                        System.out.println("Exam Completed!");
                        System.out.println("Your Score: " + score + " out of " + questions.length);
                        break;

                    case 4: // Logout
                        System.out.println("Logging out...");
                        sessionActive = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password. Access Denied.");
        }

        System.out.println("Thank you for using the Online Examination System. Goodbye!");
        scanner.close();
    }
}
