import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) throws Exception {
    StringBuilder sb = new StringBuilder();
    while (true) {
      String code = inputString();

      if (code.equals("END")) break;
      sb.append(decrypt(code)).append("\n");
    }
    System.out.print(sb);

    br.close();
  }

  private static StringBuilder decrypt(String code) {
    StringBuilder sb = new StringBuilder();

    int lastIndex = code.length() - 1;
    for (int i = lastIndex; i >= 0; i--) {
      sb.append(code.charAt(i));
    }
    return sb;
  }

  private static String inputString() {
    try {
      return br.readLine();
    } catch(Exception e) { }
    
    return null;
  }
}