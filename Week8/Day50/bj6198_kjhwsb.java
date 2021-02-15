import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {
  public static void main(String[] args) throws Exception {
    final int N = Input.nextInt();
    Stack<Building> buildings = new Stack<>();

    long count = 0;
    for (int i = 0; i < N; i++) {
      Building currentBuilding = new Building(i, Input.nextInt());

      while (!buildings.isEmpty() &&
        currentBuilding.height >= buildings.peek().height) {
        Building leftBuilding = buildings.pop();
        count += currentBuilding.number - leftBuilding.number - 1;
      }

      buildings.push(currentBuilding);
    }

    if (buildings.isEmpty()) {
      System.out.print(count);
      return;
    }

    int topNumber = buildings.pop().number;
    while (!buildings.isEmpty()) {
      count += topNumber - buildings.pop().number;
    }

    System.out.print(count);
  }
}

class Building {
  public final int number;
  public final int height;

  public Building(int number, int height) {
    this.number = number;
    this.height = height;
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