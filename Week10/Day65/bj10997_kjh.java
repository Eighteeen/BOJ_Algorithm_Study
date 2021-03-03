import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  static boolean[][] stars;

  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    if (N == 1) {
      System.out.print("*");
      return;
    }

    //// 위에서 N == 1이면 리턴하는데 밑에 그냥 4 * (N - 1) + 3 해주시면 되지 않나요?
    int rowSize = (N == 1) ? (1) : (4 * (N - 1) + 3);
    int colSize = 4 * (N - 1) + 1;
    stars = new boolean[rowSize][colSize];

    makeSnailStars(N, 0, 0);
    printStars(rowSize, colSize);
  }

  static void makeSnailStars(int step, int row, int col) {
    if (step == 2) { 
      baseStars(row, col);
      return;
    }

    //// 현재 로직상 step이 1인 경우는 없지 않나요? 
    int rowSize = (step == 1) ? (1) : (4 * (step - 1) + 3);
    int colSize = 4 * (step - 1) + 1;
    int lastRow = row + rowSize - 1;
    int lastCol = col + colSize - 1;

    for (int i = 0; i < rowSize; i++) {
      stars[row+i][col] = true;
      stars[row+i][lastCol] = true;
    }
    //// 위쪽 for문에서 first col과 last col 처리를 해줘서 1 ~ colSize - 1보다 작을 때까지만 루프 도는 게 중복이 없어 더 깔끔할 것 같습니다
    for (int i = 0; i < colSize; i++) {
      stars[row][col+i] = true;
      stars[lastRow][col+i] = true;
    }
    
    stars[row+1][lastCol] = false;
    stars[row+2][lastCol-1] = true;

    makeSnailStars(step - 1, row + 2, col + 2);
  }

  static void baseStars(int row, int col) {
    int lastRow = row + 7 - 1;
    int lastCol = col + 5 - 1;

    for (int i = 0; i < 7; i++) {
      stars[row+i][col] = true;
      stars[row+i][lastCol] = true;
    }
    for (int i = 0; i < 5; i++) {
      stars[row][col+i] = true;
      stars[lastRow][col+i] = true;
    }

    stars[row+1][lastCol] = false;

    stars[row+2][col+3] = true;
    //// 위 코드까지는 사실상 makeSnailStars 와 같은 기능을 하고 아래 부분만 다른 점인데, 따로 함수를 만들어 진행된다는 점이 아쉽습니다.
    //// 위쪽은 재귀 함수 내에서 활용하고, 밑에 내용만 따로 진행하는 건 어떤가요?
    for (int i = 0; i < 3; i++) {
      stars[row+2+i][col+2] = true;
    }
  }

  static void printStars(int rowSize, int colSize) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < rowSize; i++) {
      for (int j = 0; j < colSize; j++) {
        sb.append(stars[i][j] ? '*' : ' ');
        if (i == 1 && j == 0) break;
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
  }

  public static double nextDouble() {
    String nextString = next();
    return Double.parseDouble(nextString);
  }

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
  }

  public static char nextChar() {
    String nextString = next();
    return nextString.charAt(0);
  }
  
  public static String next() {
    makeTokensIfNeed();
    return tokenizer.nextToken();
  }

  private static void makeTokensIfNeed() {
    makeTokensIfNotReadedYet();
    makeTokensIfHasNoToken();
  }

  private static void makeTokensIfNotReadedYet() {
    if (tokenizer == null) {
      tokenizer = makeTokens();
    }
  }

  private static void makeTokensIfHasNoToken() {
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}