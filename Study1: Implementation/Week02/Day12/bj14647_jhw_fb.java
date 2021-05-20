package Day12;


import java.util.Scanner;

//문제실패- 모범답안
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


//제가 문제 실패한이유는 n*m크기만큼은 만들었는데 열과 행에 있는 9를 어떻게 구해서 어떻게 열에 9가 몇개 포함되는지 나타내서
// 가장 많이 포함되어있는 열 or 행을 삭제하는 법을 어떻게 접근해야될지 몰라서 실패하였습니다. 이런 이유였습니다.
//이 코드를 보고 열을 삭제하기보다는 열,행 배열을 따로 만들고 정수대신 문자열을 입력하고 분리해서 9가 포함되어있으면 +를 하고
//line이랑 row중 큰값을 cntAll에서 빼서 맞는 값이 나오게 한거 같습니다
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
  
        List<Integer> lineSumList = IntStream.of(lineSum).boxed().colleoct(Collectors.toList());
        List<Integer> rowSumList = IntStream.of(rowSum).boxed().collect(Collectors.toList());
        int lineMax = Collections.max(lineSumList), rowMax = Collections.max(rowSumList);
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