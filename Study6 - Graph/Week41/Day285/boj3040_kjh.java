import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    List<Integer> dwarfNums = new ArrayList<>();
    int sumOfDwarfNums = 0;

    for (int i = 0; i < 9; i++) {
      int dwarfNum = Input.nextInt();
      dwarfNums.add(dwarfNum);
      sumOfDwarfNums += dwarfNum;
    }

    //// 오 이렇게 조합할 수도 있네요 알아가요~~ : 22
    for (int i = 0; i < 8; i++) {
      for (int j = 1; j < 9; j++) {
        boolean foundFakeDwarfs = sumOfDwarfNums - dwarfNums.get(i) - dwarfNums.get(j) == 100;
        if (foundFakeDwarfs) {
          final int fakeDwarf1 = dwarfNums.get(i);
          final int fakeDwarf2 = dwarfNums.get(j);

          dwarfNums.stream()
            .filter(dwarfNum -> (dwarfNum != fakeDwarf1 && dwarfNum != fakeDwarf2))
            .forEach(System.out::println);
          return;
        }
      }
    }
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    try {
      String line = br.readLine();
      if (line.equals("")) {
        return nextLine();
      }
      return line;
    } catch (Exception e) {
    }

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

  public static void skipLine() {
    nextLine();
  }

  public static void skipLine(int n) {
    for (int i = 0; i < n; i++) {
      nextLine();
    }
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
    if (!tokenizer.hasMoreTokens()) {
      tokenizer = makeTokens();
    }
  }

  private static StringTokenizer makeTokens() {
    StringTokenizer tokens = new StringTokenizer(nextLine(), " ");
    if (tokens.hasMoreTokens() == false) {
      makeTokens();
    }
    return tokens;
  }
}