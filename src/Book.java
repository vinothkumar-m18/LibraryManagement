public class Book {
  private String bookName;
  private String bookAuthor;
  private String bookGenre;
  private boolean isAvailable;
  public void setBookName(String bookName){
    this.bookName = bookName;
  }
  public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
  }
  public void setBookGenre(String bookGenre){
    this.bookGenre = bookGenre;
  }
  public String getBookName(){
    return this.bookName;
  }
  public String getBookAuthor(){
    return this.bookAuthor;
  }
  public String getBookGenre(){
    return this.bookGenre;
  }
  public void setBookStatus(boolean status){
    this.isAvailable = status;
  }
  public boolean getStatus(){
    return this.isAvailable;
  }
  
}
