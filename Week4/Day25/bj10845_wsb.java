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
        WsbQueue queue = new WsbQueue(N);
        String command[];

        for(int i = 0; i < N; i++){
            command = br.readLine().split(" ");
            if(command[0].equals("push")) queue.push(Integer.parseInt(command[1]));
            else if(command[0].equals("pop")) bw.write(queue.pop());
            else if(command[0].equals("size")) bw.write(queue.size());
            else if(command[0].equals("empty")) bw.write(queue.empty());
            else if(command[0].equals("front")) bw.write(queue.front());
            else bw.write(queue.back());
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static class WsbQueue {
        int container[];
        int front, back, size;

        public WsbQueue(int size){
            container = new int[size];
            front = size = 0;
            back = -1;
        }

        public void push(int x){
            container[++back] = x;
            size++;
        }
    
        public String pop() {
            if(isSizeZero()) return "-1\n";
            else{
                int num = container[front];
                container[front++] = 0;
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

        public String front(){
            return wantNum(true);
        }

        public String back(){
            return wantNum(false);
        }

        private String wantNum(boolean isFront){
            int index;
            if(isFront) index = front;
            else index = back;

            if(isSizeZero()) return "-1\n";
            else return container[index] + "\n";
        }

        private boolean isSizeZero(){
            return size == 0;
        }
    }
}