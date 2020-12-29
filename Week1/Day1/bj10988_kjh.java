import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    final String WORD = inputString();
    int wordLength = WORD.length();
    
    //// 문자 길이가 길어질수록 빛을 바라는 코드네요 굿
    for (int i = 0; i < (wordLength / 2); i++) {
      char farLeftChar = WORD.charAt(i);
      char farRightChar = WORD.charAt(wordLength - i - 1);
        ////return을 잘 이용한거 같아요
      if (farLeftChar != farRightChar) {
        System.out.println("0");
        return;
      }
    }
    System.out.println("1");
  }

  private static String inputString() {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }
}
