// https://www.acmicpc.net/source/23868424
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.io.IOException;
 
public class Main {
	
	public static StringBuilder sb = new StringBuilder();
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine(), " ");
		
		int num = Integer.parseInt(str.nextToken());
		int target = Integer.parseInt(str.nextToken());
		
		str = new StringTokenizer(br.readLine(), " ");
		int[] card = new int[num];
		for (int i = 0; i < num; i++) 
			card[i] = Integer.parseInt(str.nextToken());
		
		
		System.out.println(find(card, num, target));
		 
	}
	
	public static int find (int[] card, int num, int target) {
		int answer = 0;
		for (int i = 0; i < num-2; i++) {
			for (int j = i + 1; j < num-1; j++) {
				for (int k = j + 1; k < num; k++) {
					int sum = card[i]+card[j]+card[k];
					if (sum == target) return sum;
					if (answer < sum && sum < target) answer = sum;
				}
			}
		}
		return answer;
	}
}