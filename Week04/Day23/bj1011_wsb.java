import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//// 아주 보기쉽고 깔끔하네요 :22
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        String spaceshipInfo[];
        int distance;

        for(int i = 0; i < T; i++){
            spaceshipInfo = br.readLine().split(" ");
            distance = Integer.parseInt(spaceshipInfo[1]) - Integer.parseInt(spaceshipInfo[0]);
            bw.write(countWork(distance) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int countWork(int distance){
        int work = (int)Math.sqrt(distance);
        if(work * work == distance) return (work * 2) - 1;
        else if(work * (work + 1) < distance) return (work * 2) + 1;
        else return (work * 2);
    }
}
