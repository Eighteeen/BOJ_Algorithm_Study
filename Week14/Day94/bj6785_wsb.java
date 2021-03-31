import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static final int BASIC_GRID = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int[] examineInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean isCrystal = isCrystalInImage((int)Math.pow(BASIC_GRID, examineInfo[0]), examineInfo[1], examineInfo[2]);
            sb.append(isCrystal ? "crystal\n" : "empty\n");
        }

        System.out.print(sb);
        br.close();
    }

    static boolean isCrystalInImage(int gridSize, int x, int y) {
        if (isCrystalPosition(gridSize, x, y)) return true;

        int piece = gridSize / BASIC_GRID;
        int largerLine = piece * 2;
        int outLine = gridSize - piece;
        if (x >= piece && x < outLine && y < largerLine) {
            if (x < largerLine) return isCrystalInImage(piece, x - piece, y - piece);
            return isCrystalInImage(piece, x - piece * 3, y - piece);
        }
        outLine -= piece;
        if (x >= largerLine && x < outLine && y < outLine) return isCrystalInImage(piece, x - largerLine, y - largerLine);

        return false;
    }

    static boolean isCrystalPosition(int gridSize, int x, int y) {
        int piece = gridSize / BASIC_GRID;
        int outLine = gridSize - piece;

        if (x >= piece && x < outLine && y < piece) return true;
        outLine -= piece;
        piece *= 2;
        if (x >= piece && x < outLine && y < piece) return true;

        return false;
    }
}