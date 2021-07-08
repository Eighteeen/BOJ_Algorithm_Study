import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    //// 로직을 이해하기 힘들었지만 전반적으로 깔끔한 것 같습니다!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] treeBallInfo = br.readLine().split(" ");
        int n = Integer.parseInt(treeBallInfo[0]);
        int h = Integer.parseInt(treeBallInfo[1]);

        System.out.println(getNodeIdxAfterLoadIntoBinaryTree(n, h));
        br.close();
    }

    static int getNodeIdxAfterLoadIntoBinaryTree(int numToLoad, int depth) {
        int numOfDepthNode = (int) Math.pow(2, depth);
        numToLoad %= numOfDepthNode;
        if (numToLoad == 0) numToLoad = numOfDepthNode;

        return getNodeIdxAfterLoad(numToLoad, numOfDepthNode, 0, 0);
    }

    static int getNodeIdxAfterLoad(int numToLoad, int numOfDepthNode, int firstIdx, int loopStep) {
        if (numToLoad == 1) return firstIdx;
        
        int halfNumOfDepthNode = numOfDepthNode / 2;
        if (halfNumOfDepthNode < numToLoad) {
            numToLoad -= halfNumOfDepthNode;
            firstIdx += (int) Math.pow(2, loopStep);
        }
        
        return getNodeIdxAfterLoad(numToLoad, halfNumOfDepthNode, firstIdx, loopStep + 1);
    }
}
