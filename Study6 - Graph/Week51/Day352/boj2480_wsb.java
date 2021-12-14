import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] diceInfo = br.readLine().split(" ");
        int diceResult1 = Integer.parseInt(diceInfo[0]);
        int diceResult2 = Integer.parseInt(diceInfo[1]);
        int diceResult3 = Integer.parseInt(diceInfo[2]);

        int prizeMoney = 0;
        if (diceResult1 == diceResult2 && diceResult2 == diceResult3) {
            prizeMoney = 10000 + diceResult1 * 1000;
        } else if (diceResult1 == diceResult2 || diceResult1 == diceResult3) {
            prizeMoney = 1000 + diceResult1 * 100;
        } else if (diceResult2 == diceResult3) {
            prizeMoney = 1000 + diceResult2 * 100;
        } else {
            prizeMoney = Math.max(diceResult1, Math.max(diceResult2, diceResult3)) * 100;
        }

        System.out.println(prizeMoney);
        br.close();
    }
}