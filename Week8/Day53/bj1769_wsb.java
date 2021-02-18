import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(answerOfQuestionTwo(br.readLine(), 0));

        br.close();
    }

    static StringBuilder answerOfQuestionTwo(String numStr, int cnt){
        if(numStr.length() == 1 && Integer.parseInt(numStr) < 10){
            StringBuilder sb = new StringBuilder();
            if(isMultiple(Integer.parseInt(numStr), 3)) return sb.append(cnt).append("\nYES");
            else return sb.append(cnt).append("\nNO");
        }

        int sum = 0;
        for(char c : numStr.toCharArray()){
            sum += convertCharToInt(c);
        }

        return answerOfQuestionTwo(String.valueOf(sum), ++cnt);
    }

    static boolean isMultiple(int dividend, int divisor){
        return (dividend % divisor == 0);
    }

    static int convertCharToInt(char c){
        return (c - '0');
    }
}