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
            int N = Integer.parseInt(br.readLine());
            boolean[] isChildArr = new boolean[N + 1];
            NodeMap nodeMap = new NodeMap();
            
            int rootData = 0;
            while (--N > 0) {
                String[] nodeInfo = br.readLine().split(" ");
                int parentData = Integer.parseInt(nodeInfo[0]);
                int childData = Integer.parseInt(nodeInfo[1]);

                nodeMap.setNodes(parentData, childData);
                isChildArr[childData] = true;
                if (!isChildArr[parentData]) rootData = parentData;
            }

            nodeMap.setNodesDepth(rootData);
            
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
        return nodeMap.get(data);
    }

    public void setNodes(int parentData, int childData) {
        Node parentNode = getNode(parentData);
        Node childNode = getNode(childData);

        parentNode.addChild(childNode);
        childNode.setParent(parentNode);
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
        for (Node child : parentNode.getChildList()) {
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
    private List<Node> childList;

    public Node(int data) {
        this.data = data;
        childList = new ArrayList<>();
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

    public List<Node> getChildList() {
        return childList;
    }

    public void setParent(Node node) {
        parent = node;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addChild(Node node) {
        childList.add(node);
    }
}