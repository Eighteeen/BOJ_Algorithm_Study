
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
        
		if (N < 0) {
			System.out.print(32);
			return;
		}

		int cnt = 0;
		do {
			N = N / 2;
			cnt++;
		} while(N != 0 );

		System.out.print(cnt);
	}
}
