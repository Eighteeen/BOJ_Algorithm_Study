import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //// 얼 한글자로 나눈 String 배열도 괜찮네요
        String[] numStrArr = br.readLine().split("");
        int arrLen = numStrArr.length;

        //// 미리 초기화해서 계산이 하나 줄어서 좋은 것 같아요! :22
        int result = 20;
        int num1, num2;
        if (arrLen == 3) {
            num1 = Integer.parseInt(numStrArr[0]);
            num2 = Integer.parseInt(numStrArr[2]);
            result = 10;
            result += (num2 == 0 ? num1 : num2);
        } else if (arrLen < 3) {
            num1 = Integer.parseInt(numStrArr[0]);
            num2 = Integer.parseInt(numStrArr[1]);
            result = num1 + num2;
        }

        System.out.println(result);
        br.close();
    }
}