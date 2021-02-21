import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main{
    static Set<String> possibleSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        List<String> cardList = new ArrayList();
        for(int i = 0; i < n; i++) cardList.add(br.readLine());

        possibleSet = new HashSet<>();
        System.out.print(possibleSetOfCanMake(cardList, n, k, 0).size());
        br.close();
    }

    static Set possibleSetOfCanMake(List<String> objectList, int len, int useNum, int cntUse){
        boolean isUseAll = cntUse == (useNum - 1);
        if(isUseAll || len == 0){
            if(isUseAll) possibleSet.add(objectList.get(0));
            return possibleSet;
        }

        String toAdd = objectList.get(0);
        objectList.remove(0);
        for(String s : objectList){
            StringBuilder addString = new StringBuilder(toAdd).append(s);
            List<String> leftList = processedList(objectList, s, addString.toString(), 0);
            possibleSetOfCanMake(leftList, len, useNum, cntUse + 1);
        }
        objectList.add(toAdd.toString());

        if(cntUse == 0) return possibleSetOfCanMake(objectList, --len, useNum, cntUse);
        else return possibleSet;
    }

    static List processedList(List original, Object removeObj, Object addObj, int addIdx){
        List result = new ArrayList<>(original);
        result.remove(removeObj);
        result.add(addIdx, addObj);
        return result;
    }
}