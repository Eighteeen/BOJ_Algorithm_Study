import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] treeBallInfo = br.readLine().split(" ");
        int n = Integer.parseInt(treeBallInfo[0]);
        int h = Integer.parseInt(treeBallInfo[1]);

        System.out.println(getNodeIdxAfterLoadIntoBinaryTree(n, h));
        br.close();
    }

    static int getNodeIdxAfterLoadIntoBinaryTree(int numToLoad, int depth) {
        int numOfSibling = (int) Math.pow(2, depth);
        numToLoad %= numOfSibling;
        if (numToLoad == 0) numToLoad = numOfSibling;

        return getNodeIdxAfterLoad(numToLoad, numOfSibling, 0, 0);
    }

    static int getNodeIdxAfterLoad(int numToLoad, int numOfSibling, int firstIdx, int loopStep) {
        if (numToLoad == 1) return firstIdx;
        
        int halfnumOfSibling = numOfSibling / 2;
        if (halfnumOfSibling < numToLoad) {
            numToLoad -= halfnumOfSibling;
            firstIdx += (int) Math.pow(2, loopStep);
        }
        
        return getNodeIdxAfterLoad(numToLoad, halfnumOfSibling, firstIdx, loopStep + 1);
    }
}