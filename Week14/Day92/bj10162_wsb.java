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
            for (int buttonPress : minButtonsPress(T)) sb.append(buttonPress).append(" ");
            System.out.println(sb);
        }

        br.close();
    }

    static int[] minButtonsPress(int cookingTime) {
        int[] buttonsPress = new int[3];
        
        buttonsPress[0] = cookingTime / 300;
        cookingTime -= buttonsPress[0] * 300;

        buttonsPress[1] = cookingTime / 60;
        cookingTime -= buttonsPress[1] * 60;

        buttonsPress[2] = cookingTime / 10;

        return buttonsPress;
    }
}