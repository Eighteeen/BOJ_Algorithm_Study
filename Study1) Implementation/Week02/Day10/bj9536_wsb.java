import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
    ////전체적으로 깔끔하게 짜여진거 같습니다.
class Main{
    static StringBuilder sb = new StringBuilder();
    static String nowSound;
    ////깔끔하네요.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<String> recordList;

        for(int i = 0; i < T; i++){
            recordList = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
            while(!(nowSound = br.readLine()).equals("what does the fox say?")){
                //// removeIf 배워가요.. 있는 줄도 몰랐네:22 :33 별게 다있네요
                recordList.removeIf(r -> r.equals(getSound(nowSound)));
            }
            printFoxSound(recordList);
        }
        System.out.print(sb);
    }

    static void printFoxSound(List<String> list){
        int len = list.size();
        for(int i = 0; i < len; i++){
            sb.append(list.get(i)).append(" ");
        }
        sb.append("\n");
    }
        //// 이 함수는 뭘 해주는 과정인가요??
    static String getSound(String sound){
        return sound.substring(sound.indexOf(" goes") + 6);
    }
}
