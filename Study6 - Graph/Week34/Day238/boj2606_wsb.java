import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfComputer = Integer.parseInt(br.readLine());
        int edgeNumOfComputer = Integer.parseInt(br.readLine());

        Graph graph = new Graph(numOfComputer);
        while (edgeNumOfComputer-- > 0) {
            String[] edgeOfComputers = br.readLine().split(" ");
            int computerA = Integer.parseInt(edgeOfComputers[0]);
            int computerB = Integer.parseInt(edgeOfComputers[1]);

            graph.setNodes(computerA, computerB);
        }

        //// 문제가 사이클과 전혀 상관이 없습니다!
        //// 문제의 그림1에서 정점1부터 시작해서 사이클의 개수를 구한다면 2가 나와야할겁니다
        System.out.println(graph.getNumOfCycleNode(1) - 1);
        br.close();
    }
}

class Graph {
    private Node[] nodeArr;

    public Graph(int size) {
        nodeArr = new Node[size + 1];
    }

    public Node findNode(int data) {
        return nodeArr[data];
    }

    public void setNodes(int dataA, int datB) {
        Node nodeA = getNode(dataA);
        Node nodeB = getNode(datB);

        nodeA.setAdjacentNode(nodeB);
        nodeB.setAdjacentNode(nodeA);
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }

    public int getNumOfCycleNode(int nodeData) {
        return getNumOfCycleNode(getNode(nodeData));
    }

    private int getNumOfCycleNode(Node node) {
        if (node.isVisited()) return 0;
        node.visit();

        int cntNode = 1;
        for (Node adjNode : node.getAdjacentNodeList()) {
            cntNode += getNumOfCycleNode(adjNode);
        }
        return cntNode;
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

    public void visit() {
        isVisited = true;
    }

    public List<Node> getAdjacentNodeList() {
        return adjNodeList;
    }

    public void setAdjacentNode(Node node) {
        adjNodeList.add(node);
    }

    public void removeAdjacentNode(Node node) {
        adjNodeList.remove(node);
    }
}