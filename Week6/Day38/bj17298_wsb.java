import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int arr[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<Integer> stack = new Stack<>();
        int NGEArr[] = new int[N];
        Arrays.fill(NGEArr, -1);

        for(int i = 0; i < N; i++){
            while(!stack.empty()){
                int unsavedIndex = stack.peek();
                if(arr[unsavedIndex] >= arr[i]) break;
                
                NGEArr[unsavedIndex] = arr[i];
                stack.pop();
            }

            stack.push(i);
        }

        Arrays.stream(NGEArr).forEach(NGE -> sb.append(NGE).append(" "));

        System.out.print(sb);
        br.close();
    }
}