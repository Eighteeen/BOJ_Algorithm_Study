import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static StringBuilder[] treeInfos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int K = Integer.parseInt(br.readLine());
        int[] visitBuildings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        treeInfos = new StringBuilder[K];
        for (int i = 0; i < K; i++) {
            treeInfos[i] = new StringBuilder();
        }

        setTreeInfo(visitBuildings, 0);

        Arrays.stream(treeInfos).forEach(treeInfo -> sb.append(treeInfo).append("\n"));
        System.out.print(sb);
        br.close();
    }

    //// 깔끔합니다
    static void setTreeInfo(int[] nodes, int depth) {
        int len = nodes.length;
        if (len == 1) {
            treeInfos[depth].append(nodes[0]).append(" ");
            return;
        }

        int root = len / 2;
        setTreeInfo(Arrays.copyOf(nodes, root), depth + 1);
        setTreeInfo(Arrays.copyOfRange(nodes, root, root + 1), depth);
        setTreeInfo(Arrays.copyOfRange(nodes, root + 1, len), depth + 1);
    }
}