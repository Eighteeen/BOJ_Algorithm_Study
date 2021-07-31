import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        NodeMap nodeMap = new NodeMap();
        
        while (--N > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(nodeInfo[0]);
            int dataB = Integer.parseInt(nodeInfo[1]);

            nodeMap.setNodes(dataA, dataB);
        }

        nodeMap.setNodesDepth(1);

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            String[] lcaNodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(lcaNodeInfo[0]);
            int dataB = Integer.parseInt(lcaNodeInfo[1]);

            sb.append(nodeMap.LCA(dataA, dataB).getData()).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();

    public Node findNode(int data) {
        Node node = nodeMap.get(data);
        if (node != null) return node;
        return getNode(data);
    }

    public void setNodes(int dataA, int dataB) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(dataB);

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
    }

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

    public void setNodesDepth(int rootData) {
        Node root = findNode(rootData);
        setNodesDepth(root);
    }

    private void setNodesDepth(Node parentNode) {
        int childDepth = parentNode.getDepth() + 1;
        for (Node child : parentNode.getAdjacentNodeList()) {
            child.removeAdjacentNode(parentNode);
            child.setParent(parentNode);
            child.setDepth(childDepth);
            setNodesDepth(child);
        }
    }

    public Node LCA(int dataA, int dataB) {
        Node nodeA = findNode(dataA);
        Node nodeB = findNode(dataB);
        int depthA = nodeA.getDepth();
        int depthB = nodeB.getDepth();
        
        if (depthA > depthB) nodeA = getAncestorNode(nodeA, depthA - depthB);
        else if (depthA < depthB) nodeB = getAncestorNode(nodeB, depthB - depthA);

        return LCA(nodeA, nodeB);
    }

    private Node getAncestorNode(Node node, int ancestorStep) {
        while (ancestorStep-- > 0) {
            node = node.getParent();
        }
        return node;
    }

    private Node LCA(Node nodeA, Node nodeB) {
        if (nodeA == nodeB) return nodeA;

        Node parentA = nodeA.getParent();
        Node parentB = nodeB.getParent();

        if (parentA == parentB) return parentA;
        return LCA(parentA, parentB);
    }
}

class Node {
    private int data;
    private int depth = 0;
    private Node parent;
    private List<Node> adjacentNodeList;

    public Node(int data) {
        this.data = data;
        adjacentNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public int getDepth() {
        return depth;
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getAdjacentNodeList() {
        return adjacentNodeList;
    }

    public void setParent(Node node) {
        parent = node;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addAdjacentNode(Node node) {
        adjacentNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjacentNodeList.remove(node);
    }
}