import java.util.Scanner;

class Main {
  private static final Scanner SCANNER = new Scanner(System.in);
  public static void main(String[] args) {
    int price = inputInt();
      ////값을 받아서 빼주는게 훨씬 간단하네요. 좋은 코드인거 같습니다
    for(int i = 0; i < 9; i++) {
      price -= inputInt();
    }
    
    System.out.println(price);
  }

  private static int inputInt() {
    return SCANNER.nextInt();
  }
}
