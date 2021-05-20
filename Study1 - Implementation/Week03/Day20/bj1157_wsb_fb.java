import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//// 깔꼼하네용
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        //// 이 문제에서도 알파벳별로 비교해줄 수 있었군요 생각지 못했습니다.
        int cntAlphabet[] = new int[26];

        for(Character chr : str.toCharArray()){
            if(chr >= 'a') cntAlphabet[chr - 'a']++;
            else cntAlphabet[chr - 'A']++;
        }

        //// 한 함수에서 두 가지 일을 하는 점이 조금 아쉽습니다
        //// -> 변수에 두 가지를 넣어서 체크하는 게 아쉬운 것 같아 함수는 유지하되 리턴 방법을 살짝 바꿔봤습니다.
        //// -> 함수에서 체크하는 게 다 max와 관련되어서 함수를 나누는 것은 힘들 것 같아서 함수 방법은 유지했습니다!
        bw.write(checkMaxAlphabet(cntAlphabet) + 'A');

        bw.flush();
        br.close();
        bw.close();
    }

    static int checkMaxAlphabet(int cntAlphabet[]){
        int max = 0, maxCnt = 0, maxIndex = 0;
        for(int i = 0; i < 26; i++){
            if(max == cntAlphabet[i]) maxCnt++;
            if(max < cntAlphabet[i]){
                max = cntAlphabet[i];
                maxCnt = 1;
                maxIndex = i;
            }
        }
        return (maxCnt > 1 ? -2 : maxIndex);
    }
}
