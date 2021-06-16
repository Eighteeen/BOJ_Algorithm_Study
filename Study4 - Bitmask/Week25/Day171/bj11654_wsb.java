import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////깔끔
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println((int) br.readLine().charAt(0));
        br.close();
    }
}