import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    static int L = 0, R = 1, A = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int side[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int diffrent = Math.abs(side[L] - side[R]);

        //// 규칙을 끝끝내 찾아내신 것에 증말 리스펙합니다.. 엄두도 못 냈는데 :22 꼼꼼한 구현 멋져요
        //// 문제 푸는 시간 효율면에서 보자면 무차별 대입으로 푸는 것도 좋은 것 같습니다. (물론 시간초과가 안 나는 시간복잡도와 입력일 때에만)
        //// -> 난 이럴 때 어떻게 하지? 로 시작해서 인간의(?) 방법 그대로 했는데 규칙이라고 하니 약간 놀랐습니다..!
        //// -> 하지만 kjh, lye의 코드에 비해서 가독성이 아쉬운 것 같아 로직은 유지한 채로 함수를 만들어 가속성을 높여봤습니다!
        if(diffrent <= side[A]){
            FillLackSide(side, diffrent);
            FillPossibleBoth(side, diffrent);
            System.out.print(Math.min(side[L], side[R]) * 2);
        }else{
            System.out.print(Math.min(side[L] + side[A], side[R] + side[A]) * 2);
        }
    }

    static void FillLackSide(int side[], int diffrent){
        side[A] -= diffrent;
        if(side[L] < side[R]) side[L] += diffrent;
        else side[R] += diffrent;
    }

    static void FillPossibleBoth(int side[], int diffrent){
        diffrent = side[A] / 2;
        side[A] -= diffrent;
        if(side[L] < side[R]){
            side[L] += diffrent;
            side[R] += side[A];
        }else{
            side[R] += diffrent;
            side[L] += side[A];
        }
    }
}
