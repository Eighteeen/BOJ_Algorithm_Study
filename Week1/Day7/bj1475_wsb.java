import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int roomNum[] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int len = roomNum.length;
        int nowNum;
        Integer numSet[] = new Integer[10];
        Arrays.fill(numSet, 0);

        for(int i = 0; i < len; i++){
            nowNum = roomNum[i];
            if(nowNum == 6 || nowNum == 9){
                if(numSet[6] < numSet[9]) numSet[6]++;
                else numSet[9]++;
            }else{
                numSet[nowNum]++;
            }
        }
        System.out.print(Collections.max(Arrays.asList(numSet)));
    }
}