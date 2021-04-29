// 문제 풀이 실패 : 나머지의 정의는 대체 무엇..?

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();

    for (int i = 0; i < T; i++) {
      Bitmask fx = inputPolynomial();
      Bitmask gx = inputPolynomial();
      Bitmask hx = inputPolynomial();

      
    }
  }

  static Bitmask inputPolynomial() {
    int highestDegree = Input.nextInt();
    Bitmask polynomial = new Bitmask(highestDegree);

    for (int degree = highestDegree - 1; degree >= 0; degree--) {
      int coefficient = Input.nextInt();
      if (coefficient == 0) continue;
      
      polynomial.insert(degree);
    }
    
    return polynomial;
  }
}

class Bitmask {
  private byte[] bits;

  public Bitmask(int bitSize) {
    int byteSize = bitSize / 8;
    byteSize += (bitSize % 8 > 0) ? (1) : (0);

    bits = new byte[byteSize];
  }

  public boolean exists(int n) {
    return (bits[n / 8] & (1 << (n % 8))) > 0;
  }

  public void insert(int n) {
    bits[n / 8] |= 1 << (n % 8);
  }

  public void remove(int n) {
    bits[n / 8] &= (1 << (n % 8)) - 1;
  }

  private Bitmask add(Bitmask bitmask) {
    bitmask.getBits()
  }

  public Bitmask mutiply(Bitmask bitmask) {

  }

  public Bitmask modulo(Bitmask bitmask) {

  }

  public byte[] getBits() {
    return bits;
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