import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int num, min = 101, sum = 0;

        for(int i = 0; i < 7; i++){
            num = Integer.parseInt(br.readLine());
            if(num % 2 == 1){
                sum += num;
                if (min > num) min = num;
            }
        }
        //// if 대신 삼항연산자 쓰는것도 간결해서 좋네용
        System.out.print(sum == 0 ? -1 : sum + "\n" + min);
    }
}