import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    enum Milk {
        STRAWBERRY, CHOCO, BANANA
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nextMilk = 0, drinkMilk = 0;
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