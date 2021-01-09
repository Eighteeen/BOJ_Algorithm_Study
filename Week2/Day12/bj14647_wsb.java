import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String bingoInfo[] = br.readLine().split(" ");
        int line = Integer.parseInt(bingoInfo[0]), row = Integer.parseInt(bingoInfo[1]);
        String nowLineInfo[];
        int lineSum[] = new int[line], rowSum[] = new int[row];
        int cntNow, cntAll = 0;

        for(int i = 0; i < line; i++){
            nowLineInfo = br.readLine().split(" ");
            for(int j = 0; j < row; j++){
                cntNow = cntChar(nowLineInfo[j], '9');
                lineSum[i] += cntNow;
                rowSum[j] += cntNow;
                cntAll += cntNow;
            }
        }

        //// Arrays.stream(lineSum).max().getAsInt();
        //// 요렇게 하면 바로 최대값 구할 수 있어요
        List<Integer> lineSumList = IntStream.of(lineSum).boxed().collect(Collectors.toList());
        List<Integer> rowSumList = IntStream.of(rowSum).boxed().collect(Collectors.toList());
        int lineMax = Collections.max(lineSumList), rowMax = Collections.max(rowSumList);
        //// Math.max 배워갑니당.. 전 삼항연산자로 했음 ㅎㅎ;
        cntAll -= Math.max(lineMax, rowMax);

        System.out.print(cntAll);
    }

    static int cntChar(String str, char ch){
        int cnt = 0;
        int len = str.length();
        for(int i = 0; i < len; i++){
            if(str.charAt(i) == ch) cnt++;
        }
        return cnt;
    }
}