import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        NodeMap nodeMap = new NodeMap(N);
        
        while (--N > 0) {
            String[] nodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(nodeInfo[0]);
            int dataB = Integer.parseInt(nodeInfo[1]);

            nodeMap.setNodes(dataA, dataB);
        }

        nodeMap.setOneWayTree(1);

        int M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            String[] lcaNodeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(lcaNodeInfo[0]);
            int dataB = Integer.parseInt(lcaNodeInfo[1]);

            sb.append(nodeMap.getLcaData(dataA, dataB)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}

class NodeMap {
    private Map<Integer, Node> nodeMap = new HashMap<>();
    private int[] depthArr;
    private int[] parentDataArr;

    public NodeMap(int nodeSize) {
        depthArr = new int[nodeSize + 1];
        parentDataArr = new int[nodeSize + 1];
    }

    public Node findNode(int data) {
        return nodeMap.get(data);
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

    public void setOneWayTree(int rootData) {
        Node root = findNode(rootData);
        setOneWayTreeAndInfos(root);
    }

    private void setOneWayTreeAndInfos(Node parentNode) {
        int parentData = parentNode.getData();
        int childDepth = depthArr[parentData] + 1;
        for (Node child : parentNode.getAdjacentNodeList()) {
            int childData = child.getData();
            child.removeAdjacentNode(parentNode);

            depthArr[childData] = childDepth;
            parentDataArr[childData] = parentData;
            setOneWayTreeAndInfos(child);
        }
    }

    public int getLcaData(int dataA, int dataB) {
        int depthA = depthArr[dataA];
        int depthB = depthArr[dataB];
        
        if (depthA > depthB) dataA = getAncestorNode(dataA, depthA - depthB);
        else if (depthA < depthB) dataB = getAncestorNode(dataB, depthB - depthA);

        return LCA(dataA, dataB);
    }

    private int getAncestorNode(int nodeData, int ancestorStep) {
        while (ancestorStep-- > 0) {
            nodeData = parentDataArr[nodeData];
        }
        return nodeData;
    }

    private int LCA(int dataA, int dataB) {
        if (dataA == dataB) return dataA;

        int parentA = parentDataArr[dataA];
        int parentB = parentDataArr[dataB];

        return LCA(parentA, parentB);
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

    public void removeAdjacentNode(Node node) {
        adjacentNodeList.remove(node);
    }
}