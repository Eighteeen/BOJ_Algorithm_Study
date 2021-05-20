import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    //// 1. 5로 나눠떨어지면 출력하고 리턴
    //// 2. 5로 나눈 몫이 2 이상이면 5로 나눈 몫 - 2 만큼 봉지 챙기기
        //// 이때 -2를 하는 이유는 10~14kg은 남겨두고 싶어서인 것 같음
    //// 3. 3으로 나눠떨어지면 출력하고 리턴
    //// 4. 5kg 짜리 봉지 하나 담아내보기
    //// 5. 3으로 나눠떨어지면 출력하고 리턴
    //// 5-1. 만약 8kg면 두개로 치고 출력
    //// 5-2. 이도저도 아니면 -1

    //// 우선 규칙을 찾아낸 집념에 박수드립니다.. 대단하십니다 정말
    //// 위와 같이 풀어 정리해봤는데도 워낙 복잡하다보니 어떤 원리로 걸러내지는지는 잘 모르겠네요.
    //// 일단 대강 짜고, 반례 하나씩 찾아서 기워붙인 느낌입니다. 가독성면에서는 좀 아쉬운 것 같아요.
    
    //// -> 저희도 가독성면에서 많이 아쉽다고 느끼고 있었습니다 ㅜㅜ 아래는 저희가 의도한 해결방법 풀이입니다.

    //// -> 3과 5의 최소공배수 15kg 이상의 무게라면 5로 먼저 나누고
    //// -> 그럼 남은 무게 중 나올 수 있는 경우의 수는 11kg ~ 14kg 입니다.
    //// -> 남은 무게가 3으로 나눠지면 (12kg) 출력합니다.
    //// -> 12kg이 아니라면 5kg 봉지로 담아봅니다. (이제 남은 무게 중 나올 수 있는 경우의 수는 3kg ~ 9kg 입니다.)
    //// -> 남은 무게가 3으로 나눠지면 출력합니다.
    //// -> 그 아래의 무게들 중 5 + 3인 8을 조건으로 따로 두었습니다.
    //// -> 모두 안 되면 -1

    //// -> 저희가 처음에 최소공배수 얘기가 나오고 거기에 집중하고 loop를 안 쓰고 할 수 있다는 것에 집중해서 가독성을 많이 놓친 거 같아요 ㅠㅠ
    //// -> kjh 코드를 보면 loop를 쓰셨는데 가독성도 좋고 효율성도 별 차이가 안 나서 저희가 많이 허탈했습니다..
    //// -> 자세한 피드백 감사합니다! 다시 페어프로그래밍을 통해 알고리즘을 짜보았습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sugarKg = Integer.parseInt(br.readLine());
        int leftSugar, bagCnt;
        int onlyThreeBags[] = {0, 3, 6, 9, 12};

        for(int i = sugarKg / 5; i >= 0; i--){
            leftSugar = sugarKg - (5 * i);
            bagCnt = Arrays.binarySearch(onlyThreeBags, leftSugar);
            if(bagCnt > -1){
                System.out.print(bagCnt + i);
                return;
            }
        }
        System.out.print(-1);
    }
}