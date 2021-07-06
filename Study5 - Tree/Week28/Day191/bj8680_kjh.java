import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final int NTH_BALL = Input.nextInt();
    final int TREE_HEIGHT = Input.nextInt();
    
    System.out.print(TREE_HEIGHT == 0 ? 0 : getDestinationOfNthBall(NTH_BALL, TREE_HEIGHT));
  }

  static int getDestinationOfNthBall(int nth, int treeHeight) {
    nth -= 1;
    int treeSize = (int) Math.pow(2, treeHeight);
    
    int startNum = 0;
    int endNum = treeSize - 1;
    for (int i = 1; i <= treeHeight; i++) {
      int iLevelNodeCount = (int) Math.pow(2, i);

      int middle = (startNum + endNum) / 2;
      if (nth % iLevelNodeCount < (iLevelNodeCount / 2)) {
        endNum = middle;
      } else {
        startNum = middle + 1;
      }
    }

    return startNum;
  }

  // 코드만으로 로직을 전달하기 어렵다고 생각돼서 설명 추가 작성함

  // 트리의 높이가 3일때 n번째 공이 이동하는 경로는 아래와 같음
  // 1: 좌좌좌
  // 2: 우좌좌 
  // 3: 좌우좌
  // 4: 우우좌
  // 5: 좌좌우
  // 6: 우좌우
  // 7: 좌우우
  // 8: 우우우

  // 세로로 보면
  // 좌우좌우좌우좌우
  // 좌좌우우좌좌우우
  // 좌좌좌좌우우우우
  // 가장 낮은 깊이에서는 '좌우'가 반복
  // 그것보다 한단계 깊어지면 '좌좌우우'가
  // 또 그것보다 한단계 깊어지면 '좌좌좌좌우우우우'가 반복되는 규칙을 찾음

  // 위 규칙을 일반화하여 아래와 같은 식을 만들었음
  // 원하는 n번째 공 = nth
  // 현재 깊이의 노드개수 = nodeCount
  // ((nth - 1) % nodeCount) < (nodeCount / 2)
    // 위 조건을 성립하면 '좌'
    // 성립하지 않으면 '우'
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