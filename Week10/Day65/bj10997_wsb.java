import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        System.out.print(printStarSnail(N));

        br.close();
    }

    static String leftOfSnail;
    static String rightOfSnail;
    static String firstRightOfSnail;

    static String printStarSnail(int n){
        if(n == 1) return "*\n";

        StringBuilder sb = new StringBuilder();
        leftOfSnail = "* ";
        rightOfSnail = " *";
        firstRightOfSnail = "**";

        List<StringBuilder> snailBody = makeStarSnail(horizontalOfStarSnail(n));
        snailBody.forEach(s -> sb.append(s).append("\n"));
        return sb.toString();
    }

    static int horizontalOfStarSnail(int n){
        return (4 * n - 3);
    }

    static List makeStarSnail(int horizontal){
        List<StringBuilder> snailBody = new ArrayList<>();
        String topBottomOfSnail = "*".repeat(horizontal);

        if(horizontal == 1){
            for(int i = 0; i < 3; i++) snailBody.add(new StringBuilder(topBottomOfSnail));
            return snailBody;
        }

        makeTopOfSnail(snailBody, topBottomOfSnail);
        connectInsideBody(snailBody, makeStarSnail(horizontal -= 4), Math.max(0, horizontal - 2));  
        makeBottomOfSnail(snailBody, topBottomOfSnail, horizontal);

        return snailBody;
    }

    static void makeTopOfSnail(List<StringBuilder> snailBody, String top){
        snailBody.add(new StringBuilder(top));
        snailBody.add(new StringBuilder(leftOfSnail));
    }

    static void makeBottomOfSnail(List<StringBuilder> snailBody, String bottom, int distance){
        StringBuilder aboveBottom = new StringBuilder(leftOfSnail).append(" ".repeat(distance)).append(rightOfSnail);
        snailBody.add(aboveBottom);
        snailBody.add(new StringBuilder(bottom));
    }

    static void connectInsideBody(List<StringBuilder> snailBody, List<StringBuilder> insideBody, int distance){
        StringBuilder firstInside = insideBody.remove(0).insert(0, leftOfSnail).append(firstRightOfSnail);
        snailBody.add(firstInside);

        StringBuilder secondInside = insideBody.remove(0).insert(0, leftOfSnail).append(" ".repeat(distance)).append(rightOfSnail);
        snailBody.add(secondInside);
        
        insideBody.forEach(s -> s.insert(0, leftOfSnail).append(rightOfSnail));
        snailBody.addAll(insideBody);
    }
}