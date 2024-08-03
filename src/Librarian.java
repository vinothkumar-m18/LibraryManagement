import java.util.ArrayList;
import java.util.Scanner;

public class Librarian {
  private ArrayList<User> userList;
  private ArrayList<Book> bookList;
  private Scanner input;
  private User user;
  private Book book;

  public Librarian() {
    this.input = new Scanner(System.in);
  }
  
  public void userRegistration() {

  }

  public void verifyUser() {

  }

  public void addBook() {
    System.out.println("Enter the book name ");
    book.setBookName(input.nextLine());
    System.out.println("Enter the author name ");
    book.setBookAuthor(input.nextLine());
    System.out.println("Enter the book genre ");
    book.setBookGenre(input.nextLine());
    book.setBookStatus(true);
    if (bookList.add(book)) {
      System.out.println("Book added");
    } else {
      System.out.println("Book not added");
    }
  }

  public void removeBook() {
  }

  public void canBorrow() {
  }

}
