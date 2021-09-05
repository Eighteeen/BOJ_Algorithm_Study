import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int fruitBoxMaxSize;
    static int fruitBoxCol;
    static int[][] fruitStatesArr;

    //// bfsFruitIdxQueue를 잘못쓰신거죵? ㄱㅇㅇ,,
    //// differenceIdxsToAdjSpace 는 diffIdxsToAdjSpace로 길이를 줄여도 괜찮을 것 같아요(difference약자가 diff입니다.)
    //// 밑에 다른 difference도 변경하는게 어떨까용?
    static Queue<Integer> bfsFruitIdxueue;
    static int[] differenceIdxsToAdjSpace;

    //// RIGHT_DIFFERENCE_IDX, LEFT_DIFFERENCE_IDX 변수명들이 너무 길어서 조금 보기 힘들었던 것 같아요..
    //// 변경하지 않아도 되지만 그냥 1, -1을 안쓰고 전역으로 변경한 이유가 있을까요?(궁금)
    static final int RIGHT_DIFFERENCE_IDX = 1, LEFT_DIFFERENCE_IDX = -1;
    static final int RIPEN_NUM = 1, UNRIPEN_NUM = 0, EMPTY_NUM = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] boxInfo = br.readLine().split(" ");
        fruitBoxCol = Integer.parseInt(boxInfo[0]);
        int fruitBoxRow = Integer.parseInt(boxInfo[1]);
        fruitBoxMaxSize = fruitBoxRow * fruitBoxCol;

        fruitStatesArr = new int[fruitBoxRow][];
        bfsFruitIdxueue = new LinkedList<>();
        differenceIdxsToAdjSpace = new int[] {RIGHT_DIFFERENCE_IDX, fruitBoxCol, LEFT_DIFFERENCE_IDX, -fruitBoxCol};

        int idx = 0;
        for (int i = 0; i < fruitBoxRow; i++) {
            fruitStatesArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < fruitBoxCol; j++) {
                if (fruitStatesArr[i][j] == RIPEN_NUM) {
                    bfsFruitIdxueue.offer(idx);
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
        while (!bfsFruitIdxueue.isEmpty()) {
            int fruitIdx = bfsFruitIdxueue.poll();
            int fruitRowIdx = fruitIdx / fruitBoxCol, fruitColIdx = fruitIdx % fruitBoxCol;

            for (int differenceIdx : differenceIdxsToAdjSpace) {
                if (!isInFruitBoxSpace(fruitIdx, differenceIdx)) continue;
                int adjFruitIdx = fruitIdx + differenceIdx;

                int adjFruitRowIdx = adjFruitIdx / fruitBoxCol, adjFruitColIdx = adjFruitIdx % fruitBoxCol;
                int adjFruitFlag = fruitStatesArr[adjFruitRowIdx][adjFruitColIdx];
                if (adjFruitFlag == EMPTY_NUM || adjFruitFlag >= RIPEN_NUM) continue;

                bfsFruitIdxueue.offer(adjFruitIdx);
                fruitStatesArr[adjFruitRowIdx][adjFruitColIdx] = fruitStatesArr[fruitRowIdx][fruitColIdx] + 1;
            }
        }
    }

    static boolean isInFruitBoxSpace(int fruitSpaceIdx, int differenceIdx) {
        if (differenceIdx == RIGHT_DIFFERENCE_IDX && fruitSpaceIdx % fruitBoxCol == fruitBoxCol - 1) return false;
        if (differenceIdx == LEFT_DIFFERENCE_IDX && fruitSpaceIdx % fruitBoxCol == 0) return false;

        int nextFruitSpaceIdx = fruitSpaceIdx + differenceIdx;
        return nextFruitSpaceIdx >= 0 && nextFruitSpaceIdx < fruitBoxMaxSize;
    }
}