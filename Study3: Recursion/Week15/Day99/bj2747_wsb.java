import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println(getNumInFibonacci(n, 0, 1));
        
        br.close();
    }

    //// 재귀 ㅋㅋ 굿
    static int getNumInFibonacci(int wonderIdx, int firstNum, int sencondNum) {
        if (wonderIdx == 0) return firstNum;
        return getNumInFibonacci(wonderIdx - 1, sencondNum, firstNum + sencondNum);
    }
}