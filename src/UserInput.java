import java.util.Scanner;

public class Userinput {
  Scanner input = new Scanner(System.in);
  public static void main(String[] args){
    Userinput user = new Userinput();
    System.out.println("Welcome to My Library Management System");
    String menu;
    do{
    System.out.println("Are you an user or librarian. press [U - User, L - Librarian]");
    menu = user.input.nextLine().toUpperCase();
    switch(menu){
      case "U":
        System.out.println("Enter your username : ");
        
    }
    }
  }
}
