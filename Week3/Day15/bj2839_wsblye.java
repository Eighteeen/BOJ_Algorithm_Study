import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    //// 위와 같이 풀어 정리해봤는데도 워낙 복잡하다보니 어떤 원리로 걸러내지는지는 잘 모르겠네요
    
    //// 일단 대강 짜고, 반례 하나씩 찾아서 기워붙인 느낌입니다. 가독성면에서는 아쉬운 것 같아요.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sugarKg = Integer.parseInt(br.readLine());
        int bagDCnt = 0;

        if(divisionFive(sugarKg)) return;

        if(sugarKg / 5 >= 2){
            bagDCnt = sugarKg / 5;
            bagDCnt -= 2;
            sugarKg -= bagDCnt * 5;
        }

        if(divisionThree(sugarKg, bagDCnt)) return;
        
        sugarKg -= 5;
        bagDCnt++;
        
        if(divisionThree(sugarKg, bagDCnt)) return;
        else if(sugarKg == 8) System.out.print(bagDCnt += 2);
        else System.out.print(-1);
    }

    static boolean divisionFive(int num){
        if(num % 5 == 0){
            System.out.print(num / 5);
            return true;
        }
        return false;
    }

    static boolean divisionThree(int num, int cnt){
        if(num % 3 == 0){
            System.out.print(cnt += num / 3);
            return true;
        }
        return false;
    }
}