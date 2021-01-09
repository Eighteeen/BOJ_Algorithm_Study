import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//// 깔꼼쓰:22 :33 깔끔 자체네요!
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        Integer cups[] = {1, 2, 3};
        String cupPositionInfo[];
        int X, Y;
        int indexX, indexY;

        //// 전 야바위로 해석하고 풀었는데 정석으론 이렇게 짜면 됐군요
        for(int i = 0; i < M; i++){
            cupPositionInfo = br.readLine().split(" ");
            X = Integer.parseInt(cupPositionInfo[0]);
            Y = Integer.parseInt(cupPositionInfo[1]);
            indexX = Arrays.asList(cups).indexOf(X);
            indexY = Arrays.asList(cups).indexOf(Y);

            cups[indexX] = Y;
            cups[indexY] = X;
        }

        System.out.print(cups[0]);
    }
}
