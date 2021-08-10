import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PlaygroundTree playgroundTree = new PlaygroundTree(N);
        while (--N > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(edgeInfo[0]);
            int dataB = Integer.parseInt(edgeInfo[1]);

            playgroundTree.setNodes(dataA, dataB);
        }

        System.out.println(playgroundTree.getMaxLastLeftFoot());
        br.close();
    }
}

class PlaygroundTree extends Tree {
    int[] lastLeftFootArr, lastRightFootArr;

    public PlaygroundTree(int size) {
        super(size);
        lastLeftFootArr = new int[size + 1];
        lastRightFootArr = new int[size + 1];
    }

    public int getMaxLastLeftFoot() {
        int size = getSize();
        if (size == 1) return 1;
        if (size == 2) return 0;

        Node startNode = null;
        for (int i = 1; i <= size; i++) {
            Node node = findNode(i);
            if (node.isLeafNode()) continue;
            startNode = node;
            break;
        }

        fillLastFootArrs(startNode);
        fillLastFootArrsInAllCases(startNode);

        return IntStream.of(lastLeftFootArr).max().getAsInt();
    }

    private void fillLastFootArrs(Node node) {
        int nodeData = node.getData();
        node.toggleVisit();
        for (Node adjNode : node.getAdjacentNodeList()) {
            if (adjNode.isVisited()) continue;

            int adjNodeData = adjNode.getData();
            if (adjNode.isLeafNode()) lastLeftFootArr[adjNodeData] = 1;
            else fillLastFootArrs(adjNode);

            lastLeftFootArr[nodeData] += lastRightFootArr[adjNodeData];
            lastRightFootArr[nodeData] += lastLeftFootArr[adjNodeData];
        }
        node.toggleVisit();
    }

    private void fillLastFootArrsInAllCases(Node node) {
        if (node.isVisited()) return;
        
        int nodeData = node.getData();
        int existingLeftFoot = lastLeftFootArr[nodeData];
        int existingRightFoot = lastRightFootArr[nodeData];
        lastLeftFootArr[nodeData] = lastRightFootArr[nodeData] = 0;

        for (Node adjNode : node.getAdjacentNodeList()) {
            int adjNodeData = adjNode.getData();
            lastLeftFootArr[nodeData] += lastRightFootArr[adjNodeData];
            lastRightFootArr[nodeData] += lastLeftFootArr[adjNodeData];
        }

        if (lastLeftFootArr[nodeData] != existingLeftFoot || lastRightFootArr[nodeData] != existingRightFoot) {
            lastLeftFootArr[nodeData] -= existingLeftFoot;
            lastRightFootArr[nodeData] -= existingRightFoot;
        }

        node.toggleVisit();
        for (Node adjNode : node.getAdjacentNodeList()) {
            fillLastFootArrsInAllCases(adjNode);
        }
        node.toggleVisit();
    }
}

class Tree {
    private int size;
    private Node[] nodeArr;

    public Tree(int size) {
        this.size = size;
        nodeArr = new Node[size + 1];
    }

    public int getSize() {
        return size;
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
        if (nodeArr[data] == null) nodeArr[data] = new Node(data);
        return nodeArr[data];
    }
}

class Node {
    private int data;
    private boolean isVisited = false;
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

    public void toggleVisit() {
        isVisited = !isVisited;
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

    public boolean isLeafNode() {
        return adjNodeList.size() == 1;
    }
}