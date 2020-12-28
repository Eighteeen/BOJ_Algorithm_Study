import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws Exception{
    final int N = inputInt();
    int height = 2 * N - 1; // 출력할 결과의 높이
    int width = 2 * N; // 출력할 결과의 너비

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < height; i++) {
      int stars = calcStars(i, height);
      int spaces = width - (stars * 2);
      sb.append("*".repeat(stars))
        .append(" ".repeat(spaces))
        .append("*".repeat(stars))
        .append("\n");
    }
    System.out.print(sb);

    br.close();
  }

  private static int calcStars(int currentIndex, int length) {
    boolean earlyPhase = currentIndex < (length / 2);
    if (earlyPhase) {
      return currentIndex + 1;
    }
    return length - currentIndex;
  }

  private static int inputInt() {
    String strLine = null;
    try {
      strLine = br.readLine();
    } catch(Exception e) { }

    return Integer.parseInt(strLine);
  }
}