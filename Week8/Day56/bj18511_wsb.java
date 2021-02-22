import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numInfo = br.readLine().split(" ");
        String[] numArr = br.readLine().split(" ");

        System.out.print(maxNearOfCanMake(numInfo[0], stringArrToCharacterArr(numArr)));
        br.close();
    }

    static int maxNearOfCanMake(String purpose, Character[] materials){
        Arrays.sort(materials, Collections.reverseOrder());
        String maxNums = String.valueOf(materials[0]).repeat(purpose.length() - 2);
        return getMaxNear(purpose, materials, maxNums, null);
    }

    static int getMaxNear(String purpose, Character[] materials, String maxNums, String first){
        if(first != null){
            int purposeInt = Integer.parseInt(purpose);
            for(char c : materials){
                StringBuilder sb = new StringBuilder(first);
                int compare = Integer.parseInt(sb.append(c).append(maxNums).toString());
                if(purposeInt >= compare) return compare;
            }
            return 0;
        }

        int result = 0;
        char firstPurpose = purpose.charAt(0);
        for(char c : materials){
            if(firstPurpose >= c){
                result = getMaxNear(purpose, materials, maxNums, String.valueOf(c));
                if(result != 0) return result;
            }
        }
        result = Integer.parseInt(materials[0] + maxNums);

        return result;
    }

    static Character[] stringArrToCharacterArr(String[] strArr){
        int len = strArr.length;
        Character[] chrArr = new Character[len];
        for(int i = 0; i < len; i++) chrArr[i] = strArr[i].charAt(0);
        return chrArr;
    }
}