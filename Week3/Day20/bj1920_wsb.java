import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//// 깔꽇맙니다 : 22 
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int A[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int targets[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int fistHigh = N - 1;
        Arrays.sort(A);

        //// 찾기에 최적화된 이진탐색을 사용하셨군요
        for(int target : targets){
            if(binarySearch(A, 0, fistHigh, target)) bw.write(1 + "\n");
            else bw.write(0 + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    //// 이진탐색 직접 구현하신거 멋쪄요 :22 원래 있는 기능 사용하신 줄 알았어요.
    static boolean binarySearch(int A[], int low, int high, int target){
        if(low > high) return false;
        int mid = (low + high) / 2;
        if(A[mid] == target) return true;
        if(A[mid] > target) return binarySearch(A, low, --mid, target);
        else return binarySearch(A, ++mid, high, target);
    }
}
