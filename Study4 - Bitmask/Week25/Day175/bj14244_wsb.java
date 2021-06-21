import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 요렇게 풀려다가 트리를 억지로라도 써봤는데 이게 훨 깔끔하네요 굳
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] treeInfo = br.readLine().split(" ");
        int n = Integer.parseInt(treeInfo[0]), m = Integer.parseInt(treeInfo[1]);

        int lastLineNode = n - m;
        for (int i = 0; i < lastLineNode; i++) {
            sb.append(i).append(" ").append(i + 1).append("\n");
        }

        for (int i = lastLineNode + 1; i < n; i++) {
            sb.append(lastLineNode).append(" ").append(i).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}