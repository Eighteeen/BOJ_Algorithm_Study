import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        int cntAlphabet[] = new int[26];

        for(Character chr : str.toCharArray()){
            if(chr >= 'a') cntAlphabet[chr - 'a']++;
            else cntAlphabet[chr - 'A']++;
        }

        int checkMax = checkMaxAlphabet(cntAlphabet);
        if(checkMax > -1) bw.write(checkMax + 'A');
        else bw.write('?');

        bw.flush();
        br.close();
        bw.close();
    }

    static int checkMaxAlphabet(int cntAlphabet[]){
        int max = 0, maxCnt = 0, maxIndex = 0;
        for(int i = 0; i < 26; i++){
            if(max == cntAlphabet[i]) maxCnt++;
            if(max < cntAlphabet[i]){
                max = cntAlphabet[i];
                maxCnt = 1;
                maxIndex = i;
            }
        }
        return (maxCnt > 1 ? -1 : maxIndex);
    }
}