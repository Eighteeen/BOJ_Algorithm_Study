import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
    ////와우 완전 깔끔해요,,,!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        String assignInfo[];
        int H, guestN;
        int floorNum, distanceNum;

        for(int i = 0; i < T; i++){
            assignInfo = br.readLine().split(" ");
            H = Integer.parseInt(assignInfo[0]);
            guestN = Integer.parseInt(assignInfo[2]);
            floorNum = guestN % H;
            distanceNum = guestN / H;

            if(floorNum == 0){
                sb.append(H);
            }else{
                distanceNum += 1;
                sb.append(floorNum);
            }
            sb.append(distanceNum < 10 ? "0" + distanceNum : distanceNum).append("\n");
        }

        System.out.print(sb);
    }
}