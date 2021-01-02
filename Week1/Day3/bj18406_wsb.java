import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strN = br.readLine();
        int halfLen = strN.length() / 2;
        //// 1/2보다 left/right, front/back 등으로 지었으면 더 직관적이었을 것 같아요
        String str1 = strN.substring(0, halfLen), str2 = strN.substring(halfLen);
        int sum1 = 0, sum2 = 0;
        
        for(int i = 0; i < halfLen; i++){
            sum1 += Character.getNumericValue(str1.charAt(i));
            sum2 += Character.getNumericValue(str2.charAt(i));
        }

        System.out.print(sum1 == sum2 ? "LUCKY" : "READY");
    }
}
