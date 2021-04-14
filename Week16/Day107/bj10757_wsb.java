import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//// kjh wsb 틀린그림찾기
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String numInfo[] = br.readLine().split(" ");
        BigInteger A = new BigInteger(numInfo[0]);
        BigInteger B = new BigInteger(numInfo[1]);
        System.out.print(A.add(B));

        br.close();
    }
}