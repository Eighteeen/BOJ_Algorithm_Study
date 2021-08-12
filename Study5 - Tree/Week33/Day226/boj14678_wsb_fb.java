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

        //// 위에껀 시작노드로부터 리프까지의 경우만 체크하는거 같고 밑에껀.. 음.. 코드를 읽어도 알기가 어렵네요. 모든 경우의 수에 대한 거라곤 하는데 정말 모~든 경우의 수를 체크하는거면 위 함수를 실행할 이유도 없었을 것 같고.. 뭐지..
        //// 코딩 의도에 대한 설명이 좀 더 되어 있으면 좋을 것 같습니다. (의견임) : 22 두개 함수의 구분이 없어보여요
        //// -> 1차적으로, 두분의 로직에 비해 복잡한 로직으로 문제를 풀었다는 점에서 두분이 이해하기 어려웠을 것 같아요.
            //// 두분의 로직을 보고 새로운 방법 (사실 원초적인 로직 아이디어는 같지만 풀어놓은 방법이 다름)도 알고,
            //// 역시 한 가지 로직에만 빠지면 그리 좋지 않은 결과가 나오는 것 같습니다.
            //// 두분의 로직이 시간상이나 가독성이 훨씬 좋다고 판단되지만, 로직을 아예 바꿔버리면 두분의 로직을 따라하는 것 밖에 안 돼서
            //// 현재 로직을 유지하되 변수명, 함수명에 정보를 더 추가하여 로직을 이해하기 쉽도록 바꿔보았습니다.

        //// -> 로직 간단 설명
            //// 1. startNode를 root로 간주합니다.
            //// 2. fillLastFootArrsFromChildren()
                //// 재귀로 리프노드까지 방문 후에 리프노드부터 왼발을 시작으로 발자국을 세어줍니다.
                //// 리프노드부터 root까지 거슬러 올라가면서 발자국을 세어주기 때문에,
                //// 노드들은 자신의 자식들에 대한 발자국 정보만 가지고 있고 부모에 대한 발자국 정보는 없습니다.
            //// 3. fillLastFootArrsFromAdjNodes()
                //// 근접한 노드들로부터 발자국을 세어줍니다.
                //// 결국, 2번에서 노드들이 몰랐던 정보까지 구해주는 역할입니다.
                //// 이때, 부모였던 근접 노드는 자신의 발자국까지 모두 세어놓은 상태이기 때문에 isDuplicateFoot 이라면 자신의 정보를 뺴줍니다.
                    //// isDuplicateFoot == false 라면 startNode입니다. (부모가 없기 때문에 중복 정보 없음)
        fillLastFootArrsFromChildren(startNode);
        fillLastFootArrsFromAdjNodes(startNode);

        return IntStream.of(lastLeftFootArr).max().getAsInt();
    }

    private void fillLastFootArrsFromChildren(Node node) {
        int nodeData = node.getData();
        node.toggleVisit();
        for (Node adjNode : node.getAdjacentNodeList()) {
            if (adjNode.isVisited()) continue;

            int adjNodeData = adjNode.getData();
            if (adjNode.isLeafNode()) lastLeftFootArr[adjNodeData] = 1;
            else fillLastFootArrsFromChildren(adjNode);

            lastLeftFootArr[nodeData] += lastRightFootArr[adjNodeData];
            lastRightFootArr[nodeData] += lastLeftFootArr[adjNodeData];
        }
        node.toggleVisit();
    }

    private void fillLastFootArrsFromAdjNodes(Node node) {
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

        //// if문이 무엇을 뜻하는지 따로 변수 설정을 해주셨으면 이해하기 쉬웠을 것 같아요
        //// -> 반영하였습니다. 해당 피드백으로 반영할 때 어떻게 바꿔야 할 지 좋은 힌트가 된 것 같습니다. ㄱㅅ!
        boolean isDuplicateFoot = (lastLeftFootArr[nodeData] != existingLeftFoot || lastRightFootArr[nodeData] != existingRightFoot);
        if (isDuplicateFoot) {
            lastLeftFootArr[nodeData] -= existingLeftFoot;
            lastRightFootArr[nodeData] -= existingRightFoot;
        }

        node.toggleVisit();
        for (Node adjNode : node.getAdjacentNodeList()) {
            fillLastFootArrsFromAdjNodes(adjNode);
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