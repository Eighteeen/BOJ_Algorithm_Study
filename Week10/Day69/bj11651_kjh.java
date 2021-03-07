import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

//// 깔-끔
class Main {
  public static void main(String[] args) {
    Points points = new Points();
    final int N = Input.nextInt();

    for (int i = 0; i < N; i++) {
      int x = Input.nextInt();
      int y = Input.nextInt();
      points.add(x, y);
    }

    points.sort();
    points.print();
  }
}

class Points {
  List<Point> points;
  
  public Points() {
    points = new ArrayList<>();
  }

  public void add(int x, int y) {
    points.add(new Point(x, y));
  }

  //// 오호 이런 방법도 있군요 배워갑니다!
  public void sort() {
    points.sort(
      Comparator
        .comparing(Point::getY)
        .thenComparing(Point::getX)
    );
  }

  public void print() {
    StringBuilder sb = new StringBuilder();
    points.stream()
      .forEach(point -> sb.append(point.toString()).append('\n'));
    System.out.print(sb);
  }
}

class Point {
  private int x;
  private int y;
  
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  @Override
  public String toString() {
    return x + " " + y;
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