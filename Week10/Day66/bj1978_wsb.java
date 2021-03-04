import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔꼼~
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(cntPrimeNum(numArr));
        
        br.close();
    }

    //// 제곱근까지만 구해도 괜찮습니다! (에라토스테네스의 접근)
    static int cntPrimeNum(int[] numArr){
        int cnt = 0;
        for(int n : numArr){
            if(isPrimeNum(n)) cnt++;
        }
        return cnt;
    }
    
    static boolean isPrimeNum(int num){
        if(num == 1) return false;

        double lastDivisor = Math.sqrt(num);
        for(int i = 2; i <= lastDivisor; i++){
            if(num % i == 0) return false;
        }

        return true;
    }
}