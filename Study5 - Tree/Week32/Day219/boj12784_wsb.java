import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String[] inhanikaInfo = br.readLine().split(" ");
            int M = Integer.parseInt(inhanikaInfo[1]);

            NodeMap nodeMap = new NodeMap();
            while (M-- > 0) {
                String[] edgeInfo = br.readLine().split(" ");
                int dataA = Integer.parseInt(edgeInfo[0]);
                int dataB = Integer.parseInt(edgeInfo[1]);
                int D = Integer.parseInt(edgeInfo[2]);

                nodeMap.setNodes(dataA, dataB, D);
            }

            sb.append(nodeMap.getMinValueToCutLeafNode(1)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public Node findNode(int data) {
        return nodeMap.get(data);
    }

    public void setNodes(int dataA, int dataB, int value) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(dataB);

        nodeA.setAdjacentNode(nodeB, value);
        nodeB.setAdjacentNode(nodeA, value);
    }

    //// 이름과 달리 노드를 get하지만은 않는 것 같습니다
    private Node getNode(int data) {
        Node node;
        if (nodeMap.containsKey(data)) {
            node = nodeMap.get(data);
        } else {
            node = new Node(data);
            nodeMap.put(data, node);
        }
        return node;
    }

    public int getMinValueToCutLeafNode(int rootData) {
        Node root = findNode(rootData);
        if (root == null) return 0;
        return getMinValueToCutLeafNode(root);
    }

    private int getMinValueToCutLeafNode(Node node) {
        int sumValue = 0;
        for (Node child : node.getAdjacentNodeSet()) {
            child.removeAdjacentNode(node);
            if (child.isLeafNode()) {
                sumValue += node.getValue(child);
                continue;
            }
            sumValue += Math.min(node.getValue(child), getMinValueToCutLeafNode(child));
        }
        return sumValue;
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