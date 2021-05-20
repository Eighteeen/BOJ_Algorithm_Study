import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
    //// main이 깔끔합니다!
class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        WsbStack stack = new WsbStack(N);
        Method method;
        String command[];

        for(int i = 0; i < N; i++){
            command = br.readLine().split(" ");
            //// 모든 명령이 한줄만으로 처리돼서 깔끔하네용
            //// -> 하지만 이번엔 Method invoke를 활용해봤습니다! 근데 바꾼 게 더 깔끔해보이네요 ㅎㅎ
            if(command[0].equals("push")) stack.push(Integer.parseInt(command[1]));
            else{
                method = WsbStack.class.getMethod(command[0]);
                bw.write(method.invoke(stack) + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    ////top이랑 size를 따로두니 가독성이 좋습니다만 둘다 따로 처리해야 하는건 조금 번거로울 수 있겠네요!
    //// -> 네 저도 top 하나만 두었다가 가독성 문제로 size 변수와 함께 사용했는데요, 피드백 반영해서 top만 있는 버전으로 또 작성해봤습니다!
    static class WsbStack {
        int container[];
        int top;

        public WsbStack(int size){
            container = new int[size];
            top = -1;
        }

        public void push(int x){
            container[++top] = x;
        }
    
        //// \n을 붙여 String으로 반환하는게 조금 부자연스럽게 느껴집니다
        //// -> 날카로운 지적 감사합니다! 활용성을 좀 더 생각해서 return int로 변경해봤습니다
        public int pop() {
            if(isSizeZero()) return -1;
            else{
                int num = container[top];
                container[top--] = 0;
                return num;
            } 
        }
    
        public int size() {
            return (top + 1);
        }
    
        public int empty() {
            return (isSizeZero() ? 1 : 0);
        }
    
        public int top() {
            return (isSizeZero() ? -1 : container[top]);
        }

        private boolean isSizeZero(){
            return top == -1;
        }
    }
}