// 문제 실패 - 모범답안
// 전체적인 로직 이해
  // 1. 입력은 arr에 받고, NGE(N)을 담는 배열 NGEArr을 미리 만들어 -1로 채워둠
  // 2. i=0~N-1까지 아래를 반복함
    // 대기시켜둔게 있다면 아래를 반복한다.
      // arr[i]이 대기시켜둔 놈의 오큰수가 될 수 있다면 NGEArr에 채워넣어주고 stack.pop()한다.
      // 될 수 없으면 만다
    // 오큰수를 구할 놈들의 NGEArr의 인덱스=i를 차례로 대기시켜둔다.
// 입력에 비례하는 O(N)의 선형 알고리즘이기 때문에 효율적이다.
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