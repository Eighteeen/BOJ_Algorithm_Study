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

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int형으로 값을 안집어넣고 String형으로 공백을 만들고 split를 사용해 배열에 집어넣는다.
        String bingoInfo[] = br.readLine().split(" ");
        //그리고 그 값들은 line,row에 집어넣고 배열을 만들어서 행의 크기와 열의 크기를 만든다
        int line = Integer.parseInt(bingoInfo[0]), row = Integer.parseInt(bingoInfo[1]);
        String nowLineInfo[];
        int lineSum[] = new int[line], rowSum[] = new int[row];
        int cntNow, cntAll = 0;

        
        for(int i = 0; i < line; i++){
            //3 4 2 9가 입력됐다고 치면 nowLineInfo{3,4,2,9}가 들어가게된다.
            nowLineInfo = br.readLine().split(" ");
            for(int j = 0; j < row; j++){
                // cntNow =cntChar(nowLineInfo[0], '9'); noewLineInfo[0] =3 cntNow = 0
                //lineSum[0] = 0, rowSum[0] = 0
                //cntAll=0
                
                cntNow = cntChar(nowLineInfo[j], '9');
                lineSum[i] += cntNow;
                rowSum[j] += cntNow;
                cntAll += cntNow;
            }
        }
        //열과 행값들을 배열로 만들어 열 배열,행 배열에 넣어서 가장 큰값들을 구하고 행과 열중 큰값을 cntAll값을뺀다
        List<Integer> lineSumList = IntStream.of(lineSum).boxed().colleoct(Collectors.toList());
        List<Integer> rowSumList = IntStream.of(rowSum).boxed().collect(Collectors.toList());
        int lineMax = Collections.max(lineSumList), rowMax = Collections.max(rowSumList);
        cntAll -= Math.max(lineMax, rowMax);

        System.out.print(cntAll);
    }

    
    //String형의 매개변수의 크기를 int형에 저장해서 반복문을 돌리고 String형을 문자하나하나씩 쪼개어  매개변수
    //ch와 같으면 cnt값을 증가
    static int cntChar(String str, char ch){
        int cnt = 0;
        int len = str.length();
        for(int i = 0; i < len; i++){
            if(str.charAt(i) == ch) cnt++;
        }
        return cnt;
    }
}



//문제실패:n*m크기만큼은 만들었는데 열과 행에 있는 9를 어떻게 구해서 어떻게 열에 9가 몇개 포함되는지 나타내서
// 가장 많이 포함되어있는 열 or 행을 삭제하는 법을 어떻게 접근해야될지 몰라서 실패하였습니다.
public class bj14647_jhw {

	public static void main(String[] args) {
		Scanner sc=  new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int a = 0;
		int nArr [] = new int[N];
		int mArr[] = new int[M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int number = sc.nextInt();
				int b = 0;
				nArr[i] = number;
				mArr[j] = number;
			
				if(Integer.toString(nArr[i]).contains("9")) {
					a++;
				}else if(Integer.toString(mArr[j]).contains("9")) {
					b++;
				}
				if(a>b) {
					if(Integer.toString(nArr[i]).contains("9")) {
						
					}
				}
				
			}
		}
	
		
		
	}
}
