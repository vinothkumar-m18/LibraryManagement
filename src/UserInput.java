import java.util.Scanner;

public class UserInput {
  private Librarian librarian;
  private Scanner scanner;
  private User user;

  public UserInput() {
    this.librarian = new Librarian();
    this.user = new User();
    scanner = new Scanner(System.in);

  }

  public void exitMessage(){
    System.out.println("Thanks for visiting.");
    System.out.println("Exited the application");
  }
  public static void main(String[] args) {

    UserInput userInput = new UserInput();
    System.out.println("\nWelcome to the Library Management System");
    System.out.println("Are you an user or librarian");
    System.out.println("Press 1. User, 2. Librarian");
    String input1 = userInput.scanner.nextLine();
    switch (input1) {
      case "1":
        System.out.println("\nEnter 1 to Register, 2 to login");
        String input2 = userInput.scanner.nextLine().toUpperCase();
        switch (input2) {
          case "1":
            System.out.println("Enter your name ");
            String name = userInput.scanner.nextLine();
            System.out.println("Enter your gmail id ");
            String gmail = userInput.scanner.nextLine();
            System.out.println("Enter a strong password ");
            String password = userInput.scanner.nextLine();
            if (userInput.librarian.userRegistration(name, gmail, password)) {
              System.out.println("Registered successfully");
              System.out.println("\nEnter L to Login");
              System.out.println("Press 2 to exit the app");
              if (userInput.scanner.nextLine().equals("2")) {
                userInput.exitMessage();              
                break;
              }
            } else {
              System.out.println("Registration failed");
            }
          
          case "2":
            System.out.println("\nEnter your user id");
            String userId = userInput.scanner.nextLine();
            System.out.println("Enter the password ");
            if (userInput.librarian.userLogin(userId, userInput.scanner.nextLine())) {
              System.out.println("\nLogged in successfully");            
              String input3;
              do {
              System.out.println("\nMENU OPTIONS ");
                System.out.println("1. View all available books ");
                System.out.println("2. Borrow a book ");
                System.out.println("3. Return a book ");
                System.out.println("0. Exit the application");
                System.out.println("\nEnter a menu option");
                input3 = userInput.scanner.nextLine();
                switch (input3) {
                  case "0":
                    userInput.exitMessage();
                    break;
                  case "1":
                    System.out.println();
                    userInput.user.viewAllAvailableBooks();
                    break;
                  case "2":
                    System.out.println("Enter the book name ");
                    userInput.user.borrowBook(userId, userInput.scanner.nextLine());
                    break;
                  case "3":
                    System.out.println("Enter the book name");
                    userInput.user.returnBook(userInput.scanner.nextLine());
                    break;
                  default:
                    System.out.println("Invalid input menu. Enter a valid one");
                    break;
                }
              } while (!input3.equals("0"));
            } else {
              System.out.println("Log in failed");
            }
        }
        break;

    }
  }
}
