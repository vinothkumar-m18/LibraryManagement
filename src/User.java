import java.util.UUID;
/**
 * Represents a user in the system who has a unique Name, User ID, Gmail ID,
 * Status, and a Password.
 * It provides methods to set and get the attributes of the user. Also provides
 * functionalities like borrowing a book, returning a book, and viewing all the
 * available books.
 * 
 */
public class User {
  private String userName; // To Store the user's name
  private UUID userId; // To store the user ID
  private String userGmailId; // The store the gmail ID
  private String userStatus; // To store the status of the user
  private String userPassword; // To store the user password

  /**
   * Sets the user's name
   * 
   * @param userName the username to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Sets the user's ID
   * 
   * @param userId the user ID to set
   */
  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  /**
   * Sets the user's gmail ID
   * 
   * @param userGmailId the gmail ID to set
   */
  public void setUserGmailId(String userGmailId) {
    this.userGmailId = userGmailId;
  }

  /**
   * Sets the user's status
   * 
   * @param userStatus the user's status to set
   */
  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  /**
   * Sets the user's password
   * 
   * @param password the user's password to set
   */
  public void setUserPassword(String password) {
    this.userPassword = password;
  }

  /**
   * Gets the user's name
   * 
   * @return the name of the user
   */
  public String getUserName() {
    return this.userName;
  }

  /**
   * Gets the user's ID
   * 
   * @return the ID of the user
   */
  public UUID getUserId() {
    return this.userId;
  }

  /**
   * Gets the user's gmail ID
   * 
   * @return the gmail ID of the user
   */
  public String getUserGmailId() {
    return this.userGmailId;
  }

  /**
   * Gets the user's status
   * 
   * @return the status of the user
   */
  public String getUserStatus() {
    return this.userStatus;
  }

  /**
   * Gets the user's password
   * 
   * @return the password of the user
   */
  public String getUserPassword() {
    return this.userPassword;
  }

  /**
   * Lends a book from the librarian.
   * 
   * @param librarian the librarian handling the transaction
   * @param userId    the ID of the user borrowing the book
   * @param bookName  the name of the book to borrow
   */
  public void borrowBook(Librarian librarian, UUID userId, String bookName) {
    librarian.borrowToUser(userId, bookName);
  }

  /**
   * Returns a book to the librarian from the user
   * 
   * @param librarian the librarian handling the transaction
   * @param bookName  the name of the book to return
   */
  public void returnBook(Librarian librarian, String bookName) {
    librarian.returnBookFromUser(userId, bookName);
  }

  /**
   * Displays a list of all available books
   * 
   * @param librarian the librarian showing the list of available books
   */
  public void viewAllAvailableBooks(Librarian librarian) {
    librarian.showAllAvailableBooks();
  }
}
