import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  static Map<Integer, Ice> iceMap;
  static int SUPPORTERS;
  static int foundSupporters;
  static int leftIces;
  static Queue<Ice> queue;

  public static void main(String[] args) throws Exception {
    final int ICES = Input.nextInt();
    SUPPORTERS = Input.nextInt();
    final int PENGUIN_LOCATION = Input.nextInt();

    iceMap = new HashMap<>();

    for (int i = 1; i < ICES; i++) {
      int iceANum = Input.nextInt();
      int iceBNum = Input.nextInt();

      interconnectTwoIce(iceANum, iceBNum);
    }

    Ice penguinLocation = iceMap.get(PENGUIN_LOCATION);

    queue = new LinkedList<>();
    getLeftIces(penguinLocation);
    leftIces++;

    int brokenIces = ICES - leftIces;
    System.out.print(brokenIces);
  }

  static void getLeftIces(Ice root) {
    if (foundSupporters >= 2) return;

    if (root.isSupporter) {
      leftIces += root.depth;
      foundSupporters++;
    } else {
      for (Ice child : root.connected) {
        child.disconnect(root);

        child.depth = root.depth + 1;
        queue.offer(child);
      }
    }

    if (queue.isEmpty()) { 
      return;
    }

    Ice nextIce = queue.poll();
    getLeftIces(nextIce);
  }

  static void interconnectTwoIce(int iceANum, int iceBNum) {
    Ice iceA = getOrCreateIce(iceANum, iceANum <= SUPPORTERS);
    Ice iceB = getOrCreateIce(iceBNum, iceBNum <= SUPPORTERS);

    iceA.connect(iceB);
    iceB.connect(iceA);
  }

  static Ice getOrCreateIce(int number, boolean isSupporter) {
    Ice ice;
    if (iceMap.containsKey(number)) {
      ice = iceMap.get(number);
    } else {
      ice = new Ice(number, isSupporter);
      iceMap.put(number, ice);
    }

    return ice;
  }
}

class Ice {
  public int number;
  public List<Ice> connected;
  public boolean isSupporter;
  public int depth;

  public Ice(int number, boolean isSupporter) {
    this.number = number;
    this.isSupporter = isSupporter;
    connected = new ArrayList<>();
  }

  public void connect(Ice ice) {
    connected.add(ice);
  }

  public void disconnect(Ice ice) {
    connected.remove(ice);
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