import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
    ////진짜 깰꼼 그 자체임둥,,,정렬해서 비교할까 생각은 했는데 equals생각 못해내서 포기했는데,,,굳 
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
            //둘다 정렬해버리는거 아이디어 괜찮다 
            Arrays.sort(word);
            Arrays.sort(puzzle);
            bw.write(makePuzzleResult(Arrays.equals(word, puzzle)));
        }

        bw.flush();
        br.close();
        bw.close();
    }

    //// makePuzzleResult 함수 따로 만든것도 되게 좋은거같아
    static String makePuzzleResult(boolean collection){
        if(collection) return "Case " + ++cntCase + ": same\n";
        return "Case " + ++cntCase + ": different\n";
    }
}
