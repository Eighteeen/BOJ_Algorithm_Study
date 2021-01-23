import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
    ////코드가 잘 읽히네요 굿입니다!
class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        WsbQueue queue = new WsbQueue(N);
        Method method;
        String command[];

        //// 깔꼼깔꼼
        // Method invoke 활용으로 변경
        for(int i = 0; i < N; i++){
            command = br.readLine().split(" ");
            if(command[0].equals("push")) queue.push(Integer.parseInt(command[1]));
            else{
                method = WsbQueue.class.getMethod(command[0]);
                bw.write(method.invoke(queue) + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    // 메소드 return 자료형 String -> int로 변경
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
    
        public int pop() {
            if(isSizeZero()) return -1;
            else{
                int num = container[front];
                container[front++] = 0;
                size--;
                return num;
            } 
        }
    
        public int size() {
            return size;
        }
    
        public int empty() {
            if(isSizeZero()) return 1;
            else return 0;
        }

        public int front(){
            return wantNum(true);
        }

        public int back(){
            return wantNum(false);
        }

        private int wantNum(boolean isFront){
            int index;
            if(isFront) index = front;
            else index = back;

            if(isSizeZero()) return -1;
            else return container[index];
        }

        private boolean isSizeZero(){
            return size == 0;
        }
    }
}
