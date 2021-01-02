import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;

        while(!(str = br.readLine()).equals("EOI")){
            //// 이게 한줄로 될거란 생각을 못했네요. 와우:22
            //// contains도 몰랐어요. Java에서 제공하는 기능 정말 잘 활용하시는 것 같습니다. 배워갑니다! :22
            sb.append(str.toUpperCase().contains("NEMO") ? "Found" : "Missing").append("\n");
        }
        System.out.print(sb);
    }
}
