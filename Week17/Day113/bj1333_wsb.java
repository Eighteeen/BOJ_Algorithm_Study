import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 시험 잘보십셔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numStrArr = br.readLine().split(" ");
        int N = Integer.parseInt(numStrArr[0]);
        int L = Integer.parseInt(numStrArr[1]);
        int D = Integer.parseInt(numStrArr[2]);

        int untilTime = 0;
        for (int i = 0; i < N; i++) {
            untilTime += L;

            for (int j = 0; j < 5; j++) {
                if (untilTime % D == 0) {
                    System.out.println(untilTime);
                    return;
                }
                untilTime++;
            }
        }

        while (untilTime % D != 0) {
            untilTime++;
        }
        System.out.println(untilTime);

        br.close();
    }
}