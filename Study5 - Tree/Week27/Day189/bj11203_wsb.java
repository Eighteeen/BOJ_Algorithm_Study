import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] treeInfo = br.readLine().split(" ");
        int H = Integer.parseInt(treeInfo[0]);

        int rootNum = (int) Math.pow(2, H) * 2 - 1;
        System.out.println(treeInfo.length == 1 ? rootNum : getAnswerOfStefanProblem(rootNum, treeInfo[1]));

        br.close();
    }

    static int getAnswerOfStefanProblem(int rootNum, String paths) {
        char prevPath = 'R';
        int pathNum = 1;

        for (char currentPath : paths.toCharArray()) {
            pathNum *= 2;

            if (currentPath != prevPath) {
                if (currentPath == 'L') pathNum--;
                else pathNum++;
            }

            rootNum -= pathNum;
            prevPath = currentPath;
        }

        return rootNum;
    }
}