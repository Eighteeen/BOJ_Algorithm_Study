import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int[] parentDataArr;
    static int[] depthArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        parentDataArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parentDataArr[i] = Integer.parseInt(br.readLine());
        }

        depthArr = new int[n + 1];
        Arrays.fill(depthArr, -1);
        for (int i = 1; i <= n; i++) {
            setDepthArr(i);
            sb.append(depthArr[i]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void setDepthArr(int setIdx) {
        if (depthArr[setIdx] != -1) return;

        int parentData = parentDataArr[setIdx];
        if (parentData == -1) {
            depthArr[setIdx] = 0;
        } else {
            if (depthArr[parentData] == -1) setDepthArr(parentData);
            depthArr[setIdx] = depthArr[parentData] + 1;
        }
    }
}