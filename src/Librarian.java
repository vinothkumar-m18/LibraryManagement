import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

/**
 * Represents a librarian who manages core functions such as lending books, processing book returns, handling user login and registration, managing the library's collection, and displaying available books.
 */
public class Librarian {
  private ArrayList<User> userList; // To store the users data
  private ArrayList<Book> bookList;// To store the books data
  private FileManager fileManager;
  private UserInput userInput;

  /**
   * Initializes the librarian class by setting up the necessary resources such as the booklist and userlist.
   * It also creates instances of FileManager and UserInput classes.
   * And loads the existing books and users data from the disk
   */
  public Librarian() {
    bookList = new ArrayList<Book>();
    userList = new ArrayList<User>();
    fileManager = new FileManager(userList, bookList);
    userInput = new UserInput(this, fileManager); 
    fileManager.loadBooksFromDisk("Books.txt"); 
    fileManager.loadUsersDataFromDisk("Users.txt"); 
  }

  /**
   * Process the return of a book from the user by incrementing its stock count by one, if its already present in the library's collection.
   * 
   * @param userId   the ID of the user returning the book
   * @param bookName the name of the book being returned
   */
  public void returnBookFromUser(String userId, String bookName) {
    Book book = findBook(bookName);
    if (book != null) {
      book.setStockCount(1);
      System.out.println("Book returned from " + userId + "succesfully");
    } else {
      System.out.println("Book not found");
    }
  }

  /**
   * Searches for a book by its name in the library's collection and print its
   * details if found.
   * 
   * @param bookName the name of the book to search for
   * @return true if the book is found and available; false otherwise
   */
  public boolean searchBook(String bookName) {
    Book book = findBook(bookName);
    if (book != null) {
      printBookDetails(book);
      return true;
    } else {
      System.out.println("Book not available");
      return false;
    }
  }

  /**
   * Finds a book by its name and returns it reference
   * 
   * @param bookName the name of the book to find
   * @return the reference to the book if its present in the library's collection;
   *         null otherwise
   */
  private Book findBook(String bookName) {
    for (Book book : bookList) {
      if (book.getBookName().equals(bookName)) {
        return book;
      }
    }
    return null;
  }

  /**
   * Checks for the availability of the book by its name
   * 
   * @param bookName the name of the book to check for 
   * @return true if the book is currently available; false otherwise
   */
  private boolean isBookAvailable(String bookName) {
    Book book = findBook(bookName);
    if (book == null) {
      System.out.println("null at " + bookName);
      return false;
    }
    else if (book.getStatus().equals("AVAILABLE")) {
      return true;
    }
    return false;
  }

  /**
   * Lends a book to a registered user if the book is available in stock.
   * The stock count of the book is decremented by one upon a successful borrowing.
   * @param userId   the ID of the user borrowing the boook
   * @param bookName the name of the book to borrow
   */
  public void borrowToUser(String userId, String bookName) {
    User user = findUser(userId);
    if (user != null) {
      Book book = findBook(bookName);
      if ((book != null) && isBookAvailable(bookName)) {
        book.setStockCount(-1);
        System.out.println("Book borrowed to " + userId + " successfully");
      } else {
        System.out.println("Book currently unavailable. Try later");
      }
    } else {
      System.out.println("Unregistered users can't borrow a book ");
    }
  }

  /**
   * Verifies the user's ID and password credentials during the login process.
   * Returns true if the credentials matches an existing user; false otherwise
   * 
   * @param userId   the ID of the user logging in
   * @param password the password of the user logging in
   * @return true if the login credentials are correct; false otherwise
   */
  public boolean userLogin(String userId, String password) {
    User user = findUser(userId);
    if (user != null) {
      if (user.getUserPassword().equals(password)) {
        return true;
      } else {
        System.out.println("Enter a valid password");
        return false;
      }
    } else {
      System.out.println("You are not registered yet.");
      return false;
    }
  }

