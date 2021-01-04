import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num[] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray(); 
        int len = num.length;
        //// seqSize 네이밍 좋네요 와우
        int seqSize = len > 1 ? num[0] - num[1] : 0;
        boolean isCute = true;

        for(int i = 1; i < len; i++){
            if(seqSize != num[i - 1] - num[i]){
                isCute = false;
                break;
            }
        }
        //// 확실히 요런건 삼항연산자가 깔끔한 것 같습니다. 배워갑니다
        System.out.print(isCute ? "◝(⑅•ᴗ•⑅)◜..°♡ 뀌요미!!" : "흥칫뿡!! <(￣ ﹌ ￣)>");
    }
}