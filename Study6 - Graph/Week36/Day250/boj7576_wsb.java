import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int fruitBoxMaxSize;
    static int fruitBoxCol;
    static int[][] fruitStatesArr;

    static Queue<Integer> bfsFruitIdxueue;
    static int[] differenceIdxsToAdjSpace;

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

        int idx = -1;
        for (int i = 0; i < fruitBoxRow; i++) {
            fruitStatesArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < fruitBoxCol; j++) {
                idx++;
                if (fruitStatesArr[i][j] == RIPEN_NUM) {
                    bfsFruitIdxueue.offer(idx);
                }
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