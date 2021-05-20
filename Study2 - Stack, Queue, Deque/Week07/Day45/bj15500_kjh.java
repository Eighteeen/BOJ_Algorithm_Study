import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

// 소문과 달리 음주코딩은 클린코드에 좋지 못한 것 같다
//// 그런 것 같네요... ㅎㅎ 정리는 깨고 하는 걸루 ㅋㅋㅋ
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    List<Stack<Integer>> rods = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      rods.add(new Stack<Integer>());
    }

    final int K = Input.nextInt();
    for (int i = 0; i < K; i++) {
      rods.get(0).push(Input.nextInt());
    }

    int moveCount = 0;
    int currentOrder = K;
    while (currentOrder > 0) {
      //// 루프는 두번만 돌아가지만 들여쓰기로 인해 더 복잡한? 프로그램처럼 보여서 그 점이 조금 아쉽네요
      for (int i = 0; i <= 1; i++) {
        while (!rods.get(i).empty()) {
          int top = rods.get(i).pop();
          if (top == currentOrder) {
            //// 문제 자체로만 봤을 땐 3번째 막대에 옮기는 행위는 연산만 늘어나서 굳이 필요는 없을 것 같습니다
            //// 하지만 전체적인 구현의 흐름이나 저장을 위한 구현 문장이라면 그 나름대로도 좋을 것 같아요
            rods.get(2).push(top);
            currentOrder--;
            moveCount++;
            sb.append(i + 1).append(' ').append(3).append('\n');
            continue;
          }
          
          int another = (i == 0) ? 1 : 0;
          while (!rods.get(another).empty() && rods.get(another).peek() == currentOrder) {
            rods.get(2).push(rods.get(another).pop());
            currentOrder--;
            moveCount++;
            sb.append(another + 1).append(' ').append(3).append('\n');
          }

          rods.get(another).push(top);
          moveCount++;
          sb.append(i + 1).append(' ').append(another + 1).append('\n');
        }
      }
    }
    
    System.out.println(moveCount);
    System.out.print(sb);
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