import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//// 깔꼬미 효율쓰
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cntGoodWord = 0;

        for(int i = 0; i < N; i++){
            //// 아예 추상화를 하는게 깔꼼쓰하네요
            if(isGoodWord(br.readLine().toCharArray())) cntGoodWord++;
        }

        System.out.print(cntGoodWord);
        br.close();
    }

    //// 사소한거지만 함수 이름이 isGoodWord니 매개변수가 String형이면 좀 더 자연스러웠을 것 가타요
    static boolean isGoodWord(char[] word){
        Stack<Character> wordStack = new Stack<>();

        for(char w : word){
            if(!wordStack.empty() && wordStack.peek() == w){
                wordStack.pop();
                continue;
            }
            wordStack.push(w);
        }

        return wordStack.empty();
    }
}