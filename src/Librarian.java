import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
public class Librarian {
  private ArrayList<User> userList;
  private ArrayList<Book> bookList;
  private Scanner input;
  private User user;
  private Book book;

  public Librarian() {
    this.input = new Scanner(System.in);
    bookList = new ArrayList<Book>();
    userList = new ArrayList<User>();    
    user = new User();
    
  }

  private long idGenerator(){
    long id = 10_000;
    return id + 1;
  }
  public void userRegistration(){
    user = new User();
    System.out.println("Enter your name ");
    user.setUserName(input.nextLine());
    System.out.println("Enter your gmail id ");
    user.setUserGmailId(input.nextLine());
    user.setUserId(idGenerator());
    user.setUserStatus("REGISTERED");
    System.out.println("user " + user.getUserId() + "registered");
  }
  public void viewBookList(){
    for(Book book : bookList){
      System.out.println(book.getBookName() + "; " + book.getBookAuthor() + "; " + book.getBookGenre() + "; " + book.getStatus());

    }
  }

  public void addBook() {
    book = new Book();
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
