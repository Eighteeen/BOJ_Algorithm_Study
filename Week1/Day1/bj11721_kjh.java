import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    final String WORD = inputString();
    int wordLength = WORD.length();
          ////wordLength변수를 굳이 안만들어줬어도 될거같아요
    for(int i = 0; i < wordLength; i++) {
      char currentChar = WORD.charAt(i);
      System.out.print(currentChar);
      //// 하나씩 print하게 되면 메모리 낭비가 될 것 같아요
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
