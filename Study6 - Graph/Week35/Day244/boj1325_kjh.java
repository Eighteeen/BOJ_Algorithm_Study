//// 문제풀이 실패: 로직 진짜 모르겠어요

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

class Main {
  static boolean[] visited;
  static int maxTrustedAmount;
  static Computer[] computers;

  public static void main(String[] args) throws Exception {
    final int COMPUTER_AMOUNT = Input.nextInt();
    final int TRUST_AMOUNT = Input.nextInt();

    computers = Stream.generate(Computer::new).limit(COMPUTER_AMOUNT + 1).toArray(Computer[]::new);

    for (int i = 0; i < TRUST_AMOUNT; i++) {
      int trusting = Input.nextInt();
      int trusted = Input.nextInt();

      computers[trusting].trust(computers[trusted]);
      computers[trusted].trustedBy(computers[trusting]);
    }

    visited = new boolean[COMPUTER_AMOUNT + 1];
    for (int i = 1; i <= COMPUTER_AMOUNT; i++) {
      fillTrustedAmount(computers[i]);
    }

    StringBuilder result = new StringBuilder();
    for (int i = 1; i <= COMPUTER_AMOUNT; i++) {
      if (computers[i].trustedAmount == maxTrustedAmount) {
        result.append(i).append(' ');
      }
    }

    System.out.print(result);
  }

  static void fillTrustedAmount(Computer computer) {
    if (computer.visited) return;
    computer.visited = true;
    
    final int IT_TRUST_ITSELF = 1;
    int trustedAmount = IT_TRUST_ITSELF;

    for (Computer computerTrustsMe : computer.computersTrustMe) {
      if (computerTrustsMe.visited) continue;
      fillTrustedAmount(computerTrustsMe);
      trustedAmount += computerTrustsMe.trustedAmount;

      boolean isTrustEachOther = computer.computersItTrust.contains(computerTrustsMe);
      if (isTrustEachOther) {
        trustedAmount--;
      }
    }
    
    computer.trustedAmount = trustedAmount;
    maxTrustedAmount = Math.max(maxTrustedAmount, trustedAmount);
    
    for (Computer computerItTrusts : computer.computersItTrust) {
      fillTrustedAmount(computerItTrusts);
    }
  }
}

class Computer {
  public List<Computer> computersItTrust;
  public List<Computer> computersTrustMe;
  public int trustedAmount;
  public boolean visited;

  public Computer() {
    computersItTrust = new ArrayList<>();
    computersTrustMe = new ArrayList<>();
  }

  public void trust(Computer computer) {
    computersItTrust.add(computer);
  }

  public void trustedBy(Computer computer) {
    computersTrustMe.add(computer);
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