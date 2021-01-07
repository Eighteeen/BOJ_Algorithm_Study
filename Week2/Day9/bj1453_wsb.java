import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//// 깔끔해요!:22 and 효율적ㅇㅣ에요!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ////피시방 자리를 String형으로 입력하고 StringTokenizer를 사용해서 쪼개는건 알겠는데 br.readLine()를 하면 무슨 기준으로 쪼개는건가요?
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sitSeat = new ArrayList<>();
        int wantSeat = 0, cntReject = 0;

        for(int i = 0; i < N; i++){
            wantSeat = Integer.parseInt(st.nextToken());
            //// reject 네이밍 배워갑니다
            if(sitSeat.contains(wantSeat)) cntReject++;
            else sitSeat.add(wantSeat);
        }
        System.out.print(cntReject);
    }
}
