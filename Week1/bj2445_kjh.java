import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) {
    final int N = inputInt();
    int height = 2 * N - 1; // 출력할 결과의 높이
    int width = 2 * N; // 출력할 결과의 너비

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i <= height; i++) {
      int stars = calcStars(i, height);
      int spaces = width - (stars * 2);
      sb.append("*".repeat(stars));
      sb.append(" ".repeat(spaces));
      sb.append("*".repeat(stars));
      sb.append("\n");
    }
    System.out.print(sb);
  }

  private static int calcStars(int i, int height) {
    boolean earlyPhase = i < height / 2;
    if (earlyPhase) {
      return i + 1;
    }
    return height - i;
  }

  private static int inputInt() {
    String strLine = null;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      strLine = br.readLine();
    }
    catch(Exception e) {}

    return Integer.parseInt(strLine);
  }
}