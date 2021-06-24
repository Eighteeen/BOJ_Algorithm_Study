import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // < 문제가 쉽게 풀려서 풀이 추가 >
        // 비행기 수는 모든 국가 노드가 연결되었을 때 간선의 수이다.
        // n개의 노드가 모두 연결되어 있을려면 최소 n - 1개의 간선이 필요하다.
        // 이로인해 답은 가장 적은 비행기 종류는 모든 국가의 수 - 1
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] tripInfo = br.readLine().split(" ");
            int numOfCountry = Integer.parseInt(tripInfo[0]);
            int numOfPlane = Integer.parseInt(tripInfo[1]);

            for (int j = 0; j < numOfPlane; j++) br.skip(numOfPlane);
            sb.append(numOfCountry - 1).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}