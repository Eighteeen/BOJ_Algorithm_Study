import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n;
        String nowWord, minWord;

        while((n = Integer.parseInt(br.readLine())) != 0){
            minWord = br.readLine();
            for(int i = 1; i < n; i++){
                nowWord = br.readLine();
                if(minWord.compareToIgnoreCase(nowWord) > 0){
                    minWord = nowWord;
                }
            }
            sb.append(minWord).append("\n");
        }
        System.out.print(sb);
    }
}