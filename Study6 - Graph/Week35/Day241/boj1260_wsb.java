import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//// 깔끔해요
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] graphInfo = br.readLine().split(" ");
        int N = Integer.parseInt(graphInfo[0]);
        int M = Integer.parseInt(graphInfo[1]);
        int V = Integer.parseInt(graphInfo[2]);

        Graph graph = new Graph(N);
        while (M-- > 0) {
            String[] edgeInfo = br.readLine().split(" ");
            int dataA = Integer.parseInt(edgeInfo[0]);
            int dataB = Integer.parseInt(edgeInfo[1]);

            graph.setNodes(dataA, dataB);
        }

        graph.sortAllAdjacentNodes();
        sb.append(graph.DFS(V)).append("\n")
            .append(graph.BFS(V)).append("\n");

        System.out.println(sb);
        br.close();
    }
}

class Graph {
    private Node[] nodeArr;
    private Queue<Node> bfsQueue;

    public Graph(int size) {
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

    private void resetAllVisit() {
        for (Node node : nodeArr) {
            if (node == null) continue;
            node.resetVisit();
        }
    }

    public void sortAllAdjacentNodes() {
        for (Node node : nodeArr) {
            if (node == null) continue;
            Collections.sort(node.getAdjacentNodeList());
        }
    }

    //// 함수 분리를 정말 잘하시네요 
    public String DFS(int fromData) {
        String result = DFS(getNode(fromData)).toString();
        resetAllVisit();
        return result;
    }

    private StringBuilder DFS(Node node) {
        StringBuilder sb = new StringBuilder();
        if (node.isVisited()) return sb;
        sb.append(node.getData()).append(" ");

        node.visit();
        for (Node adjNode : node.getAdjacentNodeList()) {
            sb.append(DFS(adjNode));
        }

        return sb;
    }

    public String BFS(int fromData) {
        bfsQueue = new LinkedList<>();

        //// 사전/사후처리 얘한테 맡기니 깔끔하네요
        Node fromNode = getNode(fromData);
        fromNode.visit();
        String result = BFS(fromNode).toString();
        resetAllVisit();

        return result;
    }

    //// 재귀로 BFS를 구현한 것은 처음봐요
    private StringBuilder BFS(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.getData()).append(" ");

        for (Node adjNode : node.getAdjacentNodeList()) {
            if (!adjNode.isVisited()) bfsQueue.offer(adjNode);
            adjNode.visit();
        }

        if (bfsQueue.isEmpty()) return sb;
        else return sb.append(BFS(bfsQueue.poll()));
    }
}

class Node implements Comparable<Node> {
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

    public void visit() {
        isVisited = true;
    }

    public void resetVisit() {
        isVisited = false;
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

    @Override
    public int compareTo(Node n){
        if(this.data > n.data) return 1;
        else if(this.data < n.data) return -1;
        return 0;
    }
}