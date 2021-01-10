import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
    //// 깔끔합니다 굿굿~:22 :33 쪼꼬인포 변수이름 왠지모르게 귀엽다
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String chocoInfo[] = br.readLine().split(" ");
        int N = Integer.parseInt(chocoInfo[0]);
        int M = Integer.parseInt(chocoInfo[1]);

        System.out.print(N * M - 1);
    }
}