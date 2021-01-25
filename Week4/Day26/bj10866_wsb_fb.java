import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
    //// 저도 두개로 구현했지만 한개가 더 깔끔할거 같긴 해요!
class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        WsbDeque deque = new WsbDeque(N);
        Method method;
        String command[];

        //// Reflection 적용 굳굳!
        for(int i = 0; i < N; i++){
            command = br.readLine().split(" ");
            if(command[0].equals("push_front")) deque.push_front(Integer.parseInt(command[1]));
            else if(command[0].equals("push_back")) deque.push_back(Integer.parseInt(command[1]));
            else{
                method = WsbDeque.class.getMethod(command[0]);
                bw.write(method.invoke(deque) + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static class WsbDeque {
        //// 원형 큐 구현 원리를 이용하면 배열 하나로도 구현할 수 있습니다!
        //// -> 팁 감사합니당 바꿔봤어요
        private int container[];
        private int front, size, max;

        public WsbDeque(int size){
            container = new int[size];
            max = size;
            front = size = 0;
        }

        public void push_front(int x){
            front = (front + max - 1) % max;
            container[front] = x;
            size++;
        }

        public void push_back(int x){
            container[(front + size) % max] = x;
            size++;
        }

        public int pop_front(){
            //// 2개의 배열에 담는 방식으로 구현하느라 부가적인 처리가 필요하게 된 점이 아쉽습니다
            //// -> 바꿔봤습니당
            if(isSizeZero()) return  -1;
            else{
                int num = container[front % max];
                container[front % max] = 0;
                front = (front + max + 1) % max;
                size--;
                return num;
            }
        }

        public int pop_back(){
            if(isSizeZero()) return  -1;
            else{
                int index = (front + size - 1) % max;
                int num = container[index];
                container[index] = 0;
                size--;
                return num;
            }
        }
    
        public int size() {
            return size;
        }
    
        public int empty() {
            return (isSizeZero() ? 1 : 0);
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
            else index = (front + size - 1) % max;

            if(isSizeZero()) return -1;
            else return container[index];
        }

        private boolean isSizeZero(){
            return size == 0;
        }
    }
}