import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
  ////깔끔하고 무난하게 잘 짰습니다.
class Main {
  public static void main(String[] args) throws Exception {
    final int[] gandalfUnitScores = { 1, 2, 3, 3, 4, 10 };
    final int[] sauronUnitScores = { 1, 2, 2, 2, 3, 5, 10 };

    int BATTLE = Input.nextInt();

    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < BATTLE; i++) {
      String[] gandalfCount = Input.nextLine().split(" ");
      String[] sauronCount =  Input.nextLine().split(" ");

      int gandalfGrade = calcGrade(gandalfUnitScores, gandalfCount);
      int sauronGrade = calcGrade(sauronUnitScores, sauronCount);

      if (gandalfGrade > sauronGrade) {
        sb.append(makeBattleMessage(i + 1, "Good triumphs over Evil"));
      }
      else if (sauronGrade > gandalfGrade) {
        sb.append(makeBattleMessage(i + 1, "Evil eradicates all trace of Good"));
      }
      else {
        sb.append(makeBattleMessage(i + 1, "No victor on this battle field"));
      }
    }
    System.out.print(sb);
  }
    ////이부분도 따로 함수를 만들어줘서 위에서 가독성이 좋아진거 같아요.
  private static StringBuilder makeBattleMessage(int battleNum, String message) {
    StringBuilder sb = new StringBuilder();
    sb.append("Battle ")
      .append(battleNum)
      .append(": ")
      .append(message)
      .append('\n');
    return sb;
  }

  private static int calcGrade(int[] unitScores, String[] unitCounts) {
    int sum = 0;
    for(int i = 0; i < unitScores.length; i++) {
      int unitScore = unitScores[i];
      int unitCount = Integer.parseInt(unitCounts[i]);

      sum += unitCount * unitScore;
    }
    
    return sum;
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