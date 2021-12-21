import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static boolean[] isConnectedBridges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        isConnectedBridges = new boolean[N + 1];

        for (int i = 2; i < N; i++) {
            String[] bridgeInfo = br.readLine().split(" ");
            int island1 = Integer.parseInt(bridgeInfo[0]);
            int island2 = Integer.parseInt(bridgeInfo[1]);

            setIsConnectedBridges(island1, island2);
        }

        for (int i = 1; i <= N; i++) {
            if (!isConnectedBridges[i]) {
                sb.append(String.format("%d ", i));
            }
        }

        System.out.println(sb);
        br.close();
    }

    static void setIsConnectedBridges(int island1, int island2) {
        if (isConnectedBridges[island1]) {
            isConnectedBridges[island2] = true;
        } else {
            isConnectedBridges[island1] = true;
        }
    }
}