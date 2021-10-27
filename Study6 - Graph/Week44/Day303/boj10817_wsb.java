import java.io.BufferedReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//// 깔끔
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numStrArr = br.readLine().split(" ");
        int[] nums =  {Integer.parseInt(numStrArr[0]), Integer.parseInt(numStrArr[1]), Integer.parseInt(numStrArr[2])};
        int tmp;
        
        for(int i=0; i<3; i++){
            for(int j=i+1; j<3; j++){
                if(nums[i] > nums[j]){
                    tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
        
        System.out.println(nums[1]);
        br.close();
    } 
}