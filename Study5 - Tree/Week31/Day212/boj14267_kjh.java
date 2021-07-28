import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

//// 깔끔해요
class Main {
  static int[] praisedValues;

  public static void main(String[] args) throws Exception {
    final int EMPLOYEE_COUNT = Input.nextInt();
    final int PRAISE_COUNT = Input.nextInt();
    
    Employee[] employees = new Employee[EMPLOYEE_COUNT + 1];
    praisedValues = new int[EMPLOYEE_COUNT + 1];
    
    for (int i = 1; i <= EMPLOYEE_COUNT; i++) {
      int bossIdx = Input.nextInt();

      employees[i] = new Employee(i);
      employees[i].bossIdx = bossIdx;
      
      if (bossIdx == -1) continue;
      employees[bossIdx].addJunior(employees[i]);
    }
    
    for (int i = 0; i < PRAISE_COUNT; i++) {
      int praiseTarget = Input.nextInt();
      int praiseValue = Input.nextInt();
      
      employees[praiseTarget].praisedValue += praiseValue;
    }

    Employee root = employees[1];
    fillPraisedValues(root, 0);
    
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= EMPLOYEE_COUNT; i++) {
      sb.append(praisedValues[i])
        .append(' ');
    }

    System.out.print(sb);
  }

  static void fillPraisedValues(Employee root, int bossPraises) {
    int rootIdx = root.idx;

    praisedValues[rootIdx] = bossPraises + root.praisedValue;
    
    for (Employee junior : root.juniors) {
      fillPraisedValues(junior, praisedValues[rootIdx]);
    }
  }
}

class Employee {
  public int idx;
  public int bossIdx;
  public int praisedValue;
  public List<Employee> juniors;

  public Employee(int idx) {
    this.idx = idx;
    juniors = new ArrayList<>();
  }

  public void addJunior(Employee junior) {
    juniors.add(junior);
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