  /**
   * Registers a new user by collecting their details such name, gmail ID, password.
   * The user's data is stored in the userlist and also written to the disk.
   * @param name     the name of the user to register
   * @param gmailId  the gmail ID of the user to register
   * @param password the password of the user to register
   * @return true if the user was successfully registered and data was saved; false otherwise
   */
  public boolean userRegistration(String name, String gmailId, String password) {
    User user = new User();
    user.setUserName(name);
    user.setUserId(idGenerator(name));
    user.setUserGmailId(gmailId);
    user.setUserPassword(password);
    user.setUserStatus("REGISTERED");
    System.out.println("\nYour user details. [note : Take note of your user id and password ]");
    if (userList.add(user)) {
      printUserDetails(user.getUserId());
      fileManager.writeUserDataToDisk(user);
      return true;
    }
    return false;
  }

  /**
   * Displays the information of a book by its reference in a readable format, if its present in the library's collection.
   * 
   * @param book the reference to the book object
   */
  private void printBookDetails(Book book) {
    if (book != null) {
      System.out.println(book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | "
          + book.getStockCount());
    } else {
      System.out.println("Cant print null objects");
    }
  }

  /**
   * Displays the information of a registered user by their user ID in a readable format
   * 
   * @param userId the ID of the user to display information
   */
  private void printUserDetails(String userId) {
    User user = findUser(userId);
    if (user != null) {
      System.out.println(user.getUserId() + " | " + user.getUserPassword() + " | " + user.getUserName() + " | "
          + user.getUserGmailId() + " | " + user.getUserStatus());
    } else {
      System.out.println("User not found");
    }
  }

  /**
   * Displays information of all the registered users in a readable format
   */
  public void viewUserList() {
    for (User user : userList) {
      printUserDetails(user.getUserId());
    }
  }

  /**
   * Adds a new book and its information to the library's collection if its not already present there; otherwise only updates the stock count
   * 
   * @param bookName   the name of the book to add
   * @param authorName the the name of the book's author
   * @param genre      the genre of the book
   * @param stockCount the number of stocks to add
   */
  public void addBook(String bookName, String authorName, String genre, int stockCount) {
    Book book = findBook(bookName);
    if (book == null) {
      Book newBook = new Book();
      newBook.setBookName(bookName);
      newBook.setBookAuthor(authorName);
      newBook.setBookGenre(genre);
      newBook.setStockCount(stockCount);
      if (bookList.add(newBook)) {
        System.out.println("Book added");
      } else {
        System.out.println("Book not added");
      }
    } else {
      book.setStockCount(stockCount);
    }

  }

  /**
   * Removes a book from the library's collection by its name
   * 
   * @param bookName the name of the book to remove
   */
  public void removeBook(String bookName) {
    Iterator<Book> iterator = bookList.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getBookName().equals(bookName)) {
        iterator.remove();
        System.out.println("Book removed");
        break;
      }
    }
  }

  /**
   * Generates a random user Id from a given user's name.Also removes the
   * empty spaces in the user's name.
   * 
   * @param userName the name of the user to generate ID for
   * @return the generated user ID
   */
  private String idGenerator(String userName) {
    return userName.replaceAll(" ", "") + "" + UUID.randomUUID();
  }

  /**
   * Finds a user in the userlist by their ID
   * 
   * @param userId the ID of the user to find
   * @return a reference to the user object if found; null otherwise
   */
  public User findUser(String userId) {
    for (User user : userList) {
      if (user.getUserId().equals(userId)) {
        return user;
      }
    }
    return null;
  }

  /**
   * Displays all the available books in the booklist in a readable format
   */
  public void showAllAvailableBooks() {
    for (Book book : bookList) {
      if (isBookAvailable(book.getBookName())) {
        printBookDetails(book);
      }
    }
  }

  /**
   * Creates a new instance of the Librarian class and also calls the start method of the UserInput class.
   * @param args command line arguments(not used)
   */
  public static void main(String[] args) {
    Librarian librarian = new Librarian();
    librarian.userInput.start();
  }
}
