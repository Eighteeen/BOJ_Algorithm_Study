import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
////깔끔합니다
class Main {
  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    final int N = Input.nextInt();
    
    List<String> words = new ArrayList<String>();
    for (int i = 0; i < N; i++) {
      words.add(Input.nextLine());
    }
  //// 길이순으로 정렬하고 사전순으로 정렬하는게 한번에 될거라곤 상상도 못했네요. : 22 출력까지 한번에... stream 활용을 정말 잘 하신 거 같아요
  //// => stream이 정말 좋더라구요
    words.stream()
      .distinct() // 중복 제거
      .sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())) // 길이로 정렬한 후 오름차순 정렬
      .forEach(word -> sb.append(word).append('\n')); // 각각 StringBuilder에 줄바꿈을 붙여 삽입

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
