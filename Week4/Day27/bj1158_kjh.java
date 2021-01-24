import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
  ////굉장히 깔끔하네요
class Main {
  public static void main(String[] args) throws Exception {
    final int NUMBER_OF_PEOPLE = Input.nextInt();
    final int REMOVE_NTH_PERSON = Input.nextInt();
    //// 이 기능 쓰고 싶었는데,,,못찾았어요 굿굿!
    List<Integer> peopleList = IntStream.range(1, NUMBER_OF_PEOPLE + 1) // 파이썬의 Range처럼 동작함 
      .boxed() // int -> Integer로 박싱
      .collect(Collectors.toList()); // List로 collect

    Queue<Integer> queue = new LinkedList<>();
    int removeIndex = 0;
    for (int i = 0; i < NUMBER_OF_PEOPLE; i++) {
      removeIndex = (removeIndex + REMOVE_NTH_PERSON - 1) % peopleList.size();
      queue.add(peopleList.remove(removeIndex));
    }
    
    System.out.print(
      queue.stream()
        .map(String::valueOf) // join 해주기 위해 String으로 변환
        .collect(Collectors.joining(", ", "<", ">")) // 사이에 넣을 문자열, 앞에 넣을 문자열, 뒤에 넣을 문자열
    );
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