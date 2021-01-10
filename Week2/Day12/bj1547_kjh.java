import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  ////깔끔하고 boolean형으로 짜신거 배워갑니다. : 22 공의 위치에 따라 초기 true값을 설정하면 공의 위치도 코드로 바로 알고 활용도도 높아보이네요
  ////=> 구체적인 칭찬 넘나 황송한 것
  public static void main(String[] args) throws Exception {
    boolean[] ballInCup = { true, false, false };
    final int M = Input.nextInt();

    for (int i = 0; i < M; i++) {
      int cupXIndex = Input.nextInt() - 1;
      int cupYIndex = Input.nextInt() - 1;

      boolean ballInCupX = ballInCup[cupXIndex];
      boolean ballInCupY = ballInCup[cupYIndex];
        ////오 bool 이어서 이렇게 그냥 상태만 바꿔주면 되는거네요!  
        ////=> 네에 두가지의 상태밖에 없으니까요
      if (ballInCup[cupXIndex] != ballInCup[cupYIndex]) {
        ballInCup[cupXIndex] = !ballInCup[cupXIndex];
        ballInCup[cupYIndex] = !ballInCup[cupYIndex];
      }
    }
    
    for (int i = 0; i < 3; i++) {
      if (ballInCup[i] == true) {
        System.out.print(i+1);
        break;
      }
    }
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
