import java.util.Scanner;
import java.util.UUID;

/**
 * Represents a class responsible for handling user input and interaction with
 * other classes to perform appropriate operations based on the user's input.
 */
public class UserInput {
  private Scanner scanner;
  private User user;
  // Below variables are passed from librarian class, and not instantiated within
  // this class.
  private FileManager fileManager;
  private Librarian librarian;

  /**
   * Creates a instance of the UserInput class and assigning necessary resources
   * such as FileManager and Librarian reference to the instance variables of the
   * UserInput class.
   * 
   * @param librarian   the reference to the Librarian instance
   * @param fileManager the reference to the FileManager instance
   */
  public UserInput(Librarian librarian, FileManager fileManager) {
    this.fileManager = fileManager;
    this.librarian = librarian;
    user = new User();
    scanner = new Scanner(System.in);
  }

  /**
   * Evalauates whether a gmail ID is valid by checking some conditions such as
   * thee presence of spaces, special characters, and ensuring the gmail ID is not
   * empty.
   * 
   * @param gmailID the gmail ID of the user to evaluate
   * @return true if the gmail ID is not empty, doesn't contain spaces or any
   *         special characters other than (.) and (@); false otherwise
   */
  private boolean isValidGmail(String gmailId) {
    char[] array1 = gmailId.toCharArray();
    char[] array2 = "@gmail.com".toCharArray();
    if (array1.length == 0) {
      System.out.println("Gmail Id cannot be empty");
      return false;
    }
    for (int i = 0; i < array1.length; i++) {
      if (array1[i] == 32) {
        System.out.println("Gmail Id cannot contain spaces");
        return false;
      } else if (containsSymbol(gmailId, "GMAIL")) {
        System.out.println("Gmail Id cannot contain symbols other than '.' and '@'");
        return false;
      } else if (array1[i] == 64) {

        for (int j = 0, k = i; (k < array1.length && j < array2.length); j++, k++) {
          if (array1[k] != array2[j]) {
            System.out.println("Invalid gmail address. '@' should be followed by [gmail.com]");
            System.out.println("Also More than single occurance of '@' is not allowed");
            return false;
          }
        }
      }
    }

    return true;

  }

  /**
   * Evaluates a string to determine if it contains a special character based on
   * its meta data type.
   * (e.g., username, password, gmail).
   * 
   * @param string   the string to evaluate
   * @param metaData the meta data about the string type(e.g., "USERNAME",
   *                 "PASSWORD", "GMAIL")
   * @return true if the string contains any special character by its metadata;
   *         false otherwise
   */
  private boolean containsSymbol(String string, String metaData) {
    char[] array = string.toCharArray();
    int dotSymbolCount = 0;
    for (int i = 0; i < array.length; i++) {
      switch (metaData) {
        case "USERNAME":

          if ((array[i] >= 0 && array[i] <= 31) || (array[i] >= 33 && array[i] <= 47)
              || (array[i] >= 58 && array[i] <= 64)
              || (array[i] >= 91 && array[i] <= 96) || (array[i] >= 123 && array[i] <= 126)) {
            if (array[i] == 46) {
              dotSymbolCount++;
              if (dotSymbolCount > 1) {
                System.out.println("More than a single occurance of dot(.) is not allowed");
                return true;
              }
            } else if (array[i] != 46) {
              return true;
            }
          }
          break;
        case "PASSWORD":
          if ((array[i] >= 0 && array[i] <= 47) || (array[i] >= 58 && array[i] <= 64)
              || (array[i] >= 91 && array[i] <= 96) || (array[i] >= 123 && array[i] <= 126)) {
            return true;
          }
          break;
        case "GMAIL":
          if ((array[i] >= 0 && array[i] <= 31) || (array[i] >= 33 && (array[i] <= 45) || array[i] == 47)
              || (array[i] >= 58 && array[i] <= 63)
              || (array[i] >= 91 && array[i] <= 96) || (array[i] >= 123 && array[i] <= 126)) {
            return true;
          }
          break;

        default:
          System.out.println("\nInvalid metadata");
          break;
      }

    }
    return false;
  }

