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

        System.out.print(possibleSetOfCanMake(cardList, k).size());
        br.close();
    }

    //// 이 재귀함수는 더욱 for문 쓰는 것 처럼 작성하신 것 같아 아쉽습니당.. for문 위에 선언할 지역변수를 매개변수로 옮긴 것 같아요
    //// 효율성은 조금 떨어질 수 있지만, 재귀함수를 공부하는 만큼 재귀함수를 여러 방법으로 활용해보시면 좋을 것 같아요!
    //// -> 중첩 루프를 쓸 수밖에 없는 것 같아 좀 아쉽지만 재귀함수 방법을 다른 식으로 생각해보니 코드도 간단해 보이고 특히 말씀해주신 매개변수가 깔끔해졌어요! 감사합니다!
    static Set possibleSetOfCanMake(List<String> objectList, int useNum){
        Set<String> resultSet = new HashSet<>();
        if(useNum == 1){
            objectList.forEach(o -> resultSet.add(o));
            return resultSet;
        }

        Set<String> addSet = new HashSet<>();
        for(String front : objectList){
            addSet = possibleSetOfCanMake(processedList(objectList, front), useNum - 1);
            for(String rear : addSet){
                StringBuilder addString = new StringBuilder(front).append(rear);
                resultSet.add(addString.toString());
            }
        }

        return resultSet;
    }

    static List processedList(List original, Object removeObj){
        List result = new ArrayList<>(original);
        result.remove(removeObj);
        return result;
    }
}