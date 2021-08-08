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
    private int size;
    private Node[] nodeArr;
    private int maxDepth;
    private int[][] ancestorDataArr;
    private int[] depthArr;

    public NodeMap(int size) {
        this.size = size;
        int arrLen = size + 1;
        nodeArr = new Node[arrLen];
        maxDepth = (int) (Math.log(size) / Math.log(2));
        ancestorDataArr = new int[arrLen][maxDepth + 1];
        depthArr = new int[arrLen];
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int dataA, int dataB) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(dataB);

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }

    public void setOneWayTree(int rootData) {
        ancestorDataArr[rootData][0] = rootData;
        setOneWayTreeAndInfos(findNode(rootData));
        fillAncestorDataArr();
    }

    private void setOneWayTreeAndInfos(Node parentNode) {
        int parentData = parentNode.getData();
        int childDepth = depthArr[parentData] + 1;
        for (Node child : parentNode.getAdjacentNodeList()) {
            int childData = child.getData();
            child.removeAdjacentNode(parentNode);

            depthArr[childData] = childDepth;
            ancestorDataArr[childData][0] = parentData;
            setOneWayTreeAndInfos(child);
        }
    }

    private void fillAncestorDataArr() {
        for (int depth = 1; depth <= maxDepth; depth++) {
            for (int num = 1; num <= size; num++) {
                ancestorDataArr[num][depth] = ancestorDataArr[ancestorDataArr[num][depth - 1]][depth - 1];
            }
        }
    }

    public int getLcaData(int dataA, int dataB) {
        if (depthArr[dataA] > depthArr[dataB]) dataA = getAncestorNum(dataA, dataB);
        else if (depthArr[dataA] < depthArr[dataB]) dataB = getAncestorNum(dataB, dataA);

        return LCA(dataA, dataB);
    }

    private int getAncestorNum(int dataA, int dataB) {
        for (int i = maxDepth; i >= 0; i--) {
            if (depthArr[dataB] > depthArr[ancestorDataArr[dataA][i]]) continue;
            dataA = ancestorDataArr[dataA][i];
        }
        return dataA;
    }

    private int LCA(int dataA, int dataB) {
        if (dataA == dataB) return dataA;

        for (int i = maxDepth; i >= 0; i--) {
            if (ancestorDataArr[dataA][i] == ancestorDataArr[dataB][i]) continue;
            dataA = ancestorDataArr[dataA][i];
            dataB = ancestorDataArr[dataB][i];
        }

        return ancestorDataArr[dataA][0];
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