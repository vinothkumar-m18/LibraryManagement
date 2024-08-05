public class User {
  private String userName;
  private String userId;
  private String userGmailId;
  private String userStatus;
  private String userPassword;
  private User user;
  public void setUserName(String userName){
    user = new User();
    this.user.userName = userName;
  }
  public void setUserId(String userId){
    user = new User();
    this.user.userId = userId;    
  }
  public void setUserGmailId(String userGmailId){
    user = new User();
    this.user.userGmailId = userGmailId;
  }
  public void setUserStatus(String userStatus){
    user = new User();
    this.user.userStatus = userStatus;
  }
  public void setUserPassword(String password){
    user = new User();
    this.user.userPassword = password;
  }
  public String getUserName(){
    return this.userName;
  }
  public String getUserId(){
    return this.userId;
  }
  public String getUserGmailId(){
    return this.userGmailId;
  }
  public String getUserStatus(){
    return this.userStatus;
  }
  public String getuserPassword(){
    return this.userPassword;
  }
  public void borrowBook(String bookName){
    Librarian librarian = new Librarian();
    librarian.borrowToUser(this, bookName);
  }
  public void returnBook(String bookName){
    Librarian librarian = new Librarian();
    librarian.returnBookFromUser(bookName);
  }
  public void viewAllAvailableBooks(){
    Librarian librarian = new Librarian();
    librarian.showAllAvailableBooks();
  }
}
