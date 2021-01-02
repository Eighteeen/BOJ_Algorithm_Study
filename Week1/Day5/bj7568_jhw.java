package firstDay;

import java.util.Scanner;

class Person {
	int weight;
	int height;
	int rank= 1;
	Person(int weight, int height) {
		this.weight = weight;
		this.height = height;
	}
}

public class bj7568_jhw {
//이거는 잘 모르겠어서 구글링했습니다.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		Person[] p = new Person[num];
		for(int i = 0 ; i < p.length;i++) {
			int weight = sc.nextInt();
			int height = sc.nextInt();
			p[i] = new Person(weight,height);
		
			}
		for(int i = 0; i< p.length;i++) {
		    for(int j = 0; j < p.length;j++) {
			    if(i==j) {
				    continue;
			    }
			    else if( p[j].weight >p[i].weight &&  p[j].height > p[i].height) {
				    p[i].rank++;
			    }
		    }
		}
		for(int i = 0 ; i < p.length; i++) {
			System.out.println(p[i].rank+" ");
		}
		
		
		
	

	}

}
