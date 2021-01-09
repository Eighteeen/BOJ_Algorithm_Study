import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔꼼쓰:22 : 33 잘읽혀요!
class Main{
    static final int WIN = 1;
    static final int LOSE = 0;
    static final int TIE = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String diceInfo[];
        int gamerNum1, gamerNum2;
        int gamerResult1 = 100, gamerResult2 = 100;
        int nowResultOfGamer1;

        for(int i = 0; i < n; i++){
            diceInfo = br.readLine().split(" ");
            gamerNum1 = Integer.parseInt(diceInfo[0]);
            gamerNum2 = Integer.parseInt(diceInfo[1]);
            nowResultOfGamer1 = whatResult(gamerNum1, gamerNum2);
            ////가독성 좋은이유중 하나일거 같아요
            if(nowResultOfGamer1 == WIN){
                gamerResult2 -= gamerNum1; 
            }else if(nowResultOfGamer1 == LOSE){
                gamerResult1 -= gamerNum2;
            }
        }

        sb.append(gamerResult1).append("\n");
        sb.append(gamerResult2);
        System.out.print(sb);
    }
    
    //// enum이나 상수를 반환했으면 더 좋았을 것 같아요
    //// -> 상수로 변경해봤는데 작은 차이지만 효율성은 String 반환이 더 좋다고 나오네요
    //// -> 메모리를 들려야 해서 그런건가 생각하고 있는데 피드백을 통해 체험할 수 있는 재밌는 실험이었습니다 ㅎㅎ 감사해요
    static int whatResult(int challengerNum, int opponentNum){
        if(challengerNum > opponentNum) return WIN;
        else if(challengerNum < opponentNum) return LOSE;
        else return TIE;
    }
}