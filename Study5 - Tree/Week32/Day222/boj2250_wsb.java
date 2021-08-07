import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        FrameBinaryTree frameBinaryTree = new FrameBinaryTree(N);

        boolean[] isChilNodeArr = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            int parentData = Integer.parseInt(nodeInfo[0]);
            int leftChildData = Integer.parseInt(nodeInfo[1]);
            int rightChildData = Integer.parseInt(nodeInfo[2]);

            frameBinaryTree.setNode(parentData, leftChildData, rightChildData);
            if (leftChildData != -1) isChilNodeArr[leftChildData] = true;
            if (rightChildData != -1) isChilNodeArr[rightChildData] = true;
        }

        for (int i = 1; i < N; i++) {
            if (isChilNodeArr[i]) continue;
            frameBinaryTree.setRoot(i);
            break;
        }

        int widestLevel = frameBinaryTree.getWidestLevel();
        sb.append(widestLevel)
            .append(" ")
            .append(frameBinaryTree.getWidth(widestLevel));

        System.out.println(sb);
        br.close();
    }
}

class FrameBinaryTree extends BinaryTree {
    private int[] minColOfRowArr, maxColOfRowArr;
    private int col;
    private int maxLevel;

    public FrameBinaryTree(int size) {
        super(size);
        minColOfRowArr = new int[size];
        maxColOfRowArr = new int[size];
    }

    public int getWidestLevel() {
        col = 1;
        setMinMaxCol(this.getRoot(), 0);
        
        int widestWidth = 0, widestRow = 0;
        for (int i = 0; i < maxLevel; i++) {
            int width = maxColOfRowArr[i] - minColOfRowArr[i];
            if (widestWidth < (maxColOfRowArr[i] - minColOfRowArr[i])) {
                widestWidth = width;
                widestRow = i;
            }
        }

        return widestRow + 1;
    }

    public int getWidth(int level) {
        int row = level - 1;
        return maxColOfRowArr[row] - minColOfRowArr[row] + 1;
    }

    private void setMinMaxCol(BinaryNode node, int row) {
        if (node == null) {
            maxLevel = Math.max(maxLevel, row);
            return;
        }

        setMinMaxCol(node.getLeftChild(), row + 1);
        if (minColOfRowArr[row] == 0) minColOfRowArr[row] = col;
        maxColOfRowArr[row] = col++;
        setMinMaxCol(node.getRightChild(), row + 1);
    }
}

class BinaryTree {
    private BinaryNode[] nodeArr;
    private BinaryNode root;

    public BinaryTree(int size) {
        nodeArr = new BinaryNode[size + 1];
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void setRoot(int rootData) {
        root = findNode(rootData);
    }

    public BinaryNode findNode(int data) {
        return nodeArr[data];
    }

    public void setNode(int data, int leftChildData, int rightChildData) {
        BinaryNode node = getNode(data);

        node.setLeftChild(getNode(leftChildData));
        node.setRightChild(getNode(rightChildData));
    }

    private BinaryNode getNode(int data) {
        if (data < 0) return null;

        BinaryNode node;
        if (nodeArr[data] == null) {
            node = new BinaryNode(data);
            nodeArr[data] = node;
        }
        return nodeArr[data];
    }
}

class BinaryNode {
    private int data;
    private BinaryNode leftChild;
    private BinaryNode rightChild;

    public BinaryNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public BinaryNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryNode node) {
        leftChild = node;
    }

    public BinaryNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryNode node) {
        rightChild = node;
    }
}