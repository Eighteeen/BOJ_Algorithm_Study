import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// ㄲㄲ : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] bottles = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = 0;
        for (int bottle : bottles) {
            result += bottle * 5;
        }

        System.out.println(result);
        br.close();
    }
}