import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    final int N = inputInt();

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