import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static int FIRST_BLACK = 0, FIRST_WHITE = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String boardInfo[] = br.readLine().split(" ");
        int N = Integer.parseInt(boardInfo[0]), M = Integer.parseInt(boardInfo[1]);
        String boardColor[] = new String[N];

        for(int i = 0; i < N; i++){
            boardColor[i] = br.readLine();
        }

        int lineLen = N - 7, rowLen = M - 7;
        String checkBoard;
        int resultPaint = 64, minPaint;
        int nowPaint[], needPaint[] = new int[2];
        
        for(int l = 0; l < lineLen; l++){
            for(int r = 0; r < rowLen; r++){
                needPaint[FIRST_BLACK] = needPaint[FIRST_WHITE] = 0;
                for(int i = 0; i < 8; i += 2){
                    checkBoard = boardColor[l + i].substring(r, r + 8) + boardColor[l + i + 1].substring(r, r + 8);
                    nowPaint = checkChessPaint(checkBoard);
                    //// 0, 1 대신 FIRST_BLACK FIRST_WHITE 상수로 표현했다면 더 가독성 좋았을 것 같아요!
                    //// -> 피드백 감사해요! 변경해봤어요
                    needPaint[FIRST_BLACK] += nowPaint[FIRST_BLACK];
                    needPaint[FIRST_WHITE] += nowPaint[FIRST_WHITE];
                }
                minPaint = Math.min(needPaint[FIRST_BLACK], needPaint[FIRST_WHITE]);
                if(resultPaint > minPaint) resultPaint = minPaint;
                if(resultPaint == 0){
                    System.out.print(resultPaint);
                    return;
                }
            }
        }

        System.out.print(resultPaint);
    }

    static int[] checkChessPaint(String board){
        //// 오 한줄마다 비교하는 방법도 있네요
        String firstBlackPattern = "BWBWBWBWWBWBWBWB";
        String firstWhitePattern = "WBWBWBWBBWBWBWBW";
        int paintCnt[] = new int[2];

        for(int i = 0; i < 16; i++){
            if(board.charAt(i) != firstBlackPattern.charAt(i)) paintCnt[FIRST_BLACK]++;
            if(board.charAt(i) != firstWhitePattern.charAt(i)) paintCnt[FIRST_WHITE]++;
        }

        return paintCnt;
    }
}