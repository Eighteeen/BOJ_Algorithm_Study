import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    //// 로직을 완전히 이해할 수 없었지만 전반적으로 깔끔한 것 같습니다!
    /* -> 로직을 간단하게 설명드리겠습니다.

        예) h = 4 일 때 ball과 node idx의 위치는 아래와 같습니다.
        ball :  1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16
        idx :   0   8   4   12  2   10  6   4   1   9   5   13  3   11  7   15
        위 예시와 아래 로직 설명을 함께 보시면 이해가 빠르게 될 겁니다.

        1. getNodeIdxAfterLoadIntoBinaryTree 메소드에서 numToLoad를 가장 마지막에 적재할 만큼만 남겨둡니다. (반복 절차 없애기)
        2. getNodeIdxAfterLoad 메소드에서 halfNumOfDepthNode와 numToLoad을 비교합니다.
            2-1. halfNumOfDepthNode < numToLoad 라면 -> numOfDepthNode에서 왼쪽 노드 적재 이후입니다. (현재 단계만 생각했을 때)
                a. numToLoad이 다시 halfNumOfDepthNode 내에서만 적재할 수 있을 만큼 설정해줍니다.
                b. 왼쪽 노드 적재가 끝났으므로 다음 적재할 오른쪽 노드로 firstIdx를 높여줍니다.
                    이때, loopStep로 호출 단계에서부터의 node Idx를 계산해줍니다.
        3. 재귀적으로 단계를 줄여갑니다.
        4. numToLoad가 1이라면 적재해야 합니다. 이때의 firstIdx가 적재해야할 idx입니다.
     */
    //// 깔끔해요
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
