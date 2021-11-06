import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int[] answers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        answers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            answers[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(getMaxMeetNum());
        br.close();
    }

    static int getMaxMeetNum() {
        int maxMeetNum = 0;
        int maxMeet = 0;

        int len = answers.length;
        for (int i = 1; i < len; i++) {
            boolean[] isVisited = new boolean[len];
            int current = i;
            int currentMeet = 0;

            while (!isVisited[current]) {
                isVisited[current] = true;
                current = answers[current];
                currentMeet++;
            }

            if (maxMeet < currentMeet) {
                maxMeet = currentMeet;
                maxMeetNum = i;
            }
        }

        return maxMeetNum;
    }
}