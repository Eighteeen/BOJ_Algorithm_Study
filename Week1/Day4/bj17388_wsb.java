import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
        ////매번 자바기능들 활용하시는 거 보고 감탄합니다. 값비교 조차 필요없는 코드라,,,
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String strArr[] = br.readLine().split(" ");
        List<Integer> participation = Arrays.stream(strArr).map(Integer::valueOf).collect(Collectors.toList());

        if(participation.stream().mapToInt(Integer::intValue).sum() >= 100){
            System.out.print("OK");
        }else{
            int index = participation.indexOf(Collections.min(participation));
            if(index == 0){
                System.out.print("Soongsil");
            } else if(index == 1){
                System.out.print("Korea");
            } else{
                System.out.print("Hanyang");
            }
        }
    }
}
