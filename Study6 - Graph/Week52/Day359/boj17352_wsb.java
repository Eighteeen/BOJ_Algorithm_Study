import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔쓰
class Main {
    static List<Integer>[] adjacentIslands;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        adjacentIslands = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            adjacentIslands[i] = new ArrayList<>();
        }

        for (int i = 2; i < N; i++) {
            String[] bridgeInfo = br.readLine().split(" ");
            int island1 = Integer.parseInt(bridgeInfo[0]);
            int island2 = Integer.parseInt(bridgeInfo[1]);

            connectBridge(island1, island2);
        }

        visitConnectedIslands(1);
        sb.append("1 ");

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                sb.append(i);
                break;
            }
        }

        System.out.println(sb);
        br.close();
    }

    static void visitConnectedIslands(int island) {
        if (visited[island]) return;
        visited[island] = true;

        for (int adjacentIsland : adjacentIslands[island]) {
            visitConnectedIslands(adjacentIsland);
        }
    }

    static void connectBridge(int island1, int island2) {
        adjacentIslands[island1].add(island2);
        adjacentIslands[island2].add(island1);
    }
}