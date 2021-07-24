import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

////깔끔해요 :22
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        NodeMap nodeMap = new NodeMap();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int[] edgeInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int nodeData = edgeInfo[0];
            nodeMap.setNode(nodeData, Arrays.copyOfRange(edgeInfo, 1, edgeInfo.length - 1));
        }

        int rootData = Integer.parseInt(br.readLine());
        nodeMap.makeNestedSetModel(rootData);

        for (Node node : nodeMap.getAscNodeList()) {
            sb.append(node.getData()).append(" ")
                .append(node.getLeftField()).append(" ")
                .append(node.getRightField()).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new TreeMap<>();
    private int nestingField;

    public Node findNode(int data) {
        return nodeMap.get(data);
    }

    public void setNode(int nodeData, int[] adjacentDataArr) {
        Node node = getNode(nodeData);

        for (int adjacentData : adjacentDataArr) {
            Node adjacentNode = getNode(adjacentData);
            node.addAdjacentNode(adjacentNode);
        }
    }

    private Node getNode (int data) {
        Node node;
        if (nodeMap.containsKey(data)) {
            node = nodeMap.get(data);
        } else {
            node = new Node(data);
            nodeMap.put(data, node);
        }
        return node;
    }

    public List<Node> getAscNodeList() {
        return nodeMap.values().stream().collect(Collectors.toList());
    }

    public void makeNestedSetModel(int rootData) {
        nestingField = 0;
        makeNestedSetModel(findNode(rootData));
    }

    private void makeNestedSetModel(Node node) {
        node.setLeftField(++nestingField);
        for (Node child : node.getAdjacentNodeSet()) {
            child.removeAdjacentNode(node);
            makeNestedSetModel(child);
        }
        node.setRightField(++nestingField);
    }
}

class Node implements Comparable<Node>{
    private int data;
    private Set<Node> adjacentNodeSet;
    private int leftField;
    private int rightField;

    public Node(int data) {
        this.data = data;
        adjacentNodeSet = new TreeSet<>();
    }

    public int getData() {
        return data;
    }

    public Set<Node> getAdjacentNodeSet() {
        return adjacentNodeSet;
    }

    public int getLeftField() {
        return leftField;
    }

    public int getRightField() {
        return rightField;
    }

    public void setLeftField(int field) {
        leftField = field;
    }

    public void setRightField(int field) {
        rightField = field;
    }

    public void addAdjacentNode(Node node) {
        adjacentNodeSet.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjacentNodeSet.remove(node);
    }

    @Override
    public int compareTo(Node n){
        if(this.data > n.data) return 1;
        else if(this.data < n.data) return -1;
        return 0;
    }
}