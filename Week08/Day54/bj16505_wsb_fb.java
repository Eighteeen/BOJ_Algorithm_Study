import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    static List<StringBuilder> untilFlag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        untilFlag = new ArrayList<>();
        makeStarFlag(Integer.parseInt(br.readLine()), 0, 0);
        
        StringBuilder resultFlag = new StringBuilder();
        untilFlag.forEach(sb -> resultFlag.append(sb).append("\n"));
        System.out.print(resultFlag);

        br.close();
    }

    //// 이 문제도 그렇고 특히 이진수 변환문제에서도 느꼈던건데
    //// 재귀 호출을 중간에서 하면 좀 더 편하게 풀 수도 있는 문제인데
    //// '재귀함수가 뭔가요?'와 같은 불가피한 경우가 아니라면, 재귀 호출을 꼭 맨 마지막에 하려는 경향이 있으신 거 같아요. (마치 반복문 쓰듯이요. 처음으로 푼 문제 뺴고는 다 반복문으로 바꾸기 쉽게 푸셨거든요)
    //// 아마 재귀호출을 중간에 하거나 여러 번 하는 방식으로 활용하는데엔 익숙치 않으셔서 그런게 아닐까 싶습니다.
    //// 요 문제 같은 경우에도, 이 답안(https://www.acmicpc.net/source/26373698)처럼 재귀함수의 특성을 잘 살려서 쓰면 코드가 훨씬 간결하거든요 (저 코드는 저도 보고 감탄했습니다)
    //// 참고하셔서 재귀함수 특성 잘 이용해보려고 해보시면 좋을 것 같습니다! (피드백 반영은 안 하셔도 돼요! 이 코드가 효율성면에서 압도적이여서 새로 짜는건 좀 아까워요)
    //// -> 엄청 재귀적으로 바꾸지는 못 했지만 재귀함수 안에 루프라도 없애봤습니다!
    //// -> 아무래도 StringBuilder를 이용한 방법을 버리기는 아쉬워서 그 부분을 쓰면서 조금이라도 재귀적으로 바꿔보려고 노력해봤어요
    //// -> 예시까지 보여주시는 피드백 감사합니다!!
    static void makeStarFlag(int n, int idx, int space){
        if(n < 0) return;

        if(!untilFlag.isEmpty()){
            StringBuilder line = new StringBuilder(untilFlag.get(idx));
            line.append(" ".repeat(space)).append(untilFlag.get(idx));
            untilFlag.add(0, line);
        }else{
            untilFlag.add(new StringBuilder("*"));
        }

        if(space == 0){
            int lastIdx = untilFlag.size() - 1;
            makeStarFlag(--n, lastIdx, lastIdx);
        }else{
            makeStarFlag(n, idx, --space);
        }
    }
}