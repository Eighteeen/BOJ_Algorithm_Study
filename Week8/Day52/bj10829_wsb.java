import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        System.out.print(convertToBinary(N, 0));
        br.close();
    }

    static StringBuilder convertToBinary(long num, long binary){
        StringBuilder sb = new StringBuilder();
        if(binary == 0){
            if(num == 0) return sb;
            binary = closeSquared(num, 2);
        }

        num -= binary;
        if(num >= 0) sb.append("1");
        else{
            num += binary;
            sb.append("0");
        }

        return sb.append(convertToBinary(num, binary / 2));
    }

    static long closeSquared(long num, long root){
        long squared = 1;
        while(squared <= num) squared *= root;
        squared /= root;

        return squared;
    }
}