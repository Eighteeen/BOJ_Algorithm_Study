import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
    //// main이 깔끔합니다!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        WsbStack stack = new WsbStack(N);
        String command[];

        for(int i = 0; i < N; i++){
            command = br.readLine().split(" ");
            //// 모든 명령이 한줄만으로 처리돼서 깔끔하네용
            if(command[0].equals("push")) stack.push(Integer.parseInt(command[1]));
            else if(command[0].equals("pop")) bw.write(stack.pop());
            else if(command[0].equals("size")) bw.write(stack.size());
            else if(command[0].equals("empty")) bw.write(stack.empty());
            else bw.write(stack.top());
        }

        bw.flush();
        br.close();
        bw.close();
    }
    ////top이랑 size를 따로두니 가독성이 좋습니다만 둘다 따로 처리해야 하는건 조금 번거로울 수 있겠네요!
    static class WsbStack {
        int container[];
        int top, size;

        public WsbStack(int size){
            container = new int[size];
            top = -1;
            size = 0;
        }

        public void push(int x){
            top++;
            size++;
            container[top] = x;
        }
    
        //// \n을 붙여 String으로 반환하는게 조금 부자연스럽게 느껴집니다
        public String pop() {
            if(isSizeZero()) return "-1\n";
            else{
                int num = container[top];
                container[top] = 0;
                top--;
                size--;
                return num + "\n";
            } 
        }
    
        public String size() {
            return size + "\n";
        }
    
        public String empty() {
            if(isSizeZero()) return "1\n";
            else return "0\n";
        }
    
        public String top() {
            if(isSizeZero()) return "-1\n";
            else return container[top] + "\n";
        }

        private boolean isSizeZero(){
            return size == 0;
        }
    }
}