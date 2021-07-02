import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        NodeMap nodeMap = new NodeMap();

        while (--N > 0) {
            String[] adjacentNodeInfo = br.readLine().split(" ");
            int firstNum = Integer.parseInt(adjacentNodeInfo[0]);
            int secondNum = Integer.parseInt(adjacentNodeInfo[1]);

            nodeMap.setNodes(firstNum, secondNum);
        }

        int cntOddDepth = getCntTreeOddDepth(nodeMap.findNode(1), 0);
        System.out.println(cntOddDepth % 2 == 1 ? "Yes" : "No");

        br.close();
    }
    
    static int getCntTreeOddDepth(Node root, int untilDepth) {
        if (root.isLeafNode()) return (untilDepth % 2 == 1 ? 1 : 0);
        untilDepth++;
        
        int cntOddDepth = 0;
        for (Node node : root.getAdjacentNodeList()) {
            node.getAdjacentNodeList().remove(root);
            cntOddDepth += getCntTreeOddDepth(node, untilDepth);
        }
        
        return cntOddDepth;
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public Node findNode(int data) {
        return nodeMap.get(data);
    }

    public void setNodes(int dataA, int dataB) {
        Node nodeA = setNode(dataA);
        Node nodeB = setNode(dataB);

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
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

    public void addAdjacentNode(Node node) {
        adjacentNodeList.add(node);
    }

    public boolean isLeafNode() {
        return adjacentNodeList.isEmpty();
    }
}