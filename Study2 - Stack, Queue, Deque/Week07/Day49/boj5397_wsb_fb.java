import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//// 무난무난 효율쓰
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            sb.append(getPassword(br.readLine().toCharArray())).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static String getPassword(char[] input){
        Deque<Character> password = new ArrayDeque<>();
        int cursor = 0;

        for(char c : input){
            if(isText(c)){
                password.addLast(c);
                cursor++;
                continue;
            }

            //// if (password.isEmpty()) continue; 해버리는거 어때요
            //// -> 넘나 좋네요 깔끔해졌어요!
            if(password.isEmpty()) continue;
            
            if(c == '-' && cursor != 0){
                password.removeLast();
                cursor--;
                continue;
            }

            if(c == '<' && cursor != 0){
                moveToLeft(password);
                cursor--;
            }else if(c == '>' && cursor != password.size()){
                moveToRight(password);
                cursor++;
            }
        }

        int len = password.size();
        if(cursor != len) moveToRight(password, len - cursor);

        StringBuilder sb = new StringBuilder();
        password.forEach(p -> sb.append(p));
        return sb.toString();
    }

    static boolean isText(char c){
        return (c != '-' && c != '<' && c != '>');
    }

    static void moveToLeft(Deque objects){
        objects.addFirst(objects.removeLast());
    }

    static void moveToRight(Deque objects){
        objects.addLast(objects.removeFirst());
    }

    static void moveToRight(Deque objects, int num){
        for(int i = 0; i < num; i++){
            objects.addLast(objects.removeFirst());
        }
    }
}