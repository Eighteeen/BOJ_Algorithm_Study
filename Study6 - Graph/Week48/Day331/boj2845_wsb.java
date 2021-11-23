import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] partyInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] newsInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int partyPeople = partyInfo[0] * partyInfo[1];
        
        for (int newsPeople : newsInfo) {
            sb.append(newsPeople - partyPeople).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}