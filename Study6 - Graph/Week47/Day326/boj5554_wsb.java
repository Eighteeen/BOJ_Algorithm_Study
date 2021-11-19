import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int ONE_HOUR_MINUTES = 60;

        int totalSeconds = 0;
        for (int i = 0; i < 4; i++) {
            totalSeconds += Integer.parseInt(br.readLine());
        }

        sb.append(totalSeconds / ONE_HOUR_MINUTES)
            .append("\n")
            .append(totalSeconds % ONE_HOUR_MINUTES);
        System.out.println(sb);

        br.close();
    }
}