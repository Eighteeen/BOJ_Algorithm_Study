import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        List<Integer> nodeList = new ArrayList<>();

        while ((input = br.readLine()) != null) {
            int data = Integer.parseInt(input);
            nodeList.add(data);
        }

        System.out.print(convertTraversalPreorderToPostorder(nodeList));
        br.close();
    }
    
    static StringBuilder convertTraversalPreorderToPostorder(List<Integer> nodeList) {
        StringBuilder sb = new StringBuilder();

        int len = nodeList.size();
        if (len == 0) return sb;

        int rootData = nodeList.get(0);

        int rightIdx = len;
        int idx = 0;
        for (Integer nodeData : nodeList) {
            if (rootData < nodeData) {
                rightIdx = idx;
                break;
            }
            idx++;
        }
        
        sb.append(convertTraversalPreorderToPostorder(nodeList.subList(1, rightIdx)))
            .append(convertTraversalPreorderToPostorder(nodeList.subList(rightIdx, len)))
            .append(rootData).append("\n");
        return sb;
    }
}