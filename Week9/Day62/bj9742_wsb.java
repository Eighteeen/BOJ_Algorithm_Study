import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;

        while((input = br.readLine()) != null){
            String[] TC = input.split(" ");
            List<Character> charList = stringToCharacterList(TC[0]);
            int wonder = Integer.parseInt(TC[1]);

            sb.append(input).append(" = ")
                .append(getPermutation(charList, wonder - 1)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static String getPermutation(List<Character> charList, int wonderIdx){
        if(charList.size() == 1) return String.valueOf(charList.get(0));
        
        int lastIdx = charList.size() - 1;
        int possibleCombination = factorial(lastIdx);
        int getIdx = wonderIdx / possibleCombination;
        if(getIdx > lastIdx) return "No permutation";

        char element = charList.get(getIdx);
        StringBuilder sb = new StringBuilder();
        sb.append(element)
            .append(getPermutation(processedList(charList, element), wonderIdx % possibleCombination));

        return sb.toString();
    }
    
    static List processedList(List original, Object removeObj){
        List result = new ArrayList<>(original);
        result.remove(removeObj);
        return result;
    }

    static List<Character> stringToCharacterList(String str){
        List<Character> charList = new ArrayList<>();
        for(char c : str.toCharArray()) charList.add(c);
        return charList;
    }

    static int factorial(int n){
        if(n == 1) return n;
        return factorial(n - 1) * n;
    }
}