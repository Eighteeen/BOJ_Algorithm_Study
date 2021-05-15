import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            sb.append(leastDividedNumConsistingOf0and1(Integer.parseInt(br.readLine()))).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static long leastDividedNumConsistingOf0and1(int dividingNum) {
        long bitmask = 1, dividedNum = 1;
        
        while (dividedNum % dividingNum != 0) {
            dividedNum = Long.parseLong(Long.toBinaryString(++bitmask));
        }

        return dividedNum;
    }
}