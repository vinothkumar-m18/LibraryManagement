/**
 * Represents a book which has a name, author, genre, and stock count.
 * Provides setters and getters for setting and getting the attributes of the
 * book, including the ability to update the stock count
 */
public class Book {
  private String bookName; // To store the book's name
  private String bookAuthor; // To store the book's author name
  private String bookGenre; // To store the book's genre
  private int stockCount; // To store the book's stock count

  /**
   * Sets the book's name
   * 
   * @param bookName the name of the book to set
   */
  public void setBookName(String bookName) {
    this.bookName = bookName;
  }

  /**
   * Sets the book's author name
   * 
   * @param bookAuthor the name of the author to set
   */
  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  /**
   * Sets the book's genre
   * 
   * @param bookGenre the genre of the book to set
   */
  public void setBookGenre(String bookGenre) {
    this.bookGenre = bookGenre;
  }

  /**
   * Adds to the book's stock count
   * 
   * @param stockCount the amount to add to the current stock count
   */
  public void setStockCount(int stockCount) {
    this.stockCount += stockCount;
  }

  /**
   * Gets the book's name
   * 
   * @return the name of the book
   */
  public String getBookName() {
    return this.bookName;
  }

  /**
   * Gets the book's author name
   * 
   * @return the name of the author
   */
  public String getBookAuthor() {
    return this.bookAuthor;
  }

  /**
   * Gets the book's genre
   * 
   * @return the genre of the book
   */
  public String getBookGenre() {
    return this.bookGenre;
  }

  /**
   * Gets the book's status
   * 
   * @return "AVAILABLE" if atleast one stock is available; "UNAVAILABLE" otherwise
   */
  public String getStatus() {
    return (stockCount > 0) ? "AVAILABLE" : "UNAVAILABLE";
  }

  /**
   * Gets the book's stock count
   * 
   * @return the stock count of the book
   */
  public int getStockCount() {
    return this.stockCount;
  }

}
