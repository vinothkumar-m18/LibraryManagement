import java.util.ArrayList;
public class Librarian {
  private ArrayList<User> userList;
  private ArrayList<Book> bookList;
  private User user;
  private Book book;
  public Librarian(){
    this.user = new User();
    this.book = new Book();
  }
  private String librarianName;
  private long librarianId;
  private String librarianGmailId;

  public void userRegistration(){

  }
  public void verifyUser(){

  }
  public void addBook(){
    book.setBookName("The Science Of Self-Discipline");
    book.setBookAuthor("Peter Hollins");
    book.setBookGenre("Self Development");
    book.setBookStatus(true);
    bookList.add(book);
  }
  public void removeBook(){}
  public void canBorrow(){}

}
