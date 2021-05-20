import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//// 전체적으로 깔꼼합니다
class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        System.out.print(printStarDoor(horizontalOfStarDoor(N)));

        br.close();
    }

    //// 수직으로도 수평으로도 사이즈는 같은데 sizeOfStarDoor은 어때요
    static int horizontalOfStarDoor(int n){
        return 4 * (n - 1) + 1;
    }

    //// 위의 절반, 가운데 만들고 위의 절반을 재활용하는 방식이군요. 참신하네요
    static StringBuilder printStarDoor(int horizontal){
        StringBuilder sb = new StringBuilder();
        
        List<StringBuilder> doorTopList = makeStarDoorTop(horizontal);
        doorTopList.forEach(d -> sb.append(d).append("\n"));

        StringBuilder doorBottom = new StringBuilder(sb).reverse();
        sb.append("* ".repeat(horizontal / 2)).append("*");
        sb.append(doorBottom);

        return sb;
    }

    //// 재귀함수에 대해 감을 확실히 잡으신 것 같네요!
    static List makeStarDoorTop(int horizontal){
        List<StringBuilder> doorList = new ArrayList<>();
        if(horizontal == 1) return doorList;

        doorList.add(makeEdgeOfStarDoor(horizontal));
        doorList.add(makeInsideOfStarDoor(horizontal));

        List<StringBuilder> insideDoorList = makeStarDoorTop(horizontal - 4);
        insideDoorList.forEach(sb -> sb.insert(0, "* ").append(" *"));
        doorList.addAll(insideDoorList);

        return doorList;
    }

    static StringBuilder makeEdgeOfStarDoor(int horizontal){
        StringBuilder edge = new StringBuilder();
        edge.append("*".repeat(horizontal));
        return edge;
    }

    static StringBuilder makeInsideOfStarDoor(int horizontal){
        StringBuilder inside = new StringBuilder();
        inside.append("*").append(" ".repeat(horizontal - 2)).append("*");
        return inside;
    }
}