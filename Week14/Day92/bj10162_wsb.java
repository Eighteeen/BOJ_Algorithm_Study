import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        if (T % 10 != 0) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int pressNum : minPressButtons(T)) sb.append(pressNum).append(" ");
            System.out.println(sb);
        }

        br.close();
    }

    static int[] minPressButtons(int cookingTime) {
        int[] pressNums = new int[3];
        
        pressNums[0] = cookingTime / 300;
        cookingTime -= pressNums[0] * 300;

        pressNums[1] = cookingTime / 60;
        cookingTime -= pressNums[1] * 60;

        pressNums[2] = cookingTime / 10;

        return pressNums;
    }
}