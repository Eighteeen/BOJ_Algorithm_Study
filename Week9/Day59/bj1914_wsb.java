import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//// 깔꼼합니다!
class Main{
    static StringBuilder process = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(cntMove(N));
        if(N <= 20){
            moveTowerOfHanoi(N, 1, 2, 3);
            System.out.print(process);
        }
        br.close();
    }

    static BigInteger cntMove(int n){
        return BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE);
    }  

    static void moveTowerOfHanoi(int n, int from, int tmp, int to){
        if(n == 1) moveDisc(from, to);
        else{
            moveTowerOfHanoi(n - 1, from, to, tmp);
            moveDisc(from, to);
            moveTowerOfHanoi(n - 1, tmp, from, to);
        }
    }

    //// 적절한 함수 분리와 작명 굳굳..
    static void moveDisc(int from, int to){
        process.append(from).append(" ").append(to).append("\n");
    }
}