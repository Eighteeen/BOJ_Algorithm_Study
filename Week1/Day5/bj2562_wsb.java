import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int now;
        int max = -1;
        int index = 0, maxIndex = 0;

        for(int i = 0; i < 9; i++){
            now = Integer.parseInt(br.readLine());
            index += 1;
            if(max < now){
                max = now;
                maxIndex = index;
            }
        }

        sb.append(max)
            .append("\n")
            .append(maxIndex);
        System.out.print(sb);
    }
}