import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

//// 깔끔 : 22
class Main {
  public static void main(String[] args) throws Exception {
    Resistances resistances = new Resistances();
    String[] colors = { "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white" };
    
    for (int i = 0; i < colors.length; i++) {
      resistances.add(colors[i], i, (int) Math.pow(10, i));
    }

    long calculatedValue = resistances.getValue(Input.nextLine()) * 10;
    calculatedValue += resistances.getValue(Input.nextLine());
    calculatedValue *= resistances.getValueToMultiply(Input.nextLine());

    System.out.print(calculatedValue);
  }
}

class Resistances {
  private Map<String, Resistance> resistances;

  public Resistances() {
    resistances = new HashMap<>();
  }

  public void add(String color, int value, int valueToMultiply) {
    resistances.put(color, new Resistance(value, valueToMultiply));
  }

  public int getValue(String color) {
    return resistances.get(color).value;
  }
  
  public int getValueToMultiply(String color) {
    return resistances.get(color).valueToMultiply;
  }
}

class Resistance {
  public int value;
  public int valueToMultiply;

  public Resistance(int value, int valueToMultiply) {
    this.value = value;
    this.valueToMultiply = valueToMultiply;
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