import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요~
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] treeInfo = br.readLine().split(" ");
        int N = Integer.parseInt(treeInfo[0]);
        int R = Integer.parseInt(treeInfo[1]);
        int Q = Integer.parseInt(treeInfo[2]);
        
        NodeMap nodeMap = new NodeMap();
        for (int i = 1; i < N; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            int firstNum = Integer.parseInt(nodeInfo[0]);
            int secondNum = Integer.parseInt(nodeInfo[1]);
            nodeMap.setNodes(firstNum, secondNum);
        }

        OneWayTree oneWayTree = new OneWayTree(nodeMap, R, N);
        while (Q-- > 0) {
            int U = Integer.parseInt(br.readLine());
            sb.append(oneWayTree.getSubTreeSize(U)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class OneWayTree {
    NodeMap nodeMap;
    int rootData;
    int[] subTreeSizeArr;

    public OneWayTree(NodeMap nodeMap, int rootData, int treeSize) {
        this.nodeMap = nodeMap;
        this.rootData = rootData;
        subTreeSizeArr = new int[treeSize + 1];
        setOneWayTreeAndSubTreeSize(findNode(rootData));
    }

    //// '단방향 트리를 설정하는 것'과 '서브트리 사이즈 구하는 것'의 연관관계가 없지 않나요??
    //// 함수 이름과 함수가 하는 일이 따로 노는 듯한 느낌입니다! : 22
    //// -> 간단하게 반영해봤습니다.
    private int setOneWayTreeAndSubTreeSize(Node node) {
        int nodeData = node.getData();
        
        int descendantSize = node.getAdjacentNodeSize();
        for (Node child : node.getAdjacentNodeList()) {
            child.removeAdjacentNode(node);
            descendantSize += setOneWayTreeAndSubTreeSize(child);
        }

        subTreeSizeArr[nodeData] = descendantSize + 1;
        return descendantSize;
    }

    public int getSubTreeSize(int ancestorData) {
        return subTreeSizeArr[ancestorData];
    }

    private Node findNode(int data) {
        return nodeMap.findNode(data);
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

    public int getAdjacentNodeSize() {
        return adjacentNodeList.size();
    }

    public void addAdjacentNode(Node node) {
        adjacentNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjacentNodeList.remove(node);
    }
}