import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //// 에엥 Long으로도 됐네요? 0~21억 합 구할때 오버플로우 나던데 뭐지
        //// 아무튼 long 쓰니 더 깔끔하네요!
        String[] numStrArr = br.readLine().split(" ");
        long A = Long.parseLong(numStrArr[0]);
        long B = Long.parseLong(numStrArr[1]);

        System.out.println(getAllSumBetweenTwoNums(A, B));
        br.close();
    }

    static long getAllSumBetweenTwoNums(long num1, long num2) {
        return (num1 + num2) * (Math.abs(num1 - num2) + 1) / 2;
    }
}