import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

class Main{
    static Character[] materials;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numInfo = br.readLine().split(" ");
        String[] numArr = br.readLine().split(" ");
        materials = stringArrToCharacterArr(numArr);

        System.out.print(maxNearOfCanMake(numInfo[0]));
        br.close();
    }
    //// purpose(목적)은 뭔가 너무 포괄적인 느낌인데 target은 어때요
    //// -> 훨씬 직관적인 거 같아요 감사합니다!
    static int maxNearOfCanMake(String target){
        //// Arrays.sort 활용 배워갑니다
        Arrays.sort(materials, Collections.reverseOrder());
        String maxNums = String.valueOf(materials[0]).repeat(target.length() - 2);

        Set<String> resultSet = mixMaterialsSet(Integer.parseInt(target), maxNums, null);
        int targetInt = Integer.parseInt(target);
        for(String s : resultSet){
            StringBuilder sb = new StringBuilder(s).append(maxNums);
            int compare = Integer.parseInt(sb.toString());
            if(targetInt >= compare) return compare;
        }

        return Integer.parseInt(materials[0] + maxNums);
    }

    //// 26~30줄이 이쪽 라인에 있을 일반함수를 억지로 재귀함수로 만든 것 같습니다.
    //// 이쪽 라인으로 26~30줄을 옮겨본 코드입니다: https://pastebin.com/mjVjyk0i
    //// '억지로'라고 한 이유는 재귀호출 함으로써 잃는 것만 있다는 점 때문에 그렇습니다. (매개변수가 불필요하게 하나 늘어나고 프로그램 흐름이 복잡해짐)
    //// 그래서 사실상 재귀함수를 사용하지 않고 푼 것이나 다름없다고 생각합니다.. 그래서,, 이런 말 드리기 조심스럽지만 함수를 새로 다시 짜는게 좋을 것 같습니다,,,
    //// -> 해당 문제의 특성으로는 재귀적으로 푸는 방식이 잃을 게 많다고 생각이 됐고 그래서 말씀하신 것처럼 억지로 만들어 봤습니다 ㅜ
        //// -> 다시 아무리 봐도 이 문제는 제 풀이로는 이중 루프 한번만 돌면 되는 문제라서 재귀를 쓰는 것에 많은 의문점이 들었던 문제였습니다.
        //// -> 하지만 재귀를 공부하는 입장이니 문자 조합을 set으로 반환하여 그것으로 비교하는 함수로 바꿔보았습니다.
        //// -> 현재 바꾼 재귀함수도 재귀로서 얻는 점보다 잃는 점이 많다고 생각하지만,
        //// -> 아무리 생각해도 루프를 돌릴 필요없는 걸 굳이 돌리는 건 아쉽다고 판단되어 이정도로 바꾸게 되었습니다. (사살 호출하는 곳에서 set 루프를 돌리지만요 ㅠ)
    //// -> 이번 피드백과 저번 피드백에서 말씀하신 것처럼 아직 재귀함수가 익숙하지 않아서 재귀함수로 얻을 수 있는 점들을 활용하지 못 하는 것 같습니다.
    //// -> 말씀하신 것처럼 잃는 것보다 얻는 게 많은 재귀함수 만들 수 있도록 앞으로 노력해보려구요! 피드백 감사합니다!
    static Set mixMaterialsSet(int target, String maxNums, String front){
        Set<String> resultSet = new TreeSet(Collections.reverseOrder());
        if(front != null){
            for(char c : materials){
                StringBuilder sb = new StringBuilder(front).append(c);
                resultSet.add(sb.toString());
            }
            return resultSet;
        }

        char frontTarget = String.valueOf(target).charAt(0);
        for(char c : materials){
            if(frontTarget >= c){
                resultSet = mixMaterialsSet(target, maxNums, String.valueOf(c));
                for(String s : resultSet){
                    StringBuilder sb = new StringBuilder(s).append(maxNums);
                    if(target >= Integer.parseInt(sb.toString())) return resultSet;
                }
            }
        }

        return resultSet;
    }

    static Character[] stringArrToCharacterArr(String[] strArr){
        int len = strArr.length;
        Character[] chrArr = new Character[len];
        for(int i = 0; i < len; i++) chrArr[i] = strArr[i].charAt(0);
        return chrArr;
    }
}