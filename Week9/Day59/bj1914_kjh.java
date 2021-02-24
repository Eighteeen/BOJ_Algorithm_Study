import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
  static StringBuilder movesInfo;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int DISCS = Integer.parseInt(br.readLine());
    
    System.out.println(getLeastMoveCount(DISCS));

    if (DISCS > 20) return;

    movesInfo = new StringBuilder();
    moveDiscs(DISCS, 1, 2, 3);
    
    System.out.print(movesInfo);
  }

  static BigInteger getLeastMoveCount(int discs) {
    return new BigInteger("2").pow(discs).subtract(BigInteger.ONE);
  }

  static void moveDiscs(int amount, int from, int tmp, int to) {
    if (amount == 1) {
      movesInfo.append(from).append(' ').append(to).append('\n');
      return;
    } 

    moveDiscs(amount - 1, from, to, tmp);
    movesInfo.append(from).append(' ').append(to).append('\n');
    moveDiscs(amount - 1, tmp, from, to);
  }
}