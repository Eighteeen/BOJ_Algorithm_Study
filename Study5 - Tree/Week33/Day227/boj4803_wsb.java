import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCnt = 0;
        while (true) {
            String[] treeInfo = br.readLine().split(" ");
            int n = Integer.parseInt(treeInfo[0]);
            int m = Integer.parseInt(treeInfo[1]);
            if (n == 0 && m == 0) break;

            NodeMap nodeMap = new NodeMap(n);
            while (m-- > 0) {
                String[] edgeInfo = br.readLine().split(" ");
                int dataA = Integer.parseInt(edgeInfo[0]);
                int dataB = Integer.parseInt(edgeInfo[1]);

                nodeMap.setNodes(dataA, dataB);
            }

            testCnt++;
            int numOfTrees = nodeMap.getNumOfTrees();
            //// String.format 알아갑니다 굳굳
            if (numOfTrees == 0) sb.append(String.format("Case %d: No trees.\n", testCnt));
            else if (numOfTrees == 1) sb.append(String.format("Case %d: There is one tree.\n", testCnt));
            else sb.append(String.format("Case %d: A forest of %d trees.\n", testCnt, numOfTrees));
        }

        System.out.print(sb);
        br.close();
    }
}

class NodeMap {
    private int size;
    private Node[] nodeArr;

    public NodeMap(int size) {
        this.size = size;
        nodeArr = new Node[size + 1];
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

    public int getNumOfTrees() {
        int numOfTrees = 0;
        for (int i = 1; i <= size; i++) {
            Node node = nodeArr[i];
            if (node == null || (!node.isVisited() && node.isOneCycleNode())) {
                numOfTrees++;
                continue;
            }
            if (isTreeNode(node)) numOfTrees++;
        }
        return numOfTrees;
    }

    private boolean isTreeNode(Node node) {
        if (node.isVisited()) return false;

        node.visit();
        for (Node adjNode : node.getAdjacentNodeList()) {
            adjNode.removeAdjacentNode(node);

            if (isTreeNode(adjNode)) continue;
            return false;
        }

        return true;
    }
}

class Node {
    private int data;
    private boolean isVisited;
    private List<Node> adjNodeList;

    public Node(int data) {
        this.data = data;
        adjNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void visit() {
        isVisited = true;
    }

    public List<Node> getAdjacentNodeList() {
        return adjNodeList;
    }

    public void addAdjacentNode(Node node) {
        adjNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjNodeList.remove(node);
    }

    public boolean isOneCycleNode() {
        return adjNodeList.isEmpty();
    }
}