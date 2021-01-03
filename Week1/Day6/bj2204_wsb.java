import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
    ////깔끔하게 잘 짠거 같습니다.
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
                //// 저번부터 느꼈지만 언어에 내장돼있는 기능을 잘 찾아 활용하시는 것 같습니다. 배워가요
                if(minWord.compareToIgnoreCase(nowWord) > 0){
                    minWord = nowWord;
                }
            }
            //// StringBuilder 활용 굳굳!
            sb.append(minWord).append("\n");
        }
        System.out.print(sb);
    }
}