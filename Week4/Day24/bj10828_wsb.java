import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        WsbStack stack = new WsbStack(N);
        String command[];

        for(int i = 0; i < N; i++){
            command = br.readLine().split(" ");
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