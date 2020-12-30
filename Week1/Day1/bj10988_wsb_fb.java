import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String strCompare;

        strCompare = new StringBuffer(str).reverse().toString();
        System.out.print(str.equals(strCompare) ? 1 : 0);
    }
}