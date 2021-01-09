//문제실패:n*m크기만큼은 만들었는데 열과 행에 있는 9를 어떻게 구해서 어떻게 열에 9가 몇개 포함되는지 나타내서
// 가장 많이 포함되어있는 열 or 행을 삭제하는 법을 어떻게 접근해야될지 몰라서 실패하였습니다.
public class bj14647_jhw {

	public static void main(String[] args) {
		Scanner sc=  new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int a = 0;
		int nArr [] = new int[N];
		int mArr[] = new int[M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int number = sc.nextInt();
				int b = 0;
				nArr[i] = number;
				mArr[j] = number;
			
				if(Integer.toString(nArr[i]).contains("9")) {
					a++;
				}else if(Integer.toString(mArr[j]).contains("9")) {
					b++;
				}
				if(a>b) {
					if(Integer.toString(nArr[i]).contains("9")) {
						
					}
				}
				
			}
		}
	
		
		
	}
}
