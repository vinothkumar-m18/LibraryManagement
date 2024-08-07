public class Book {
  private String bookName;
  private String bookAuthor;
  private String bookGenre;
  private int stockCount;

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
  public void setStockCount(int count){
    this.stockCount = count;
  }
  public void setStockCount(Book book, String operation){ 
    if(operation.equals("BORROW")){
       book.stockCount -= 1;
    }else if(operation.equals("RETURN")){
      book.stockCount += 1;
    }else{
      System.out.println("Invalid operation");
    }
  }

  public String getStatus(){
    return (stockCount > 0) ? "AVAILABLE" : "UNAVAILABLE";
  }
  public int getStockCount(){
    return this.stockCount;
  }
  
}
