import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append("     /~\\\n")
            .append("    ( oo|\n")
            .append("    _\\=/_\n")
            .append("   /  _  \\\n")
            .append("  //|/.\\|\\\\\n")
            .append(" ||  \\ /  ||\n")
            .append("============\n")
            .append("|          |\n".repeat(3));
            
        System.out.print(sb);
        br.close();
    }
}
//// ㄲㄲ