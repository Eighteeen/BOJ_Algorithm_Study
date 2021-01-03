import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
    ////깔끔하게 잘짠거 같습니다
class Main{
    //// enum 활용 배워가요!
    enum Milk {
        STRAWBERRY, CHOCO, BANANA
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nextMilk = 0, drinkMilk = 0;
        //// 종학이가 21억개의 우유를 쳐묵해도 처리할 수 있는 코드네요 굳
        int possibleMilk = Milk.values().length;

        for(int i = 0; i < N; i++){
            if(Integer.parseInt(st.nextToken()) == nextMilk){
                drinkMilk++;
                nextMilk = ++nextMilk % possibleMilk;
            }
        }
        System.out.print(drinkMilk);
    }
}
