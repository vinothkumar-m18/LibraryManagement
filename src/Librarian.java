import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
  private Book book;
  private String LibrarianName;
  private String LibrarianId;

  public Librarian() {
    this.input = new Scanner(System.in);
    bookList = new ArrayList<Book>();
    userList = new ArrayList<User>();    
    book = new Book();
    loadBooksFromDisk();
    loadUsersDataFromDisk();
  }

  private void writeUserDataToDisk(User user){
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("Users.txt", true))){    
        String userData = user.getUserId() + " | "  + user.getuserPassword() + " | " + user.getUserName() + " | " + user.getUserGmailId() + " | " + user.getUserStatus();
        bw.write(userData);
        bw.newLine();
      
    }catch(IOException e){
      System.err.println("Error writing to file " + e);
    }
  }
  private void loadUsersDataFromDisk(){
    try(BufferedReader br = new BufferedReader(new FileReader("Users.txt"))){    
      String userData;
      while((userData = br.readLine()) != null){      
        String parts[] = userData.split("\\|");
        if(parts.length == 5){
          User user = new User();
          user.setUserId(parts[0].trim());
          user.setUserPassword(parts[1].trim());
          user.setUserName(parts[2].trim());
          user.setUserGmailId(parts[3].trim());
          user.setUserStatus(parts[4].trim());
          userList.add(user);
        }else{
          System.out.println("Invalid format of data while reading users data from disk");
          break;          
        }
      }
    }catch(FileNotFoundException e){
      System.err.println("Error locating the file. " + e);
    }catch(IOException e){
      System.err.println("Error reading the file. " + e);
    }
  }
  public void returnBookFromUser(String bookName){
    Book book = findBook(bookName);
    book.setStockCount(book, "RETURN");
    System.out.println("Book returned successfully");
  }
  private Book findBook(String bookName){
    for(Book book : bookList){
      if(book.getBookName().equals(bookName)){
        return book;
      }
    }
    return null;
  }
  private boolean isBookAvailable(String bookName){
    Book book = findBook(bookName);
    if(book == null){
      System.out.println("null at " + bookName);
    }
    if(book.getStatus().equals("AVAILABLE")){
      return true;
    }
    return false;
  }
  public void borrowToUser(User user, String bookName){
    if(findUser(user.getUserId()) != null){
      if(isBookAvailable(bookName)){
        Book book = findBook(bookName);
        book.setStockCount(book, "BORROW");
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
    User user = new User();
    user.setUserName(name);
    user.setUserId(idGenerator(name));
    user.setUserGmailId(gmailId);
    user.setUserPassword(password);
    user.setUserStatus("REGISTERED");
    System.out.println("\nYour user details. [note : Take note of your user id and password ]");
    if(userList.add(user)){
    printUserDetails(user.getUserId());
      writeUserDataToDisk(user);
      return true;
    }
    return false;
  }
  private void printBookDetails(Book book){
    System.out.println(book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | " + book.getStockCount());
  }
  private void printUserDetails(String userId){
    User user = findUser(userId);
    if(user != null){
      System.out.println(user.getUserId() + " | " + user.getuserPassword() + " | " + user.getUserName() + " | " + user.getUserGmailId() + " | " + user.getUserStatus());      
    }else{
      System.out.println("User not found");
    }      
  }

  public void viewBookList(){
    for(Book book : bookList){
      printBookDetails(book);
    }
  }
  public void viewUserList(){
    for(User user : userList){
      printUserDetails(user.getUserId());
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
    if (bookList.add(book) && (addBookToDisk(book))) {
      System.out.println("Book added");
    } else {
      System.out.println("Book not added");
    }
  }
  private boolean addBookToDisk(Book book){
    try(BufferedWriter bw = new BufferedWriter(new FileWriter("Books.txt", true))){
      String bookDetails = book.getBookName() + " | " + book.getBookAuthor() + " | " + book.getBookGenre() + " | " + book.getStockCount();
      bw.write(bookDetails);      
      bw.newLine();      
      return true;
    }catch(IOException e){
      System.err.println("Error writing task to disk. " + e);
    }
    return false;
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
  private void loadBooksFromDisk(){
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
  public User findUser(String userId){
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
