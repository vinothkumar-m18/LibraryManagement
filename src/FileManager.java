import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a file manager class which is responsible for persistent storage
 * of users and books data.
 * Also manages core file handling functions such as writing users and books
 * data to the disk, loading users and books
 * data from the disk.
 */
public class FileManager {
  // These variables are passed from the Librarian class and not instantiated
  // within this class.
  private ArrayList<Book> bookList;
  private ArrayList<User> userList;
  private String filePathForUsersData = "src/Users.txt";
  private String filePathForBooksData = "src/Books.txt";
  private File booksDataFile;
  private File usersDataFile;

  /**
   * Creates a FileManager instance which initializes the user list and book list.
   * 
   * @param userList the ArrayList containing the users data
   * @param bookList the ArrayList containing the books data
   */
  public FileManager(ArrayList<User> userList, ArrayList<Book> bookList) {
    this.userList = userList;
    this.bookList = bookList;
    try {
      booksDataFile = new File(filePathForBooksData);
    } catch (NullPointerException e) {
      System.err.println("Pathname is null. " + e);
      exitDuringError();
    }
    try {
      usersDataFile = new File(filePathForUsersData);
    } catch (NullPointerException e) {
      System.err.println("Pathname is null" + e);
      exitDuringError();
    }
  }

  private void exitDuringError() {
    System.out.println("Exiting the application");
    System.exit(1);
  }

  /**
   * Writes a user's data to the disk by its book list reference. It appends the
   * new data to the existing data in the file.
   * The users data is split by the pipe(|) character.
   * 
   * @param user the reference to the user object
   * @throws IOException if the writing to the file was interrupted
   */
  public void writeUserDataToDisk(User user) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersDataFile, true))) {
      String userData = user.getUserId() + "| " + user.getUserPassword() + " | " + user.getUserName() + " | "
          + user.getUserGmailId();
      bw.write(userData);
      bw.newLine();
    } catch (IOException e) {
      System.err.println("Error writing to the file. " + e);
      exitDuringError();
    }
  }

  /**
   * Reads the data of the registered users from the disk.
   * 
   * @throws FileNotFoundException if the file containing the users data is not
   *                               found
   * @throws SecurityException     if the access to the file is denied
   * @throws IOException           if the reading operation is interrupted
   */
  public void loadUsersDataFromDisk() {
    try {
      if (!usersDataFile.exists()) {
        usersDataFile.createNewFile();
      }

      try (BufferedReader br = new BufferedReader(new FileReader(usersDataFile))) {
        String userData;
        while ((userData = br.readLine()) != null) {
          String parts[] = userData.split("\\|");
          if (parts.length == 4) {
            User user = new User();
            try{
              System.out.println(parts[0]);
              user.setUserId(UUID.fromString(parts[0]));
            }catch(IllegalArgumentException e){
              System.err.println("Invalid UUID format while reading user ID from disk");
              exitDuringError();
            }
            user.setUserPassword(parts[1].trim());
            user.setUserName(parts[2].trim());
            user.setUserGmailId(parts[3].trim());
            userList.add(user);
          } else {
            System.out.println("Invalid format of data while reading the file containing the users data");
            exitDuringError();
          }
        }
      } catch (FileNotFoundException e) {
        System.err.println("Error locating the file containing the users data");
      }
    } catch (SecurityException e) {
      System.err.println("Error : Access to the file containing the users data is denied");
      exitDuringError();
    } catch (IOException e) {
      System.err.println("Reading operation is interuppted. " + e);
      exitDuringError();
    }

  }

  /**
   * Writes all the information of the books in the library's collection to the
   * disk by overwriting the existing file data.
   * 
   * @param filename the name of the fiel to write the information of the books
   */
  public void writeAllBooksToDisk() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(booksDataFile, false))) {
      String bookDetails;

      for (Book book : bookList) {
        bookDetails = book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | "
            + book.getStockCount();
        bw.write(bookDetails);
        bw.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error writing books to disk");
      exitDuringError();
    }
  }

  /**
   * Reads all the books and their information from the disk and propagate it into
   * the
   * book list
   * 
   * @throws NumberFormatException if the entered string doesn't contains a proper
   *                               integer number
   * @throws FileNotFoundException if the file containing the books information is
   *                               not found
   * @throws IOException           if the reading operation was interrupted
   */
  public void loadBooksFromDisk() {
    try {
      if (!booksDataFile.exists()) {
        booksDataFile.createNewFile();
      }
      try (BufferedReader br = new BufferedReader(new FileReader(filePathForBooksData))) {
        String str;
        while ((str = br.readLine()) != null) {
          String[] parts = str.split("\\|");
          if (parts.length != 4) {
            System.out.println("Invalid format " + str);
            continue;
          }
          Book book = new Book();
          try {
            book.setBookName(parts[0].trim());
            book.setBookAuthor(parts[1].trim());
            book.setBookGenre(parts[2].trim());
            book.setStockCount((Integer.parseInt(parts[3].trim())));
            bookList.add(book);
          } catch (NumberFormatException e) {
            System.err.println("Error converting string to number. " + e);
            exitDuringError();
          }
        }
      } catch (FileNotFoundException e) {
        System.err.println("Error locating the file containing book details. please provide a valid path " + e);
        exitDuringError();
      } catch (IOException e) {
        System.err.println("Reading the books data operation is interrupted. " + e);
        exitDuringError();
      }
    } catch (SecurityException e) {
      System.err.println("Error : Access to the file containing books data is denied");
      exitDuringError();
    } catch (IOException e) {
      System.err.println("Error : Reading the file containing books data is interrupted");
      exitDuringError();
    }
  }
}
