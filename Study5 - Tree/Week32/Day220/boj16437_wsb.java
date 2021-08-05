import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        Tree tree = new Tree(N);
        for (int i = 2; i <= N; i++) {
            String[] islandInfo = br.readLine().split(" ");
            boolean t = islandInfo[0].charAt(0) == 'S';
            int a = Integer.parseInt(islandInfo[1]);
            int p = Integer.parseInt(islandInfo[2]);

            tree.setNodes(p, i, a);
            tree.setNode(i, t);
        }

        System.out.println(tree.getCalcSpecificValueToRoot(1));
        br.close();
    }
}

class Tree {
    private Node[] nodeArr;

    public Tree(int size) {
        nodeArr = new Node[size + 1];
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int parentData, int childData, int value) {
        Node parent = getNode(parentData);
        Node child = getNode(childData);

        parent.setAdjacentNode(child, value);
    }

    public void setNode(int data, boolean isSpecific) {
        if (!isSpecific) return;
        findNode(data).specific();
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }

    public long getCalcSpecificValueToRoot(int rootData) {
        Node root = findNode(rootData);
        return getCalcSpecificValue(root);
    }

    private long getCalcSpecificValue(Node node) {
        long calcValue = 0;
        for (Node child : node.getAdjacentNodeSet()) {
            child.removeAdjacentNode(node);

            long childCalcValue = getCalcSpecificValue(child);
            calcValue += childCalcValue;

            int childValue = node.getValue(child);
            if (child.isSpecific()) {
                calcValue += childValue;
                continue;
            }

            if (childCalcValue < childValue) calcValue -= childCalcValue;
            else calcValue -= childValue;
        }
        return calcValue;
    }
}

class Node {
    private int data;
    private boolean isSpecific = false;
    private Map<Node, Integer> edgeValueMap;

    public Node(int data) {
        this.data = data;
        edgeValueMap = new HashMap<>();
    }

    public int getData() {
        return data;
    }

    public boolean isSpecific() {
        return isSpecific;
    }

    public Map<Node, Integer> getEdgeValueMap() {
        return edgeValueMap;
    }

    public void specific() {
        isSpecific = true;
    }

    public Set<Node> getAdjacentNodeSet() {
        return edgeValueMap.keySet();
    }

    public int getValue(Node toNode) {
        return edgeValueMap.get(toNode);
    }

    public void setAdjacentNode(Node node, int value) {
        edgeValueMap.put(node, value);
    }

    public void removeAdjacentNode(Node node) {
        edgeValueMap.remove(node);
    }

    public boolean isLeafNode() {
        return edgeValueMap.isEmpty();
    }
}