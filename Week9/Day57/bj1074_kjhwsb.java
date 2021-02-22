import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] boardInfo = br.readLine().split(" ");
        int N = Integer.parseInt(boardInfo[0]);
        int row = Integer.parseInt(boardInfo[1]);
        int col = Integer.parseInt(boardInfo[2]);
        int twoPowerN = (int)Math.pow(2, N);

        System.out.print(sequenceOfVisit(0, twoPowerN * twoPowerN - 1, row, col));
        br.close();
    }

    static int sequenceOfVisit(int startRange, int endRange, int targetRow, int targetCol){
        if(startRange == endRange) return startRange;

        int total = endRange - startRange + 1;
        int halfIdx = (int)Math.sqrt(total) / 2;
        int quarter = total / 4;

        if(targetRow < halfIdx){
            endRange = startRange + quarter - 1;
            if(targetCol >= halfIdx){
                startRange += quarter;
                endRange += quarter;
                targetCol -= halfIdx;
            }
        }else{
            startRange += quarter * 2;
            targetRow -= halfIdx;
            if(targetCol < halfIdx){
                endRange = startRange + quarter - 1;
            }else{
                startRange += quarter;
                targetCol -= halfIdx;
            }
        }

        return sequenceOfVisit(startRange, endRange, targetRow, targetCol);
    }
}