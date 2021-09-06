import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끄미요
class Main {
    static int fruitBoxMaxSize;
    static int fruitBoxCol;
    static int[][] fruitStatesArr;

    //// bfsFruitIdxQueue를 잘못쓰신거죵? ㄱㅇㅇ,, : 22 일관성있게 오타낸거보면 변수 자동완성 쓰시는건가?
    //// -> 그러네요 코드 갈면서 잘못 쓰인 듯 하네요 ㅠ
    //// differenceIdxsToAdjSpace 는 diffIdxsToAdjSpace로 길이를 줄여도 괜찮을 것 같아요(difference약자가 diff입니다.)
    //// 밑에 다른 difference도 변경하는게 어떨까용?
    //// -> 좋네요! 팁 감사해요. 전체적으로 반영했어요.
    static Queue<Integer> bfsFruitIdxQueue;
    static int[] diffIdxsToAdjSpace;

    //// RIGHT_DIFFERENCE_IDX, LEFT_DIFFERENCE_IDX 변수명들이 너무 길어서 조금 보기 힘들었던 것 같아요..
    //// 변경하지 않아도 되지만 그냥 1, -1을 안쓰고 전역으로 변경한 이유가 있을까요?(궁금) 
    //// -> 1과 -1 이 명확하게 뭘 나타내는지 표시하고 싶었습니다!
    static final int RIGHT_DIFF_IDX = 1, LEFT_DIFF_IDX = -1;
    static final int RIPEN_NUM = 1, UNRIPEN_NUM = 0, EMPTY_NUM = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] boxInfo = br.readLine().split(" ");
        fruitBoxCol = Integer.parseInt(boxInfo[0]);
        int fruitBoxRow = Integer.parseInt(boxInfo[1]);
        fruitBoxMaxSize = fruitBoxRow * fruitBoxCol;

        fruitStatesArr = new int[fruitBoxRow][];
        bfsFruitIdxQueue = new LinkedList<>();
        diffIdxsToAdjSpace = new int[] {RIGHT_DIFF_IDX, fruitBoxCol, LEFT_DIFF_IDX, -fruitBoxCol};

        int idx = 0;
        for (int i = 0; i < fruitBoxRow; i++) {
            fruitStatesArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < fruitBoxCol; j++) {
                if (fruitStatesArr[i][j] == RIPEN_NUM) {
                    bfsFruitIdxQueue.offer(idx);
                }
                idx++;
            }
        }

        System.out.println(getDateOfAllFruitsRipen());
        br.close();
    }

    static int getDateOfAllFruitsRipen() {
        setMinDatesForRipen();

        int dateForRipen = 0;
        for (int[] fruitStates : fruitStatesArr) {
            for (int fruitState : fruitStates) {
                dateForRipen = Math.max(dateForRipen, fruitState);
                if (fruitState == UNRIPEN_NUM) return -1;
            }
        }

        return dateForRipen - 1;
    }

    static void setMinDatesForRipen() {
        while (!bfsFruitIdxQueue.isEmpty()) {
            int fruitIdx = bfsFruitIdxQueue.poll();
            int fruitRowIdx = fruitIdx / fruitBoxCol, fruitColIdx = fruitIdx % fruitBoxCol;

            for (int diffIdx : diffIdxsToAdjSpace) {
                if (!isInFruitBoxSpace(fruitIdx, diffIdx)) continue;
                int adjFruitIdx = fruitIdx + diffIdx;

                int adjFruitRowIdx = adjFruitIdx / fruitBoxCol, adjFruitColIdx = adjFruitIdx % fruitBoxCol;
                int adjFruitFlag = fruitStatesArr[adjFruitRowIdx][adjFruitColIdx];
                if (adjFruitFlag == EMPTY_NUM || adjFruitFlag >= RIPEN_NUM) continue;

                bfsFruitIdxQueue.offer(adjFruitIdx);
                fruitStatesArr[adjFruitRowIdx][adjFruitColIdx] = fruitStatesArr[fruitRowIdx][fruitColIdx] + 1;
            }
        }
    }

    static boolean isInFruitBoxSpace(int fruitSpaceIdx, int diffIdx) {
        if (diffIdx == RIGHT_DIFF_IDX && fruitSpaceIdx % fruitBoxCol == fruitBoxCol - 1) return false;
        if (diffIdx == LEFT_DIFF_IDX && fruitSpaceIdx % fruitBoxCol == 0) return false;

        int nextFruitSpaceIdx = fruitSpaceIdx + diffIdx;
        return nextFruitSpaceIdx >= 0 && nextFruitSpaceIdx < fruitBoxMaxSize;
    }
}