import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
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
    book = new Book();
    readBooksFromDisk();
  }

  private long idGenerator(){
    long id = 10_000;
    return id + 1;
  }
  private void readBooksFromDisk(){
    try(BufferedReader br = new BufferedReader(new FileReader("Books.txt"))){
      String str;
      while((str = br.readLine()) != null){        
        String[] parts = str.split("\\|");
        if(parts.length != 4){
          System.out.println("Invalid format " + str);
          continue;          
        }
        book = new Book();
        try{
          book.setBookName(parts[0].trim());
          book.setBookAuthor(parts[1].trim());
          book.setBookGenre(parts[2].trim());
          book.setNoOfStocks(Integer.parseInt(parts[3].trim()));
          bookList.add(book);
        }catch(NumberFormatException e){
        System.err.println("Error converting string to number. " + e);
        }
      }
    }catch(FileNotFoundException e){
      System.err.println("Error locating the file. please provide a valid path " + e);
    }catch(IOException e){
      System.err.println("Error closing the input stream. " + e);
    }
  }

  public void userRegistration(){    
    user = new User();
    System.out.println("Enter your name ");
    user.setUserName(input.nextLine());
    System.out.println("Enter your gmail id ");
    user.setUserGmailId(input.nextLine());
    user.setUserId(idGenerator());
    user.setUserStatus("REGISTERED");
    System.out.println("Registered Successfully");
    userList.add(user);
  }
  public void viewBookList(){
    for(Book book : bookList){
      System.out.println(book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | " + book.getStatus());

    }
  }
  public void viewUserList(){
    for(User user : userList){
      System.out.println(user.getUserId() + " | " + user.getUserName() + " | " + user.getUserGmailId() + " | " + user.getUserStatus());      
    }
  }  
  public void addBook() {
    System.out.println("Enter the book name ");
    book.setBookName(input.nextLine());
    System.out.println("Enter the author name ");
    book.setBookAuthor(input.nextLine());
    System.out.println("Enter the book genre ");
    book.setBookGenre(input.nextLine());
    try{
      book.setNoOfStocks(Integer.parseInt(input.nextLine()));
    }catch(NumberFormatException e){
      System.err.println("Error converting string to number. " + e);
    }
    if (bookList.add(book)) {
      System.out.println("Book added");
    } else {
      System.out.println("Book not added");
    }
  }

  public void removeBook(String bookName) {
    Iterator<Book> iterator = bookList.iterator();
    while(iterator.hasNext()){
      if(iterator.next().getBookName().equals(bookName)){
        iterator.remove();
        System.out.println("Book removed");
        break;
      }
    }
  }

  

}
