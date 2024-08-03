public class User {
  private String userName;
  private long userId;
  private String userGmailId;
  private String userStatus;
  public void setUserName(String userName){
    this.userName = userName;
  }
  public void setUserId(long userId){
    this.userId = userId;    
  }
  public void setUserGmailId(String userGmailId){
    this.userGmailId = userGmailId;
  }
  public void setUserStatus(String userStatus){
    this.userStatus = userStatus;
  }
  public String getUserName(){
    return this.userName;
  }
  public long getUserId(){
    return this.userId;
  }
  public String getUserGmailId(){
    return this.userGmailId;
  }
  public String getUserStatus(){
    return this.userStatus;
  }
  public void borrowBook(){}
  public void returnBook(){}
  
}
