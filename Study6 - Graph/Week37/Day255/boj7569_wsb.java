import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    static int fruitStoreMaxSize;
    static int fruitBoxCol, fruitBoxRow, fruitBoxHeight;
    static int[][][] fruitStatesArr;

    static Queue<Coordinate> bfsFruitCoordinateQueue;
    static int[] diffIdxsToAdjSpace;

    static final int RIGHT_DIFF_IDX = 1, LEFT_DIFF_IDX = -1;
    static final int RIPEN_NUM = 1, UNRIPEN_NUM = 0, EMPTY_NUM = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] boxInfo = br.readLine().split(" ");
        fruitBoxCol = Integer.parseInt(boxInfo[0]);
        fruitBoxRow = Integer.parseInt(boxInfo[1]);
        fruitBoxHeight = Integer.parseInt(boxInfo[2]);
        int fruitBoxMaxSize = fruitBoxRow * fruitBoxCol;
        fruitStoreMaxSize = fruitBoxMaxSize * fruitBoxHeight;

        fruitStatesArr = new int[fruitBoxHeight][fruitBoxRow][];
        bfsFruitCoordinateQueue = new LinkedList<>();
        diffIdxsToAdjSpace = new int[] {RIGHT_DIFF_IDX, fruitBoxCol, LEFT_DIFF_IDX, -fruitBoxCol, fruitBoxMaxSize, -fruitBoxMaxSize};

        for (int i = 0; i < fruitBoxHeight; i++) {
            for (int j = 0; j < fruitBoxRow; j++) {
                fruitStatesArr[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                for (int k = 0; k < fruitBoxCol; k++) {
                    if (fruitStatesArr[i][j][k] == RIPEN_NUM) {
                        bfsFruitCoordinateQueue.offer(Coordinate.threePointOf(i, j, k));
                    }
                }
            }
        } 

        System.out.println(getDateOfAllFruitsRipen());
        br.close();
    }

    static int getDateOfAllFruitsRipen() {
        setMinDatesForRipen();

        int dateForRipen = 0;
        for (int[][] fruitStates : fruitStatesArr) {
            for (int[] fruitState : fruitStates) {
                for (int fruitFlag : fruitState) {
                    dateForRipen = Math.max(dateForRipen, fruitFlag);
                    if (fruitFlag == UNRIPEN_NUM) return -1;
                }
            }
        }

        return dateForRipen - 1;
    }

    static void setMinDatesForRipen() {
        while (!bfsFruitCoordinateQueue.isEmpty()) {
            Coordinate fruitCoordinate = bfsFruitCoordinateQueue.poll();
            int fruitHeightIdx = fruitCoordinate.getZ();
            int fruitRowIdx = fruitCoordinate.getX();
            int fruitColIdx = fruitCoordinate.getY();

            //// 71~82, 84~95, 97~108 코드 중복이 좀 심한 것 같습니다. dz, dy, dx를 이용하시는건 어떨까요?
            for (int i = -1; i <= 1; i++) {
                if (i == 0) continue;
                if (i == -1 && fruitHeightIdx == 0) continue;
                if (i == 1 && fruitHeightIdx + 1 == fruitBoxHeight) continue;
                int adjFruitHeightIdx = fruitHeightIdx + i;

                int adjFruitFlag = fruitStatesArr[adjFruitHeightIdx][fruitRowIdx][fruitColIdx];
                if (adjFruitFlag == EMPTY_NUM || adjFruitFlag >= RIPEN_NUM) continue;

                bfsFruitCoordinateQueue.offer(Coordinate.threePointOf(adjFruitHeightIdx, fruitRowIdx, fruitColIdx));
                fruitStatesArr[adjFruitHeightIdx][fruitRowIdx][fruitColIdx] = fruitStatesArr[fruitHeightIdx][fruitRowIdx][fruitColIdx] + 1;
            }

            for (int i = -1; i <= 1; i++) {
                if (i == 0) continue;
                if (i == -1 && fruitRowIdx == 0) continue;
                if (i == 1 && fruitRowIdx + 1 == fruitBoxRow) continue;
                int adjFruitRowIdx = fruitRowIdx + i;

                int adjFruitFlag = fruitStatesArr[fruitHeightIdx][adjFruitRowIdx][fruitColIdx];
                if (adjFruitFlag == EMPTY_NUM || adjFruitFlag >= RIPEN_NUM) continue;

                bfsFruitCoordinateQueue.offer(Coordinate.threePointOf(fruitHeightIdx, adjFruitRowIdx, fruitColIdx));
                fruitStatesArr[fruitHeightIdx][adjFruitRowIdx][fruitColIdx] = fruitStatesArr[fruitHeightIdx][fruitRowIdx][fruitColIdx] + 1;
            }

            for (int i = -1; i <= 1; i++) {
                if (i == 0) continue;
                if (i == -1 && fruitColIdx == 0) continue;
                if (i == 1 && fruitColIdx + 1 == fruitBoxCol) continue;
                int adjFruitColIdx = fruitColIdx + i;

                int adjFruitFlag = fruitStatesArr[fruitHeightIdx][fruitRowIdx][adjFruitColIdx];
                if (adjFruitFlag == EMPTY_NUM || adjFruitFlag >= RIPEN_NUM) continue;

                bfsFruitCoordinateQueue.offer(Coordinate.threePointOf(fruitHeightIdx, fruitRowIdx, adjFruitColIdx));
                fruitStatesArr[fruitHeightIdx][fruitRowIdx][adjFruitColIdx] = fruitStatesArr[fruitHeightIdx][fruitRowIdx][fruitColIdx] + 1;
            }
        }
    }
}

class Coordinate {
    private int z, x, y;

    public static Coordinate threePointOf(int z, int x, int y) {
        return new Coordinate(z, x, y);
    }

    private Coordinate(int z, int x, int y) {
        this.z = z;
        this.x = x;
        this.y = y;
    }

    public int getZ() { return z; }
    public int getX() { return x; }
    public int getY() { return y; }
}