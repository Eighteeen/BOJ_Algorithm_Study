import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

//// Clean
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] scoresOfStudent1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] scoresOfStudent2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //// Socrates
        int sumSocre1 = IntStream.of(scoresOfStudent1).sum();
        int sumSocre2 = IntStream.of(scoresOfStudent2).sum();
        
        System.out.println(sumSocre1 >= sumSocre2 ? sumSocre1 : sumSocre2);
        br.close();
    }
}