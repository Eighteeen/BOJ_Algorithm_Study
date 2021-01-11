
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class bj19945_kjhjhw {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        
		if(N  < 0 ) {
			System.out.println(32);
			return;
		}
		int cnt = 0;
		do {
			N = N / 2 ;
			cnt++;
		}while(N != 0 );
		System.out.println(cnt);
		
	}

}
