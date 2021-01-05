import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    //// 앵간히 까다로운 문제였는데 효율성, 가독성에서 놓친 부분이 없네요 👍👍
    //// 변수명이 잘되어있어서 읽기 편했습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String man = br.readLine();
        int womanN = Integer.parseInt(br.readLine());
        //// 미리 세어두는 거 효율적으로 좋네요!
        int manLOVE[] = countLOVE(man);
        int womanLOVE[];
        int maxLOVE = -1, nowLOVE;
        String beMyWoman = null, nowWoman;

        for(int i = 0; i < womanN; i++){
            nowWoman = br.readLine();
            womanLOVE = countLOVE(nowWoman);
            nowLOVE = sumLOVE(manLOVE, womanLOVE);

            if(maxLOVE < nowLOVE){
                maxLOVE = nowLOVE;
                beMyWoman = nowWoman;
            }else if(maxLOVE == nowLOVE){
                if(beMyWoman.compareTo(nowWoman) > 0) beMyWoman = nowWoman;
            }
        }
        System.out.print(beMyWoman);
    }

    //// 한꺼번에 세는 거 효율적으로 좋네요!:22
    static int[] countLOVE(String name){
        int numLOVE[] = new int[4];
        int len = name.length();
        Arrays.fill(numLOVE, 0);

        for(int i = 0; i < len; i++){
            switch(name.charAt(i)){
                case 'L':
                    numLOVE[0]++;
                    break;
                case 'O':
                    numLOVE[1]++;
                    break;
                case 'V':
                    numLOVE[2]++;
                    break;
                case 'E':
                    numLOVE[3]++;
                    break;
            }
        }
        return numLOVE;
    }
    
    static int calcLOVE(int L, int O, int V, int E){
        return (((L+O)*(L+V)*(L+E)*(O+V)*(O+E)*(V+E)) % 100);
    }

    static int sumLOVE(int man[], int woman[]){
        int sumLOVE[] = new int[4];
        for(int i = 0; i < 4; i++){
            sumLOVE[i] = man[i] + woman[i];
        }
        return calcLOVE(sumLOVE[0], sumLOVE[1], sumLOVE[2], sumLOVE[3]);
    }
}