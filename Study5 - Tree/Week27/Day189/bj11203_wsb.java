import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] treeInfo = br.readLine().split(" ");
        int H = Integer.parseInt(treeInfo[0]);

        int rootNum = (int) Math.pow(2, H) * 2 - 1;
        System.out.println(treeInfo.length == 1 ? rootNum : getAnswerOfStefanProblem(rootNum, treeInfo[1]));

        br.close();
    }

    //// 저는 루트를 1로 취급하고 나중에 전체크기에서 빼는 방식을 사용했는데
    //// 아래같이 루트를 그대로 제일 제일 큰 수로 두고 거슬러 올라가는 로직의 코드를 보니 흥미롭네요 : 22
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