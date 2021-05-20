import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//// 와우 효율성 굳! 제 코드보다 두배 효율적이에요
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

    //// 이 재귀함수는 더욱 for문 쓰는 것 처럼 작성하신 것 같아 아쉽습니당.. for문 위에 선언할 지역변수를 매개변수로 옮긴 것 같아요
    //// 효율성은 조금 떨어질 수 있지만, 재귀함수를 공부하는 만큼 재귀함수를 여러 방법으로 활용해보시면 좋을 것 같아요!
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