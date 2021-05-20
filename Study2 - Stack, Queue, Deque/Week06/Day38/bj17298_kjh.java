// 문제 실패 : 시간초과
// 최악의 경우 O(N^2)이 걸리기 때문에 실패하였음
// 시간을 단축할 수 있는 방법이 전혀 떠오르지 않았다.
  // 아래 코드도 시도해봤고
  // 입력 값을 전부 Stack에 담아서 하나씩 pop하며 오른쪽부터 검사하는 로직도 짜봤는데 오히려 더 시간 걸리는 것 같았음

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();

    final int N = Input.nextInt();
    Queue<Integer> numbers = new LinkedList<>();
    Arrays.stream(Input.nextLine().split(" "))
      .map(Integer::valueOf)
      .forEach(item -> numbers.offer(item));
    
    for (int i = 0; i < N; i++) {
      int current = numbers.poll();

      int okn = numbers.stream()
        .filter(item -> (item > current))
        .findFirst()
        .orElse(-1);
      sb.append(okn).append(' ');
    }

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