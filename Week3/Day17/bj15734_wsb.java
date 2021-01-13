import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String playerInfo[] = br.readLine().split(" ");
        int L = Integer.parseInt(playerInfo[0]);
        int R = Integer.parseInt(playerInfo[1]);
        int A = Integer.parseInt(playerInfo[2]);
        int diffrent = Math.abs(L - R);

        if(diffrent <= A){
            A -= diffrent;
            if(L < R) L += diffrent;
            else R += diffrent;

            diffrent = A / 2;
            A -= diffrent;
            if(L < R){
                L += diffrent;
                R += A;
            }else{
                R += diffrent;
                L += A;
            }

            System.out.print(Math.min(L, R) * 2);
        }else{
            System.out.print(Math.min(L + A, R + A) * 2);
        }
    }
}