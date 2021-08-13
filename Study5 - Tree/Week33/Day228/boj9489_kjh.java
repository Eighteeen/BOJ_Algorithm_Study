// 두통으로 인한 문제풀이 실패
// 쉬고 내일해야겠음

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();

    while (true) {
      int nodeAmount = Input.nextInt();
      int targetNode = Input.nextInt();
      if (nodeAmount == 0 && targetNode == 0) {
        break;
      }

      int[] nodes = Arrays.stream(Input.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      result.append(getNumberOfCousins(nodes, targetNode)).append('\n');
    }

    System.out.print(result);
  }

  static int target;
  static boolean isFoundTarget;

  static int previousAdded;

  static int targetSiblings;
  static int targetSiblingsAndCousins;

  static int siblings;
  static int siblingsAndCousins;

  static int availableParentCount;

  static void addNode(int node) {
    boolean alreadySolved = (targetSiblings != 0 && targetSiblingsAndCousins != 0);
    if (alreadySolved) {
      return;
    }

    if (node == target) {
      isFoundTarget = true;
    }

    boolean isNewSequence = node > (previousAdded + 1);
    if (isNewSequence) {
      if (isFoundTarget && targetSiblings == 0) {
        targetSiblings = siblings;
      }

      if (--availableParentCount <= 0) {
        if (isFoundTarget) {
          targetSiblingsAndCousins = siblingsAndCousins;
          return;
        }
        availableParentCount = siblingsAndCousins;
        siblingsAndCousins = 0;
      }
    }

    siblings++;
    siblingsAndCousins++;
    previousAdded = node;
  }

  static int getNumberOfCousins(int[] nodes, int target) {
    int targetSiblingsSameParent = 0;
    
    int siblings = 0;
    int siblingsSameParent = 0;
    int availableParentAmount = 0;
    
    int previousAdded = -1;
    boolean isFoundTarget = false;
    for (int i = 0; i < nodes.length; i++) {
      if (nodes[i] == target) {
        isFoundTarget = true;
      }
      
      boolean isNewSequence = nodes[i] > (previousAdded + 1);
      if (isNewSequence) {
        if (isFoundTarget) {
          targetSiblingsSameParent = siblingsSameParent;
          System.out.printf("현재 %d, 같은 부모 수 %d\n", nodes[i], targetSiblingsSameParent);
        }

        availableParentAmount--;
        if (availableParentAmount <= 0) {
          if (isFoundTarget) {
            break;
          }
          availableParentAmount = siblings;
          siblings = 0;
        }
        siblingsSameParent = 0;
      }
      
      siblingsSameParent++;
      
      siblings++;
      previousAdded = nodes[i];

      System.out.printf("%d / %d %d  %d\n", nodes[i], availableParentAmount, siblings, siblingsSameParent);
    }
    System.out.println(siblings + "///" + targetSiblingsSameParent);

    return siblings - targetSiblingsSameParent;
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