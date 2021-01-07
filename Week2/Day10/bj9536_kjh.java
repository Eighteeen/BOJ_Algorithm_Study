import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

class Main {
  public static void main(String[] args) throws Exception {
    final int T = Input.nextInt();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < T; i++) {
      String record = Input.nextLine();
      List<String> knownCryings = new ArrayList<String>();

      while (true) {
        String animalGoesCrying = Input.nextLine();
        if (animalGoesCrying.equals("what does the fox say?")) break;
        
        knownCryings.add(extractCrying(animalGoesCrying));
      }

      sb.append(excludeKnownCryings(record, knownCryings))
        .append('\n');
    }
    System.out.print(sb);
  }
  
  private static String extractCrying(String animalGoesCrying) {
    int cryingIndex = animalGoesCrying.lastIndexOf(' ') + 1;
    return animalGoesCrying.substring(cryingIndex);
  }

  //// 구현을 꼼꼼하게 잘 하신 거 같아요:22
  private static StringBuilder excludeKnownCryings(String record, List<String> knownCryings) {
    StringBuilder excludedKnownCryings = new StringBuilder();
    StringTokenizer cryings = new StringTokenizer(record, " ");
    while (cryings.hasMoreTokens()) {
      String crying = cryings.nextToken();
      if (knownCryings.indexOf(crying) == -1) {
        excludedKnownCryings.append(crying)
                            .append(' ');
      }
    }
    return excludedKnownCryings;
  }
}

class Input {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokenizer;

  public static String nextLine() {
    return readLine();
  }

  public static int nextInt() {
    String nextString = next();
    return Integer.parseInt(nextString);
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
    return new StringTokenizer(readLine(), " ");
  }

  private static String readLine() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}