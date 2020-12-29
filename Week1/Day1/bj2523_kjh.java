import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    final int N = inputInt();
          //// 함수를 잘 활용한거 같아요. 이해하기 편하네요
          //// 함수 활용을 잘 한 것 같아요.
    for (int stars = 1; stars < N; stars++) {
      printStarsAndReturn(stars);
    }

    for (int stars = N; stars >= 1; stars--) {
      printStarsAndReturn(stars);
    }
  }

  private static int inputInt() {
    Scanner sc = new Scanner(System.in);
    return sc.nextInt();
  }

  private static void printStarsAndReturn(int stars) {
    for (int i = 0; i < stars; i++) {
      System.out.print("*");
    }
    System.out.println();
  }
}
