import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.lang.Math;
public class Librarian {
  private ArrayList<User> userList;
  private ArrayList<Book> bookList;
  private Scanner input;
  private User user;
  private Book book;
  private String LibrarianName;
  private String LibrarianId;

  public Librarian() {
    this.input = new Scanner(System.in);
    bookList = new ArrayList<Book>();
    userList = new ArrayList<User>();    
    user = new User();
    book = new Book();
    readBooksFromDisk();
  }

  
  public void returnBookFromUser(String bookName){
    Book book = findBook(bookName);
    book.setStockCount("RETURN");
    System.out.println("Book returned successfully");
  }
  private Book findBook(String bookName){
    for(Book book : bookList){
      if(!book.getBookName().equals(bookName)){
        System.out.println("Book not found");
      }else{      
        return book;
      }
    }
    return null;
  }

  private boolean isBookAvailable(String bookName){
    Book book = findBook(bookName);
    if(book.getStatus().equals("AVAILABLE")){
      return true;
    }
    return false;
  }
  public void borrowToUser(User user, String bookName){
    if(findUser(user.getUserId()) != null){
      if(isBookAvailable(bookName)){
        Book book = findBook(bookName);
        book.setStockCount("BORROW");
        System.out.println("Book borrowed successfully");
      }else{
        System.out.println("Book currently unavailable. Try later");
      }
    }else{
      System.out.println("Unregistered users can't borrow a book ");
    }
  }
  public boolean userLogin(String userId, String password){
    User user = findUser(userId);
    if(user != null){
      if(user.getuserPassword().equals(password)){
        return true;
      }else{
        System.out.println("Enter a valid password");
        return false;
      }
    }else{
      System.out.println("You are not registered yet.");
      return false;
    }
  }
  public boolean userRegistration(String name, String gmailId, String password){    
    user = new User();
    user.setUserName(name);
    user.setUserId(idGenerator(name));
    user.setUserGmailId(gmailId);
    user.setUserStatus("REGISTERED");
    if(userList.add(user)){
      return true;
    }
    return false;
  }
  private void printBookDetails(Book book){
    System.out.println(book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | " + book.getStatus());
  }
  public void viewBookList(){
    for(Book book : bookList){
      printBookDetails(book);
    }
  }
  public void viewUserList(){
    for(User user : userList){
      System.out.println(user.getUserId() + " | " + user.getuserPassword() + " | " + user.getUserName() + " | " + user.getUserGmailId() + " | " + user.getUserStatus());      
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
      book.setStockCount(Integer.parseInt(input.nextLine()));
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
          book.setStockCount((Integer.parseInt(parts[3].trim())));
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
  private String idGenerator(String userName){
    return userName + "" + (int)(Math.random() * 100);
  }
  private User findUser(String userId){
    for(User user : userList){
      if(user.getUserId().equals(userId)){
        return user;
      }
    }
    return null;
  }
  public void showAllAvailableBooks(){
    for(Book book : bookList){
      if(isBookAvailable(book.getBookName())){
        printBookDetails(book);
      }
    }
  }

}
