import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cntGoodWord = 0;

        for(int i = 0; i < N; i++){
            if(isGoodWord(br.readLine().toCharArray())) cntGoodWord++;
        }

        System.out.print(cntGoodWord);
        br.close();
    }

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