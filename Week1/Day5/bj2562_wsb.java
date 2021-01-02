import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//// 무난하게 잘 짜신거ㅓ 같아요.
//// 가독성이 좋고 문제도 쉬워서 10초만에 코드가 읽혔습니다.
//// 효율성 면에서도 이 코드보다 더 효율적인건 생각나는게 없네요.
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int now;
        int max = -1;
        int index = 0, maxIndex = 0;

        for(int i = 0; i < 9; i++){
            now = Integer.parseInt(br.readLine());
            index += 1;
            if(max < now){
                max = now;
                maxIndex = index;
            }
        }

        sb.append(max)
            .append("\n")
            .append(maxIndex);
        System.out.print(sb);
    }
}
