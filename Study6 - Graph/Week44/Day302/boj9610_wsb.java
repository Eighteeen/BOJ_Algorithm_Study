import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔 :2
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] coordinatesCounts = new int[5];
        final int AXIS = 0, Q1 = 1, Q2 = 2, Q3 = 3, Q4 = 4;

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] coordinates = br.readLine().split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);

            if (x == 0 || y == 0) coordinatesCounts[AXIS]++;
            else if (x > 0) {
                if (y > 0) coordinatesCounts[Q1]++;
                else coordinatesCounts[Q4]++;
            } else {
                if (y > 0) coordinatesCounts[Q2]++;
                else coordinatesCounts[Q3]++;
            }
        }

        sb.append(String.format("Q1: %d\n", coordinatesCounts[Q1]))
            .append(String.format("Q2: %d\n", coordinatesCounts[Q2]))
            .append(String.format("Q3: %d\n", coordinatesCounts[Q3]))
            .append(String.format("Q4: %d\n", coordinatesCounts[Q4]))
            .append(String.format("AXIS: %d\n", coordinatesCounts[AXIS]))

        System.out.print(sb);
        br.close();
    }
}