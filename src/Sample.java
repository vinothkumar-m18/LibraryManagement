public class Sample {

  public static void main(String[] args) {
    int input = 1;
    int input2 = 10;
    switch (input) {
      case 1:
        System.out.println("one");
        switch (input2) {
          case 10:
            System.out.println("one + ten");
            break;
        
          default:
            break;
        }
      case 2:
        System.out.println("two");
        break;
      case 3:
        System.out.println("three");
        break;
      default:
        break;
    }
  }
}