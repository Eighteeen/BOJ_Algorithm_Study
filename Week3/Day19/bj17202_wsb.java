import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 섞는 로직도 궁합보는 로직도 깔꼼합니다
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ANum = br.readLine();
        String BNum = br.readLine();
        int compatiNum[] = new int[16];
        for(int i = 0; i < 8; i++){
            int index = i * 2;
            compatiNum[index] = ANum.charAt(i) - '0';
            compatiNum[index + 1] = BNum.charAt(i) - '0';
        }

        for(int i = 15; i > 1; i--){
            for(int j = 0; j < i; j++){
                compatiNum[j] = (compatiNum[j] + compatiNum[j + 1]) % 10;
            }
        }
        System.out.print(compatiNum[0] + "" + compatiNum[1]);
    }
}