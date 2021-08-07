import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        IslandCountry islandCountry = new IslandCountry(N);

        String islandPopulationStr = "0 " + br.readLine();
        int[] islandPopulationArr = Arrays.stream(islandPopulationStr.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i < N; i++) {
            String[] edgeInfo = br.readLine().split(" ");
            int islandNumA = Integer.parseInt(edgeInfo[0]);
            int islandNumB = Integer.parseInt(edgeInfo[1]);

            islandCountry.setIsland(i, islandPopulationArr[i]);
            islandCountry.setIslandEdge(islandNumA, islandNumB);
        }
        islandCountry.setIsland(N, islandPopulationArr[N]);

        System.out.println(islandCountry.getMaxPopulationOfGoodIslands(1));
        br.close();
    }
}

class IslandCountry extends Tree {
    private int[] maxPopulationIfGoodIsland;
    private int[] maxPopulationIfNormalIsland;

    public IslandCountry(int size) {
        super(size);
        maxPopulationIfGoodIsland = new int[size + 1];
        maxPopulationIfNormalIsland = new int[size + 1];
    }

    public void setIslandEdge(int islandNumA, int islandNumB) {
        super.setNodes(islandNumA, islandNumB);
    }

    public void setIsland(int islandNum, int population) {
        super.setNode(islandNum, population);
    }

    public int getMaxPopulationOfGoodIslands(int islandNum) {
        setMaxPopulationArrs(super.findNode(islandNum));
        return Math.max(maxPopulationIfGoodIsland[islandNum], maxPopulationIfNormalIsland[islandNum]);
    }

    private void setMaxPopulationArrs(Node island) {
        int islandNum = island.getData();

        maxPopulationIfGoodIsland[islandNum] = island.getValue();
        for (Node adjIsland : island.getAdjacentNodeList()) {
            adjIsland.removeAdjacentNode(island);
            setMaxPopulationArrs(adjIsland);

            int adjIslandNum = adjIsland.getData();
            maxPopulationIfGoodIsland[islandNum] += maxPopulationIfNormalIsland[adjIslandNum];
            maxPopulationIfNormalIsland[islandNum] += Math.max(
                maxPopulationIfGoodIsland[adjIslandNum],
                maxPopulationIfNormalIsland[adjIslandNum]);
        }
    }
}

class Tree {
    private Node[] nodeArr;

    public Tree(int size) {
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

    public void setNode(int data, int value) {
        getNode(data).setValue(value);
    }

    private Node getNode(int data) {
        Node node;
        if (nodeArr[data] == null) {
            node = new Node(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }
}

class Node {
    private int data;
    private int value;
    private List<Node> adjNodeList;

    public Node(int data) {
        this.data = data;
        adjNodeList = new ArrayList<>();
    }

    public int getData() {
        return data;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
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