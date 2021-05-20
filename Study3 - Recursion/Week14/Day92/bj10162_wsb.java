import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        if (T % 10 != 0) {
            System.out.println(-1);
            //// 여기다가 return 넣어주고 else문 없애서 더 깔끔하게 하는건 어때요
        } else {
            StringBuilder sb = new StringBuilder();
            for (int pressNum : minPressButtons(T)) sb.append(pressNum).append(" ");
            System.out.println(sb);
        }

        br.close();
    }

    //// 훨씬 깔끔하네요 ㄷㄷ 굳
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