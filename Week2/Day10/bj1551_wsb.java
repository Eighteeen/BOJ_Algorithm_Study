import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//// 깔꼼!
class Main{
    static StringBuilder sb = new StringBuilder();
    ////전체적으로 깔끔해서 읽기 편했습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String seqInfo[] = br.readLine().split(" ");
        int N = Integer.parseInt(seqInfo[0]);
        int K = Integer.parseInt(seqInfo[1]);
        List<Integer> sequence = Arrays.stream(br.readLine().split(",")).map(Integer::valueOf).collect(Collectors.toList());
        int transformNum;

        for(int i = 0; i < K; i++){
            for(int j = 1; j < N; j++){
                transformNum = sequence.get(j) - sequence.get(j - 1);
                sequence.set(j - 1, transformNum);
            }
            sequence.remove(N - 1);
            N--;
        }

        printSequence(N - 1, sequence);
        System.out.print(sb);
    }

    static void printSequence(int len, List<Integer> sequence){
        for(int i = 0; i < len; i++){
            sb.append(sequence.get(i)).append(",");
        }
        sb.append(sequence.get(len));
    }
}