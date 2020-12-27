import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    final String WORD = inputString();
    int wordLength = WORD.length();

    for(int i = 0; i < wordLength; i++) {
      char currentChar = WORD.charAt(i);
      System.out.print(currentChar);

      if (isMultiplesOfTen(i + 1)) {
        System.out.println();
      }
    }
  }

  private static boolean isMultiplesOfTen(int number) {
    return number % 10 == 0;
  }

  private static String inputString() {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }
}