import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
    ////무난하게 잘 짜신거 같아요.
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
        //// -> 꿀팁 감사합니다!
        //// Math.max 배워갑니당.. 전 삼항연산자로 했음 ㅎㅎ; :22 오 이렇게 해도 되는군요.
        cntAll -= Math.max(Arrays.stream(lineSum).max().getAsInt(), Arrays.stream(rowSum).max().getAsInt());

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
