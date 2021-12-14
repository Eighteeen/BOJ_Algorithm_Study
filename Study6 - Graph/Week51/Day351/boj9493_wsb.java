import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int ONE_HOUR_SECONDS = 3600;
        final int ONE_MINUTE_SECONDS = 60;

        String input;
        while (!(input = br.readLine()).equals("0 0 0")) {
            String[] testInfo = input.split(" ");
            int M = Integer.parseInt(testInfo[0]);
            int A = Integer.parseInt(testInfo[1]);
            int B = Integer.parseInt(testInfo[2]);

            double trainTime = M / (double) A * ONE_HOUR_SECONDS;
            double planeTime = M / (double) B * ONE_HOUR_SECONDS;
            int differenceTime = (int) trainTime - planeTime;

            int differenceHours = differenceTime / ONE_HOUR_SECONDS;
            int differenceMinutes = differenceTime % ONE_HOUR_SECONDS / ONE_MINUTE_SECONDS;
            int differenceSeconds = differenceTime % ONE_HOUR_SECONDS % ONE_MINUTE_SECONDS;
            sb.append(String.format("%d:%02d:%02d\n", differenceHours, differenceMinutes, differenceSeconds));
        }

        System.out.print(sb);
        br.close();
    }
}