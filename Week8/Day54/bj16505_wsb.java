import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(printStarFlag(Integer.parseInt(br.readLine()), null));

        br.close();
    }

    //// 이 문제도 그렇고 특히 이진수 변환문제에서도 느꼈던건데
    //// 재귀 호출을 중간에서 하면 좀 더 편하게 풀 수도 있는 문제인데
    //// '재귀함수가 뭔가요?'와 같은 불가피한 경우가 아니라면, 재귀 호출을 꼭 맨 마지막에 하려는 경향이 있으신 거같아요. (마치 반복문 쓰듯이요. 처음으로 푼 문제 뺴고는 다 반복문으로 바꾸기 쉽게 푸셨거든요)
    //// 아마 재귀호출을 중간에 하거나 여러 번 하는 방식으로 활용하는데엔 익숙치 않으셔서 그런게 아닐까 싶습니다.
    //// 요 문제 같은 경우에도, 이 답안(https://www.acmicpc.net/source/26373698)처럼 재귀함수의 특성을 잘 살려서 쓰면 코드가 훨씬 간결하거든요 (저 코드는 저도 보고 감탄했습니다)
    //// 참고하셔서 재귀함수 특성 잘 이용해보려고 해보시면 좋을 것 같습니다!
    static StringBuilder printStarFlag(int n, List<StringBuilder> untilFlag){ // When calling this method, use null for List parameter 
        if(n < 0){
            StringBuilder resultFlag = new StringBuilder();
            untilFlag.forEach(sb -> resultFlag.append(sb).append("\n"));
            return resultFlag;
        }

        List<StringBuilder> currentFlag = new ArrayList<>();
        if(untilFlag == null) currentFlag.add(new StringBuilder("*"));
        else{
            int len = untilFlag.size();
            for(int i = 0; i < len; i++){
                StringBuilder line = new StringBuilder(untilFlag.get(i));
                currentFlag.add(line.append(" ".repeat(i)).append(untilFlag.get(i)));
            }
            currentFlag.addAll(untilFlag);
        }

        return printStarFlag(--n, currentFlag);
    }
}