import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//// 깔끔합니잉 : 22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        NodeMap nodeMap = new NodeMap();
        int N = Integer.parseInt(br.readLine());
        while (--N > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int firstNum = Integer.parseInt(nodeInfo[0]);
            int secondNum = Integer.parseInt(nodeInfo[1]);
            nodeMap.setNodes(firstNum, secondNum);
        }

        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            String[] questionInfo = br.readLine().split(" ");
            int questionNum = Integer.parseInt(questionInfo[0]);

            if (questionNum == 1) {
                int nodeData = Integer.parseInt(questionInfo[1]);
                sb.append(isCutVertex(nodeMap.findNode(nodeData)) ? "yes\n" : "no\n");
            } else {
                sb.append("yes\n");
            }
        }

        System.out.print(sb);
        br.close();
    }

    //// 본인 취향이지만 조건문 그대로 반환해도 돼유! : hsk도 알아가요~
    //// -> 그게 더 깔끔할 것 같아요! 반영합니다.
    static boolean isCutVertex(Node node) {
        return (node.getAdjacentNodeList().size() > 1);
    }
}

//// NodeMap 클래스 덕분에 더 읽기 편했어요!
class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public Node findNode(int data) {
        return nodeMap.get(data);
    }

    public void setNodes(int dataA, int dataB) {
        Node nodeA = setNode(dataA);
        Node nodeB = setNode(dataB);

        nodeA.setAdjacentNodeList(nodeB);
        nodeB.setAdjacentNodeList(nodeA);
    }

    private Node setNode (int data) {
        Node node;
        if (nodeMap.containsKey(data)) {
            node = nodeMap.get(data);
        } else {
            node = new Node(data);
            nodeMap.put(data, node);
        }
        return node;
    }
}

class Node {
    private int data;
    private List<Node> adjacentNodeList;

    public Node(int data) {
        this.data = data;
        adjacentNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public List<Node> getAdjacentNodeList() {
        return adjacentNodeList;
    }

    public void setAdjacentNodeList(Node node) {
        adjacentNodeList.add(node);
    }
}