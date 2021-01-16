import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//// 까다로운 문제답게 함수 활용이 눈에 띄었습니다. 굿!
//// 구현이 아주 구체적이에요!! 함수활용을 잘하셔서 메인이 되게 
class Main {
  public static void main(String[] args) throws Exception {
    int startHour = Input.nextInt();
    int startMinute = Input.nextInt();
    int stopHour = Input.nextInt();
    int stopMinute = Input.nextInt();
    int targetNumber = Input.nextInt();

    int targetCount = 0;

    if (startHour == stopHour) {
      System.out.print(calcCountOfPortionHour(startHour, startMinute, stopMinute, targetNumber));
      return;
    }

    targetCount += calcCountOfPortionHour(startHour, startMinute, 59, targetNumber);
    targetCount += calcCountOfHours(startHour + 1, stopHour - 1, targetNumber);
    targetCount += calcCountOfPortionHour(stopHour, 0, stopMinute, targetNumber);
    
    System.out.print(targetCount);
  }

  private static boolean isAppear(int doubleDigits, int target) {
    //// 0일때 처리 따로 안 해도 밑에 return 식으로 똑같은 결과가 나와요
    if (target == 0) {
      return (doubleDigits < 10) || (doubleDigits % 10 == 0);
    }

    return (doubleDigits / 10 == target) || (doubleDigits % 10 == target);
  }

  private static int calcCountOfPortionHour(int hour, int minuteFrom, int minuteTo, int target) {
    if (isAppear(hour, target)) return minuteTo - minuteFrom + 1;
    
    int count = 0;
    for (int i = minuteFrom; i <= minuteTo; i++) {
      if (isAppear(i, target)) count++;
    }

    return count;
  }

  private static int calcCountOfHours(int hourFrom, int hourTo, int target) {
    int count = 0;
    //// 아 이렇게 미리 세어놓으면 되는 걸 왜 계속 연산했을까요..? 피드백 반영하면서 미리 세어놔야 겠어요!
    int countOfAnHour = calcCountOfAnHour(target);

    for (int i = hourFrom; i <= hourTo; i++) {
      if (isAppear(i, target)) {
        count += 60;
        continue;
      }
      count += countOfAnHour;
    }

    return count;
  }

  private static int calcCountOfAnHour(int target) {
      int count = 0;

      for (int i = 0; i <= 59; i++) {
          if (isAppear(i, target)) {
            count++;
          }
      }
      
      return count;
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
