import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//// 경우의 수를 생각해서 섬세하게 나눠주었지만 조건문이 많아 조금은 복잡해 보여요.
//// -> 로직이 간단하게 짜여진 건 아니라서 저도 조금 아쉬운 부분입니다 ㅠ
class Main{
    static final int H = 0, M = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int startTime[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int endTime[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int wantNum = Integer.parseInt(br.readLine());
        int numCnt = 0;

        //// 함수로 분리하고 함수 이름으로 의도를 표현해줬다면 더 가독성있게 읽혔을 것 같습니다 :22
        //// -> 많은 시간을 들인 문제라 풀고 지쳐서 바로 냈던 것 같아요 ㅠ 반영했습니다! 앞으로는 지치더라도 가독성을 놓지면 안 되겠어요 ㅎㅎ 두분 모두 감사합니다
        if(startTime[H] == endTime[H]){
            numCnt = cntNumInOneHour(startTime[H], startTime[M], endTime[M], wantNum, numCnt);
        }else{
            numCnt = cntNumInOneHour(startTime[H], startTime[M], 59, wantNum, numCnt);
            numCnt = cntNumInHours(startTime[H], endTime[H], wantNum, numCnt);
            numCnt = cntNumInOneHour(endTime[H], 0, endTime[M], wantNum, numCnt);
        }

        System.out.print(numCnt);
    }

    static int cntNumInOneHour(int hour, int startM, int endM, int num, int cnt){
        if(containWantNum(hour, num)) cnt += endM - startM + 1;
        else cnt = cntNumOfClockInOneHour(startM, endM, num, cnt);
        return cnt;
    }

    static int cntNumOfClockInOneHour(int start, int end, int num, int cnt){
        for(int i = start; i <= end; i++){
            if(containWantNum(i, num)) cnt++;
        }
        return cnt;
    }

    static int cntNumInHours(int start, int end, int num, int cnt){
        int cntWantNumInOneHour = (num < 6 ? 15 : 6);
        for(int i = start + 1; i < end; i++){
            if(containWantNum(i, num)) cnt += 60;
            else cnt += cntWantNumInOneHour;
        }
        return cnt;
    }

    static boolean containWantNum(int num, int want){
        return want == num || want == num / 10 || want == num % 10;
    }
}
