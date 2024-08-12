import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a file manager class which is responsible for persistent storage of users and books data.
 * Also manages core file handling functions such as writing users and books data to the disk, loading users and books
 * data from the disk.
 */
public class FileManager {
  // These variables are passed from the Librarian class and not instantiated within this class.
  private ArrayList<Book> bookList; 
  private ArrayList<User> userList;

  /**
   * Creates a FileManager instance which initializes the user list and book list.
   * 
   * @param userList the ArrayList containing the users data
   * @param bookList the ArrayList containing the books data
   */
  public FileManager(ArrayList<User> userList, ArrayList<Book> bookList) {
    this.userList = userList;
    this.bookList = bookList;

  }

  /**
   * Writes a user's data to the disk by its book list reference. It appends the new data to the existing data in the file.
   * The users data is split by the pipe(|) character.
   * @param user
   */
  public void writeUserDataToDisk(User user) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt", true))) {
      String userData = user.getUserId() + " | " + user.getUserPassword() + " | " + user.getUserName() + " | "
          + user.getUserGmailId() + " | " + user.getUserStatus();
      bw.write(userData);
      bw.newLine();

    } catch (IOException e) {
      System.err.println("Error writing users data to file " + e);
    }
  }

  /**
   * Reads the data of the registered users from the disk and throws a FileNotFoundException if the file is not found.
   * 
   * @param filename the name of the file containing the registered users data
   */
  public void loadUsersDataFromDisk(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String userData;
      while ((userData = br.readLine()) != null) {
        String parts[] = userData.split("\\|");
        if (parts.length == 5) {
          User user = new User();
          user.setUserId(parts[0].trim());
          user.setUserPassword(parts[1].trim());
          user.setUserName(parts[2].trim());
          user.setUserGmailId(parts[3].trim());
          user.setUserStatus(parts[4].trim());
          userList.add(user);
        } else {
          System.out.println("Invalid format of data while reading users data from disk");
          break;
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error locating the file containing user details. " + e);
    } catch (IOException e) {
      System.err.println("Error reading the file containing the user details. " + e);
    }
  }

  /**
   * Writes all the information of the books in the library's collection to the
   * disk by overwriting the existing file data.
   * 
   * @param filename the name of the fiel to write the information of the books
   */
  public void writeAllBooksToDisk(String filename) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Books.txt", false))) {
      String bookDetails;

      for (Book book : bookList) {
        bookDetails = book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | "
            + book.getStockCount();
        bw.write(bookDetails);
        bw.newLine();
      }
    } catch (IOException e) {
      System.err.println("Error writing books to disk");
    }
  }

  /**
   * Reads all the books and their information from the disk and propagate it into
   * book list
   * 
   * @param fileName the name of the file to read the information of the books
   * @throws NumberFormatException if the entered string doesn't contains a proper
   *                               integer number
   * @throws FileNotFoundException if the file containing the books information is
   *                               not found
   * @throws IOException           if the reading operation was interrupted
   */
  public void loadBooksFromDisk(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
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
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Error locating the file containing book details. please provide a valid path " + e);
    } catch (IOException e) {
      System.err.println("Reading the books data operation is interrupted. " + e);
    }
  }
}