  /**
   * Evaluates a password based on some conditions, such as containing a uppercase
   * letter, a lowercase letter, a special character, and a digit.
   * It also ensures that the password contains atleast 8 characters.
   * 
   * @param password the password to evaluate
   * @return true if the above mentioned conditions are fulfilled; otherwise false
   */
  private boolean isValidPassword(String password) {
    if (password.length() < 8) {
      System.out.println("\nPassword must contain atleast 8 characters");
      return false;
    }
    char[] array = password.toCharArray();
    boolean hasSymbol = false;
    boolean hasUpperCase = false;
    boolean hasLowerCase = false;
    boolean hasDigit = false;
    for (int i = 0; i < array.length; i++) {
      if (array[i] >= 65 && array[i] <= 90) {
        hasUpperCase = true;
      } else if (array[i] >= 97 && array[i] <= 122) {
        hasLowerCase = true;
      } else if (array[i] >= 48 && array[i] <= 57) {
        hasDigit = true;
      } else if (containsSymbol(password, "PASSWORD")) {
        hasSymbol = true;
      }
    }
    if (!hasLowerCase) {
      System.out.println("\nPassword must contain a lowercase letter");
      return false;
    } else if (!hasUpperCase) {
      System.out.println("\nPassword must contain a UPPERCASE letter");
      return false;
    } else if (!hasDigit) {
      System.out.println("\nPassword must contain a digit");
      return false;
    } else if (!hasSymbol) {
      System.out.println("\nPassword must contain a special character");
      return false;
    }
    return true;
  }

  /**
   * Validates whether a stock count is valid by ensuring that its neither zero,
   * negative, nor empty.
   * 
   * @param count the count of the stocks
   * @return true if the above mentioned conditions are satisfied; false otherwise
   */
  private boolean isValidStockCount(int count) {
    if (count == 0) {
      System.out.println("\nStock count cannot be zero");
      return false;
    } else if (count < 0) {
      System.out.println("\nStock count cannot be negative");
      return false;
    } else if (count > 0) {
      return true;
    } else {
      System.out.println("\nStock count cannot be empty");
      return false;
    }

  }

  /**
   * Evaluates a username based on conditions such as ensuring it doesn't contain
   * digits or special characters other than (.) and space.
   * And also it can't be empty.
   * 
   * @param userName the name of the user to evaluate
   * @return true if the above mentioned conditions are met; false otherwise
   */
  private boolean isValidUserName(String userName) {
    if (userName.length() == 0) {
      System.out.println("\nUser name cannot be empty");
      return false;
    }
    char[] array = userName.toCharArray();
    for (int i = 0; i < array.length; i++) {
      if (containsSymbol(userName, "USERNAME")) {
        System.out.println("\nSymbols other than (.) dot are not allowed in usernames");
        return false;
      } else if (array[i] >= 48 && array[i] <= 57) {
        System.out.println("\nUsername connot contains digits");
        return false;
      }
    }
    return true;
  }

  /**
   * Writes the booklist to the disk and prints some exit messages.
   */
  private void exitMessage() {
    fileManager.writeAllBooksToDisk();
    System.out.println("Thanks for visiting.");
    System.out.println("Exited the application");
    System.exit(0);
  }

