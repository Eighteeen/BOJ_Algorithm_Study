import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String man = br.readLine();
        int womanN = Integer.parseInt(br.readLine());
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