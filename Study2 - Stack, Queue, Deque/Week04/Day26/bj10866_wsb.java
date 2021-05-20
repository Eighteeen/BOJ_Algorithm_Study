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
        private int container_front[], container_back[];
        private int frontEndIndex, backStartIndex;
        private int front, back, size;

        public WsbDeque(int size){
            frontEndIndex = size / 2;
            container_front = new int[frontEndIndex];
            container_back = new int[size % 2 == 0 ? frontEndIndex : frontEndIndex + 1];
            front = frontEndIndex--;
            back = -1;
            backStartIndex = size = 0;
        }

        public void push_front(int x){
            container_front[--front] = x;
            size++;
        }

        public void push_back(int x){
            container_back[++back] = x;
            size++;
        }

        public int pop_front(){
            int num;
            //// 2개의 배열에 담는 방식으로 구현하느라 부가적인 처리가 필요하게 된 점이 아쉽습니다
            if(isNotSizeZeroOfFront()){
                num =  pop(container_front, front);
                if(isNotMinusOne(num)) front++;
            }else{
                num = pop(container_back, backStartIndex);
                if(isNotMinusOne(num)) backStartIndex++;
            }
            return num;
        }

        public int pop_back(){
            int num;
            if(isNotSizeZeroOfBack()){
                num = pop(container_back, back);
                if(isNotMinusOne(num)) back--;
            }else{
                num =  pop(container_front, frontEndIndex);
                if(isNotMinusOne(num)) frontEndIndex--;
            }
            return num;
        }
    
        public int size() {
            return size;
        }
    
        public int empty() {
            return (isSizeZero() ? 1 : 0);
        }

        public int front(){
            return (isNotSizeZeroOfFront() ? wantNum(container_front, front) : wantNum(container_back, backStartIndex));
        }

        public int back(){
            return (isNotSizeZeroOfBack() ? wantNum(container_back, back) : wantNum(container_front, frontEndIndex));
        }

        private int pop(int container[], int index){
            if(isSizeZero()) return -1;
            else{
                int num = container[index];
                container[index] = 0;
                size--;
                return num;
            }
        }

        private int wantNum(int container[], int index){
            return (isSizeZero() ? -1 : container[index]);
        }

        private boolean isSizeZero(){
            return size == 0;
        }

        private boolean isNotSizeZeroOfFront(){
            return container_front[frontEndIndex] != 0;
        }

        private boolean isNotSizeZeroOfBack(){
            return container_back[backStartIndex] != 0;
        }

        private boolean isNotMinusOne(int num){
            return num != -1;
        }
    }
}
