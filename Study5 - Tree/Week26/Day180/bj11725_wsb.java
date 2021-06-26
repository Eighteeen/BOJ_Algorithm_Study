import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    static Map<Integer, Node> nodeMap = new HashMap<>();
    static int[] parentNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        parentNodes = new int[N + 1];

        for (int i = 1; i < N; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            int firstNum = Integer.parseInt(nodeInfo[0]);
            int secondNum = Integer.parseInt(nodeInfo[1]);
            setNodeMap(firstNum, secondNum);
        }

        setParentNodes(1);
        for (int i = 2; i <= N; i++) {
            sb.append(parentNodes[i]).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void setParentNodes(int root) {
        Node rootNode = nodeMap.get(root);
        for (Node childNode : rootNode.getAdjacentNodeList()) {
            int childNodeData = childNode.getData();
            if (parentNodes[childNodeData] != 0) continue;

            parentNodes[childNodeData] = root;
            setParentNodes(childNodeData);
        }
    }

    static void setNodeMap(int firstNum, int secondNum) {
        Node firstNode = setNode(firstNum);
        Node secondNode = setNode(secondNum);

        firstNode.setAdjacentNodeList(secondNode);
        secondNode.setAdjacentNodeList(firstNode);
    }

    static Node setNode (int data) {
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