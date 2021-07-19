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

        //// 규칙을 끝끝내 찾아내신 것에 증말 리스펙합니다.. 엄두도 못 냈는데 :22 꼼꼼한 구현 멋져요
        //// 문제 푸는 시간 효율면에서 보자면 무차별 대입으로 푸는 것도 좋은 것 같습니다. (물론 시간초과가 안 나는 시간복잡도와 입력일 때에만)
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
