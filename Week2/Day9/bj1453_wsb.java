import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> sitSeat = new ArrayList<>();
        int wantSeat = 0, cntReject = 0;

        for(int i = 0; i < N; i++){
            wantSeat = Integer.parseInt(st.nextToken());
            if(sitSeat.contains(wantSeat)) cntReject++;
            else sitSeat.add(wantSeat);
        }
        System.out.print(cntReject);
    }
}