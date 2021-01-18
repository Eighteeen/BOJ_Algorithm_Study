import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

class Main{
    static int cntCase = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String lineStr;
        char word[], puzzle[];

        while(!(lineStr = br.readLine()).equals("END")){
            word = lineStr.toCharArray();
            puzzle = br.readLine().toCharArray();
            Arrays.sort(word);
            Arrays.sort(puzzle);
            bw.write(makePuzzleResult(Arrays.equals(word, puzzle)));
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static String makePuzzleResult(boolean collection){
        if(collection) return "Case " + ++cntCase + ": same\n";
        return "Case " + ++cntCase + ": different\n";
    }
}