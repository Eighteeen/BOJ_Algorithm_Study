import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    static final int LEN = 5;
    static final int MAKE_LEN = 6;
    static final int dLen = 4;
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    static int[][] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nums = new int[LEN][];
        for (int i = 0; i < LEN; i++) {
            nums[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(getMoveMakeNumSet().size());
        br.close();
    }

    static Set<Integer> getMoveMakeNumSet() {
        Set<Integer> nums = new HashSet<>();

        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                nums.addAll(getMoveMakeNumSet(MAKE_LEN, Coordinate.twoPointOf(i, j)));
            }
        }

        return nums;
    }

    //// Str->Integer가 필수적인 문제는 아닌 거 같아요!
    static Set<Integer> getMoveMakeNumSet(int movement, Coordinate coordinate) {
        return getMoveMakeNumStrSet(movement, coordinate).stream().map(Integer::valueOf).collect(Collectors.toSet());
    }

    static Set<String> getMoveMakeNumStrSet(int movement, Coordinate coordinate) {
        Set<String> numStrSet = new HashSet<>();

        int x = coordinate.getX(), y = coordinate.getY();
        String numStr = String.valueOf(nums[x][y]);
        if (movement == 1) {
            numStrSet.add(numStr);
            return numStrSet;
        }

        movement--;
        Set<String> prevNumStrSet = new HashSet<>();
        for (int i = 0 ; i < dLen; i++) {
            int nx = x + dx[i], ny = y + dy[i];
            if (isOutOfSize(LEN, nx) || isOutOfSize(LEN, ny)) continue;
            Coordinate nextCoordinate = Coordinate.twoPointOf(nx, ny);
            prevNumStrSet.addAll(getMoveMakeNumStrSet(movement, nextCoordinate));
        }

        for (String prevNumStr : prevNumStrSet) {
            numStrSet.add(numStr + prevNumStr);
        }

        return numStrSet;
    }

    static boolean isOutOfSize(int size, int point) {
        return (point < 0 || point >= size);
    }
}

class Coordinate {
    private int x, y;

    public static Coordinate twoPointOf(int x, int y) {
        return new Coordinate(x, y);
    }

    private Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        Coordinate other = (Coordinate) obj;
        if (x != other.x) return false;
        if (y != other.y) return false;
        return true;
    }
}