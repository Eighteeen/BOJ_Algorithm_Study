import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    static final int YEAR_DIFFERENCE = 543;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Integer.parseInt(br.readLine()) - YEAR_DIFFERENCE);
        br.close();
    }
}