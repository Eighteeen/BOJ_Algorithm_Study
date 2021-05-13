import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();

    Set set = new Set();
    for (int i = 0; i < N; i++) {
      String methodName = Input.next();

      Method method = getMethod(methodName);
      if (method.getParameters().length == 0) {
        method.invoke(set);
        continue;
      }
      
      Object returnValue = method.invoke(set, new Object[] { Input.nextInt() });
      if (returnValue != null) {
        sb.append(returnValue).append('\n');
      }
    }
    
    System.out.print(sb);
  }

  private static Method getMethod(String methodName) throws Exception {
    Method method = null;

    try {
      method = Class.forName("Set").getDeclaredMethod(methodName);
    } catch (NoSuchMethodException e) {
      method = Class.forName("Set").getDeclaredMethod(methodName, int.class);    
    }

    return method;
  }
}

class Set {
  private static final int SET_SIZE = 20;  
  private int bitmask;

  public Set() {
    bitmask = 0;
  }

  public void add(int x) {
    bitmask |= (1 << (x-1));
  }
  
  public void remove(int x) {
    bitmask &= ~(1 << (x-1));
  }

  public int check(int x) {
    return ( (bitmask & (1 << (x-1))) > 0 ) ? 1 : 0;
  }

  public void toggle(int x) {
    bitmask ^= (1 << (x-1));
  }

  public void all() {
    bitmask = (1 << SET_SIZE) - 1;
  }

  public void empty() {
    bitmask = 0;
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
    if (tokenizer.hasMoreTokens() == false) {
      tokenizer = makeTokens();
    }
  }
  
  private static StringTokenizer makeTokens() {
    return new StringTokenizer(nextLine(), " ");
  }
}