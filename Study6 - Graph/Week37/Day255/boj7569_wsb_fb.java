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
    static int[] dz, dx, dy;

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

        dz = new int[]{1, 0, 0, 0, 0, -1};
        dx = new int[]{0, -1, 0, 1, 0, 0};
        dy = new int[]{0, 0, 1, 0, -1, 0};
        
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
            //// : 22
            //// -> z부분까지 d arr를 이용한다고 생각을 못 했네요. 반영했어요!

            for (int i = 0; i < 6; i++) {
                int adjFruitHeightIdx = fruitHeightIdx + dz[i];
                int adjFruitRowIdx = fruitRowIdx + dx[i];
                int adjFruitColIdx = fruitColIdx + dy[i];

                if (adjFruitHeightIdx < 0 || adjFruitHeightIdx >= fruitBoxHeight
                    || adjFruitRowIdx < 0 || adjFruitRowIdx >= fruitBoxRow
                    || adjFruitColIdx < 0 || adjFruitColIdx >= fruitBoxCol) continue;

                int adjFruitFlag = fruitStatesArr[adjFruitHeightIdx][adjFruitRowIdx][adjFruitColIdx];
                if (adjFruitFlag == EMPTY_NUM || adjFruitFlag >= RIPEN_NUM) continue;

                bfsFruitCoordinateQueue.offer(Coordinate.threePointOf(adjFruitHeightIdx, adjFruitRowIdx, adjFruitColIdx));
                fruitStatesArr[adjFruitHeightIdx][adjFruitRowIdx][adjFruitColIdx] = fruitStatesArr[fruitHeightIdx][fruitRowIdx][fruitColIdx] + 1;
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