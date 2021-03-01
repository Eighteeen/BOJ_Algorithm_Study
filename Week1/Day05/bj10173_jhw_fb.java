package Day5;

import java.util.Scanner;

public class bj10173_jhw_fb {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String str = sc.nextLine();
				////정성 인정이에요,,,!대단합니다! 그렇지만 대소문자 무시하고 할 수있는 방법이 있을 거 같아요! : 22
			//// 노가다를 해야만 풀리는 문제는 거의 없습니다.. 좀 더 깔끔하게 할 수 없을까 하는 고민을 해보셨으면 해요! : 22
			//// 한번에 짜는 게 어려우시면 노가다로 풀어보고 그 다음에 이걸 어떻게 효율적으로 할 수 있을까? 하고 고민하면서 조금씩 고쳐보시면 단계적으로 나아질 거라고 봐요
            ////->수정했습니다!
            if(str.compareToIgnoreCase("NEMO")) {
                System.out.println("Found");
            }else{
                System.out.println("Missing");
            }
		
		}

	}

}
