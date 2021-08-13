// 문제풀이 실패 : 모범답안
// http://wookje.dance/2018/03/30/boj-9489-%EC%82%AC%EC%B4%8C/
// 위 코드를 이해하고 재해석해서 Java로 짰음
// 부모의 인덱스를 저장시켜놓고 그걸로 조부모가 같은지, 부모가 다른지를 체크하여 사촌을 알아내는 로직
// 부모의 인덱스를 이렇게 쉽게 저장시킬 수 있는지 몰랐음

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder result = new StringBuilder();

    while (true) {
      int nodeAmount = Input.nextInt();
      int targetNode = Input.nextInt();
      if (nodeAmount == 0 && targetNode == 0)
        break;

      int[] nodes = new int[nodeAmount + 1];
      nodes[0] = -1;
      int[] parentIndices = new int[nodeAmount + 1];
      parentIndices[0] = -1;

      int targetIndex = 0;
      int parentIndex = -1;
      for (int i = 1; i <= nodeAmount; i++) {
        nodes[i] = Input.nextInt();
        if (nodes[i] == targetNode) targetIndex = i;
        if (nodes[i] != nodes[i - 1] + 1) parentIndex++;
        parentIndices[i] = parentIndex;
      }

      int cousins = 0;

      //// isSibling과 hasSameGrandParent로 변경하여 이해가 잘되네요
      //// 피드백 하고싶어서 남겨요 => 아유 감사합니다 ㅎㅎ
      for (int i = 1; i <= nodeAmount; i++) {
        boolean hasSameParent = parentIndices[i] == parentIndices[targetIndex];
        boolean hasSameGrandparent = parentIndices[parentIndices[i]] == parentIndices[parentIndices[targetIndex]];

        if (!hasSameParent && hasSameGrandparent) {
          cousins++;
        }
      }
      result.append(cousins).append('\n');
    }
    System.out.print(result);
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