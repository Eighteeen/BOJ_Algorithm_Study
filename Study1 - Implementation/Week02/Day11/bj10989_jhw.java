package Day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj10989_jhw {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(bf.readLine());
		int[] Narr = new int[N];
		
		for(int i = 0; i < N; i++) {
			Narr[i] = Integer.parseInt(bf.readLine());
        }
		
		//// 메소드 활용도 좋자만 카톡으로 소통했다싶이 구현을 하는 문제라서 직접 구현해봤으면 좋겠어요..!
		Arrays.sort(Narr);
		
		for(int i = 0 ; i < N; i++) {
			sb.append(Narr[i]).append("\n");
		}
		System.out.print(sb);
		
	
	}

}
