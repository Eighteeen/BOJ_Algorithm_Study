import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    int[] colors = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int boxes = 0;

    boxes += packByThree(colors);
    boxes += packByTwoAfterByThree(colors);
    boxes += packByOne(colors);

    System.out.print(boxes);
  }

  static int packByThree(int[] colors) {
    int boxes = 0;

    int minColor = (colors[0] < colors[1]) ? colors[0] : colors[1];
    minColor = (minColor < colors[2]) ? minColor : colors[2];

    for (int i = 0; i < 3; i++) {
      colors[i] -= minColor;
    }
    boxes += minColor;

    for (int i = 0; i < 3; i++) {
      if (colors[i] < 3) continue;
    
      boxes += colors[i] / 3;
      colors[i] %= 3;
    }
    
    return boxes;
  }

  static int packByTwoAfterByThree(int[] colors) {
    int boxes = 0;

    for (int i = 0; i < 3; i++) {
      if (colors[i] < 2) continue;
    
      boxes += colors[i] / 2;
      colors[i] %= 2;
    }

    int existingColorIndex = -1;
    for (int i = 0; i < 3; i++) {
      if (colors[i] == 0) continue;
      if (existingColorIndex == -1) {
        existingColorIndex = i;
        continue;
      }
      colors[existingColorIndex] = 0;
      colors[i] = 0;
      boxes += 1;
    }

    return boxes;
  }

  static int packByOne(int[] colors) {
    return colors[0] + colors[1] + colors[2];
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

  public static long nextLong() {
    String nextString = next();
    return Long.parseLong(nextString);
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