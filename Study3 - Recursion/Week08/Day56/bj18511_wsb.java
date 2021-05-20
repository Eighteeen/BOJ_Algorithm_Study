import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numInfo = br.readLine().split(" ");
        String[] numArr = br.readLine().split(" ");

        System.out.print(maxNearOfCanMake(numInfo[0], stringArrToCharacterArr(numArr)));
        br.close();
    }
    //// purpose(목적)은 뭔가 너무 포괄적인 느낌인데 target은 어때요
    static int maxNearOfCanMake(String purpose, Character[] materials){
        //// Arrays.sort 활용 배워갑니다
        Arrays.sort(materials, Collections.reverseOrder());
        String maxNums = String.valueOf(materials[0]).repeat(purpose.length() - 2);
        return getMaxNear(Integer.parseInt(purpose), materials, maxNums, null);
    }

    static int getMaxNear(int purpose, Character[] materials, String maxNums, String first){
        if(first != null){
            for(char c : materials){
                StringBuilder sb = new StringBuilder(first);
                int compare = Integer.parseInt(sb.append(c).append(maxNums).toString());
                if(purpose >= compare) return compare;
            }
            return 0;
        }

        int result = 0;
        char firstPurpose = String.valueOf(purpose).charAt(0);
        for(char c : materials){
            if(firstPurpose >= c){
                //// 26~30줄이 이쪽 라인에 있을 일반함수를 억지로 재귀함수로 만든 것 같습니다.
                //// 이쪽 라인으로 26~30줄을 옮겨본 코드입니다: https://pastebin.com/mjVjyk0i
                //// '억지로'라고 한 이유는 재귀호출 함으로써 잃는 것만 있다는 점 때문에 그렇습니다. (매개변수가 불필요하게 하나 늘어나고 프로그램 흐름이 복잡해짐)
                //// 그래서 사실상 재귀함수를 사용하지 않고 푼 것이나 다름없다고 생각합니다.. 그래서,, 이런 말 드리기 조심스럽지만 함수를 새로 다시 짜는게 좋을 것 같습니다,,,
                result = getMaxNear(purpose, materials, maxNums, String.valueOf(c));
                if(result != 0) return result;
            }
        }
        result = Integer.parseInt(materials[0] + maxNums);

        return result;
    }

    static Character[] stringArrToCharacterArr(String[] strArr){
        int len = strArr.length;
        Character[] chrArr = new Character[len];
        for(int i = 0; i < len; i++) chrArr[i] = strArr[i].charAt(0);
        return chrArr;
    }
}