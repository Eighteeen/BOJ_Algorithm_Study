import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] treeInfo = br.readLine().split(" ");
        int N = Integer.parseInt(treeInfo[0]);
        int R = Integer.parseInt(treeInfo[1]);

        CityTree cityTree = new CityTree(N);
        while (--N > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(edgeInfo[0]);
            int dataB = Integer.parseInt(edgeInfo[1]);
            int distance = Integer.parseInt(edgeInfo[2]);

            cityTree.setNodes(dataA, dataB, distance);
        }

        cityTree.measureTreeLen(R);
        sb.append(cityTree.getPoleLen()).append(" ").append(cityTree.getMaxBranchLen());

        System.out.println(sb);
        br.close();
    }
}

class CityTree extends NodeMap {
    private Node gigaNode;
    private int poleLen;
    private int maxBranchLen;

    public CityTree(int size) {
        super(size);
    }

    public void measureTreeLen(int rootData) {
        if (getSize() == 1) return;
        measurePoleLen(findNode(rootData));
        measureMaxBranchLen(gigaNode);
    }

    private void measurePoleLen(Node parent) {
        if (parent.isLeafNode() || parent.getAdjacentNodeSize() > 1) {
            gigaNode = parent;
            return;
        }

        for (Node child : parent.getAdjacentNodeSet()) {
            child.removeAdjacentNode(parent);
            poleLen += parent.getValue(child);
            measurePoleLen(child);
        }
    }

    private int measureMaxBranchLen(Node parent) {
        if (parent.isLeafNode()) return 0;

        int maxBranchLenFromParent = 0;
        for (Node child : parent.getAdjacentNodeSet()) {
            child.removeAdjacentNode(parent);
            maxBranchLenFromParent = Math.max(maxBranchLenFromParent, parent.getValue(child) + measureMaxBranchLen(child));
        }
        maxBranchLen = Math.max(maxBranchLen, maxBranchLenFromParent);

        return maxBranchLenFromParent;
    }

    public int getPoleLen() {
        return poleLen;
    }

    public int getMaxBranchLen() {
        return maxBranchLen;
    }
}

class NodeMap {
    private int size;
    private Node[] nodeArr;

    public NodeMap(int size) {
        this.size = size;
        nodeArr = new Node[size + 1];
    }

    public int getSize() {
        return size;
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int dataA, int dataB, int value) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(dataB);

        nodeA.addAdjacentNode(nodeB, value);
        nodeB.addAdjacentNode(nodeA, value);
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }
}

class Node {
    private int data;
    private Map<Node, Integer> edgeValueMap;

    public Node(int data) {
        this.data = data;
        edgeValueMap = new HashMap<>();
    }

    public int getData() {
        return data;
    }

    public Map<Node, Integer> getEdgeValueMap() {
        return edgeValueMap;
    }

    public Set<Node> getAdjacentNodeSet() {
        return edgeValueMap.keySet();
    }

    public int getValue(Node toNode) {
        return edgeValueMap.get(toNode);
    }

    public void addAdjacentNode(Node node, int value) {
        edgeValueMap.put(node, value);
    }

    public void removeAdjacentNode(Node node) {
        edgeValueMap.remove(node);
    }

    public int getAdjacentNodeSize() {
        return edgeValueMap.size();
    }

    public boolean isLeafNode() {
        return edgeValueMap.isEmpty();
    }
}