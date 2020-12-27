import java.util.Scanner;

class Main {
  private static final Scanner SCANNER = new Scanner(System.in);
  public static void main(String[] args) {
    int price = inputInt();

    for(int i = 0; i < 9; i++) {
      price -= inputInt();
    }
    
    System.out.println(price);
  }

  private static int inputInt() {
    return SCANNER.nextInt();
  }
}