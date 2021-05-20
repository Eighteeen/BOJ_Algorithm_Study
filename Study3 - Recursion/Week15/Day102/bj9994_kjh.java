import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws Exception {
    final String ENCRYPTED = Input.nextLine();
    
    System.out.print(countEncryptionWay(ENCRYPTED));
  }

  static int countEncryptionWay(String encrypted) {
    String[] decrypted = decrypt(encrypted);
    if (decrypted[0].equals("") && decrypted[1].equals("")) return 0;

    //// decrypt 함수 내에서 체크를 한 후 똑같은 작업을 또 하게되는 점이 아쉽게 느껴집니다.
    int countIfCodeInLeft = countEncryptionWayFromDecryptedToEncrypted(decrypted[0], encrypted);
    int countIfCodeInRight = countEncryptionWayFromDecryptedToEncrypted(decrypted[1], encrypted);

    return countIfCodeInLeft * (1 + countEncryptionWay(decrypted[0])) + 
      countIfCodeInRight * (1 + countEncryptionWay(decrypted[1]));
  }

  //// 정말 최선의 함수 이름이었나요...? 매개변수도 다르고 매개변수 이름으로도 충분히 의미가 전달된다고 보여서 countEncryptionWay 오버로딩으로 사용해도 좋을 것 같습니다.
  static int countEncryptionWayFromDecryptedToEncrypted(String decrypted, String encrypted) {
    if (decrypted.equals("")) return 0;

    String firstShorten = decrypted.substring(1);
    String lastShorten = decrypted.substring(0, decrypted.length() - 1);

    int count = 0;
    count += encrypted.equals(firstShorten + decrypted) ? 1 : 0;
    count += encrypted.equals(lastShorten + decrypted) ? 1 : 0;
    count += encrypted.equals(decrypted + firstShorten) ? 1 : 0;
    count += encrypted.equals(decrypted + lastShorten) ? 1 : 0;

    return count;
  }
  
  static String[] decrypt(String encrypted) {
    if (encrypted.equals("")) return new String[] {"", ""};

    int decryptedLen = (encrypted.length() - 1) / 2 + 1;

    String left = encrypted.substring(0, decryptedLen);
    String right = encrypted.substring(encrypted.length() - decryptedLen);

    boolean ifCodeIsInLeft = countEncryptionWayFromDecryptedToEncrypted(left, encrypted) > 0;
    boolean ifCodeIsInRight = countEncryptionWayFromDecryptedToEncrypted(right, encrypted) > 0;

    String decrypted[] = new String[2];

    decrypted[0] = ifCodeIsInLeft ? left : "";
    decrypted[1] = ifCodeIsInRight ? right : "";
    if (left.equals(right)) {
      decrypted[1] = "";
    }

    return decrypted;
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