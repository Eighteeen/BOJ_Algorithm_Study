import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 아 하긴 라이브러리 함수 있을만한 기능이네요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int X = Integer.parseInt(br.readLine());
        System.out.println(Integer.bitCount(X));
        
        br.close();
    }
}