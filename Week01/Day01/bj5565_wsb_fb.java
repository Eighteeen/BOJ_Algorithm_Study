import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int price = Integer.parseInt(br.readLine());

        for(int i = 0; i < 9; i++){
            price -= Integer.parseInt(br.readLine());
        }
        System.out.print(price);
    }
}
