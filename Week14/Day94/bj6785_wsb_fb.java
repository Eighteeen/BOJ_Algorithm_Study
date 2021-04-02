import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 진짜 항상 효율면에서는 태클걸 게 없네요
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
        if (isLargerSquare(gridSize, x, y)) return true;

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

    //// CrystalPostion과 CrystalInImage의 작명만 보고서는 둘이 어떤 점이 다른건지 쉽게 알기 어려워쓰요 ㅠ
    //// isInLargerSquare이라던가 하는식으로 더 구체적인 작명이었으면 좋았을 것 같아요!
    //// -> 오호 largerSquare이 문제 설명에도 있고 더 구체적이네요 반영했어요!
    static boolean isLargerSquare(int gridSize, int x, int y) {
        //// 오 piece 작명 되게 좋은거같아요
        int piece = gridSize / BASIC_GRID;
        int outLine = gridSize - piece;

        if (x >= piece && x < outLine && y < piece) return true;
        outLine -= piece;
        piece *= 2;
        if (x >= piece && x < outLine && y < piece) return true;

        return false;
    }
}