import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int x = Integer.parseInt(br.readLine()), y = Integer.parseInt(br.readLine());
        int quadrant = 1;
        if (x < 0 && y > 0) quadrant = 2;
        else if (x < 0 && y < 0) quadrant = 3;
        else if (x > 0 && y < 0) quadrant = 4;

        System.out.println(quadrant);
        br.close();
    }
}