import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//// 오우 괸장히 깔끔하네요!!
//// 깔꼼깔꼼하고 출제자의 의도대로 로직도 잘 짜신 것 같습니당
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int orders[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Queue<Integer> personQueue = new LinkedList<>();

        for(int i = 1; i <= orders[0]; i++){
            personQueue.add(i);
        }

        bw.write("<");
        //// 맥락상 !=가 아닌 <를 사용했으면 더 좋았을 것 같아요!
        while(personQueue.size() != 1){
            for(int i = 1; i < orders[1]; i++){
                personQueue.add(personQueue.remove());
            }
            bw.write(personQueue.remove() + ", ");
        }
        bw.write(personQueue.remove() + ">");

        bw.flush();
        br.close();
        bw.close();
    }
}