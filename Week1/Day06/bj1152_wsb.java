import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
    //// 깔끔하게 잘 짠거 같습니다.:22
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().trim();
        int len = str.length();
        int cnt = 0;

        for(int i = 0; i < len; i++){
            if(str.charAt(i) == ' '){
                cnt++;
            }
        }
        //// 공백 체크를 이렇게 삼항연산자로 나중에 하는게 미적으로 더 좋은 것 같네요.
        //// 저는 어쩔 수 없다는 생각으로 맨 앞에서 공백체크 해놓고 미적인 면에서 좀 불만족스러워 했거든요. 배워갑니다.
        System.out.print(str.equals("") ? 0 : ++cnt);
    }
}
