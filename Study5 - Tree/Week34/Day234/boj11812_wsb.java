import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] treeQueryInfo = br.readLine().split(" ");
        int K = Integer.parseInt(treeQueryInfo[1]);
        int Q = Integer.parseInt(treeQueryInfo[2]);

        while (Q-- > 0) {
            String[] queryInfo = br.readLine().split(" ");
            long dataA = Long.parseLong(queryInfo[0]);
            long dataB = Long.parseLong(queryInfo[1]);

            sb.append(K == 1 ? Math.abs(dataA - dataB) : getDistanceOfKpowerTree(K, dataA, dataB)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static long getDistanceOfKpowerTree(int K, long dataA, long dataB) {
        long distance = 0;
        while (dataA != dataB) {
            if (dataA > dataB) dataA = getParentOfKpowerTree(K, dataA);
            else dataB = getParentOfKpowerTree(K, dataB);
            distance++;
        }
        return distance;
    }

    static long getParentOfKpowerTree(int K, long data) {
        long parent;
        parent = --data / K;
        if (data % K > 0) parent++;
        return parent;
    }
}