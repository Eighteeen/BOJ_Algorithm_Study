import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔꼼쓰:22 : 33 잘읽혀요!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String diceInfo[];
        int gamerNum1, gamerNum2;
        int gamerResult1 = 100, gamerResult2 = 100;
        String nowResultOfGamer1;

        for(int i = 0; i < n; i++){
            diceInfo = br.readLine().split(" ");
            gamerNum1 = Integer.parseInt(diceInfo[0]);
            gamerNum2 = Integer.parseInt(diceInfo[1]);
            nowResultOfGamer1 = whatResult(gamerNum1, gamerNum2);
            ////가독성 좋은이유중 하나일거 같아요
            if(nowResultOfGamer1.equals("win")){
                gamerResult2 -= gamerNum1; 
            }else if(nowResultOfGamer1.equals("lose")){
                gamerResult1 -= gamerNum2;
            }
        }

        sb.append(gamerResult1).append("\n");
        sb.append(gamerResult2);
        System.out.print(sb);
    }
    
    //// enum이나 상수를 반환했으면 더 좋았을 것 같아요
    static String whatResult(int challengerNum, int opponentNum){
        if(challengerNum > opponentNum) return "win";
        else if(challengerNum < opponentNum) return "lose";
        else return "tie";
    }
}