  /**
   * Handles and proces the user inputs
   */
  public void start() {

    System.out.println("\nWelcome to the Library Management System");
    System.out.println("\nMain menu options");
    System.out.println("Are you an user or librarian");
    System.out.println("Enter 'U' for user");
    System.out.println("Enter 'L' for librarian");
    System.out.println("Enter 'E' to exit the application");
    String input1 = scanner.nextLine().toUpperCase();
    switch (input1) {
      case "E":
        exitMessage();
        break;
      case "U":
        String input2 = null;
        do {
          System.out.println("\nEnter 'R' to register, 'LL' to login");
          input2 = scanner.nextLine().toUpperCase();
          switch (input2) {
            case "R":
              String userName, gmailId, password;
              do {
                System.out.println("\nEnter your userName");
                userName = scanner.nextLine();
              } while (!isValidUserName(userName));
              do {
                System.out.println("\nEnter your gmailId");
                gmailId = scanner.nextLine().toLowerCase();
              } while (!isValidGmail(gmailId));
              if (!gmailId.contains("@gmail.com")) {
                gmailId = gmailId + "@gmail.com";
              }
              do {
                System.out.println("\nEnter a strong password with atleast 8 characters");
                password = scanner.nextLine();
              } while (!isValidPassword(password));
              if (librarian.userRegistration(userName, gmailId, password)) {
                System.out.println("\nRegistered successfully");
                System.out.println("\nPress any key other than 'E' to Login");
                System.out.println("Press E to exit the app");
                if (scanner.nextLine().toUpperCase().equals("E")) {
                  exitMessage();
                }
              } else {
                System.out.println("\nRegistration failed");
              }
              
            case "LL":
              UUID userId = null;
              String userPassword;
              do {
                System.out.println("\nEnter your user id");
                String input = scanner.nextLine();
                try {
                  userId = UUID.fromString(input);
                } catch (IllegalArgumentException e) {
                  System.err.println("Invalid UUID format. " + e);
                }
              } while (userId == null);

              do {
                System.out.println("Enter the password ");
                userPassword = scanner.nextLine();
              } while (userPassword.isEmpty());
              if (librarian.userLogin(userId, userPassword)) {
                System.out.println("\nLogged in successfully");
                String input3;
                do {
                  System.out.println("\nMENU OPTIONS ");
                  System.out.println("1. View all available books ");
                  System.out.println("2. Borrow a book ");
                  System.out.println("3. Return a book ");
                  System.out.println("0. Exit the application");
                  System.out.println("\nEnter a menu option");
                  input3 = scanner.nextLine();
                  switch (input3) {
                    case "0":
                      exitMessage();
                      break;
                    case "1":                      
                      user.viewAllAvailableBooks(librarian);
                      break;
                    case "2":
                      System.out.println("Enter the book name ");
                      user.borrowBook(librarian, userId, scanner.nextLine());
                      break;
                    case "3":
                      System.out.println("Enter the book name");
                      user.returnBook(librarian, scanner.nextLine());
                      break;
                    default:
                      System.out.println("Invalid input menu. Enter a valid one");
                      break;
                  }
                } while (!input3.equals("0"));
              } else {
                System.out.println("Log in failed");
              }
              break;
            default:
              System.out.println("Enter a valid menu between 'R' && 'LL'");
          }
        } while (!input2.equals("R") && !input2.equals("LL"));
        break;
      case "L":
        String input;
        do {
          System.out.println("\nMENU OPTIONS ");
          System.out.println("1. Add a new book");
          System.out.println("2. Remove a existing book");
          System.out.println("3. View all available books");
          System.out.println("4. Search a book");
          System.out.println("0. Exit the app");
          System.out.println("Enter a menu option ");
          input = scanner.nextLine();
          switch (input) {
            case "0":
              exitMessage();
              break;
            case "1":
              String name, author, genre;
              int stockCount;
              do {
                System.out.println("\nEnter the book name ");
                name = scanner.nextLine();
                if (name.isEmpty()) {
                  System.out.println("Book name cannot be empty");
                }
              } while (name.isEmpty());
              do {
                System.out.println("Enter the author name ");
                author = scanner.nextLine();
                if (author.isEmpty()) {
                  System.out.println("Author name cannot be empty");
                }
              } while (author.isEmpty());
              do {
                System.out.println("Enter the book genre ");
                genre = scanner.nextLine();
                if (genre.isEmpty())
                  System.out.println("Book genre cannot be empty");
              } while (genre.isEmpty());
              do {
                System.out.println("Enter the stock count ");
                stockCount = Integer.parseInt(scanner.nextLine());
              } while (!isValidStockCount(stockCount));
              librarian.addBook(name, author, genre, stockCount);
              break;
            case "2":
              String bookName;
              do {
                System.out.println("\nEnter the book name");
                bookName = scanner.nextLine();
                if (bookName.isEmpty())
                  System.out.println("Book name cannot be empty");
              } while (bookName.isEmpty());
              librarian.removeBook(bookName);
              break;

            case "3":
              librarian.showAllAvailableBooks();
              break;
            case "4":
              String bookName1;
              do {
                System.out.println("Enter the book name");
                bookName1 = scanner.nextLine();
                if (bookName1.isEmpty())
                  System.out.println("\nBook name cannot be empty");
              } while (bookName1.isEmpty());
              librarian.searchBook(bookName1);
              break;
            default:
              System.out.println("Enter a valid menu");
              break;
          }
        } while (!input.equals("0"));
        break;

      default:
        System.out.println("Enter a valid menu option ");
        start();
        break;
    }
  }
}
