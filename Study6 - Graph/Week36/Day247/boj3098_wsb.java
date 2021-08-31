import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] socialInfo = br.readLine().split(" ");
        int N = Integer.parseInt(socialInfo[0]);
        int M = Integer.parseInt(socialInfo[1]);

        SocialRelationship socialRelationship = new SocialRelationship(N);

        while (M-- > 0) {
            String[] friendsInfo = br.readLine().split(" ");
            int personA  = Integer.parseInt(friendsInfo[0]);
            int personB  = Integer.parseInt(friendsInfo[1]);

            socialRelationship.addFriendEachOther(personA, personB);
        }

        System.out.print(socialRelationship.getInfoAboutAddAllFriends());
        br.close();
    }
}

class SocialRelationship extends Graph<Object> {
    private int cntRelationshipConnection = 0;

    public SocialRelationship(int maxPerson) {
        super(maxPerson);
    }

    public void addFriendEachOther(int personA, int personB) {
        connectVertices(personA, personB);
        cntRelationshipConnection++;
    }

    public String getInfoAboutAddAllFriends() {
        StringBuilder sb = new StringBuilder();

        int days = 0;
        int maxConnection = maxSize * (maxSize - 1) / 2;
        Set<Integer>[] friendApplicationsIdxSetArr = new HashSet[maxSize + 1];
        int prevCntRelationshipConnection = cntRelationshipConnection;

        while (cntRelationshipConnection < maxConnection) {
            for (int i = 1; i <= maxSize; i++) {
                friendApplicationsIdxSetArr[i] = getFriendApplicationsIdxSetPerDay(vertexArr[i]);
            }

            for (int i = 1; i <= maxSize; i++) {
                for (int friendApplicationsIdx : friendApplicationsIdxSetArr[i]) {
                    if (vertexArr[i].getAdjacentVertexList().contains(vertexArr[friendApplicationsIdx])) continue;
                    addFriendEachOther(friendApplicationsIdx, i);
                }
            }

            sb.append("\n").append(cntRelationshipConnection - prevCntRelationshipConnection);
            days++;
            prevCntRelationshipConnection = cntRelationshipConnection;
        }

        sb.insert(0, days).append("\n");
        return sb.toString();
    }

    private Set<Integer> getFriendApplicationsIdxSetPerDay(Vertex<Object> person) {
        Set<Integer> friendApplicationsIdxSet = new HashSet<>();

        for (Vertex<Object> friend : person.getAdjacentVertexList()) {
            friendApplicationsIdxSet.addAll(getPersonIdxSetOfFriendApplications(person, friend));
        }

        return friendApplicationsIdxSet;
    }

    private Set<Integer> getPersonIdxSetOfFriendApplications(Vertex<Object> standardPerson, Vertex<Object> friend) {
        Set<Integer> personIdxSet = new HashSet<>();
        for (Vertex<Object> person : friend.getAdjacentVertexList()) {
            if (person == standardPerson || standardPerson.getAdjacentVertexList().contains(person)) continue;
            personIdxSet.add(person.getIndex());
        }
        return personIdxSet;
    }
}

class Graph<T> {
    protected int maxSize;
    protected Vertex<T>[] vertexArr;

    public Graph(int maxSize) {
        this.maxSize = maxSize;
        vertexArr = new Vertex[maxSize + 1];
    }

    public Vertex<T> findVertex(int vertexIdx) {
        return vertexArr[vertexIdx];
    }

    public void connectVertices(int vertexIdxA, int vertexIdxB) {
        Vertex<T> vertexA = getOrCreateVertex(vertexIdxA);
        Vertex<T> vertexB = getOrCreateVertex(vertexIdxB);

        vertexA.addAdjacentVertex(vertexB);
        vertexB.addAdjacentVertex(vertexA);
    }

    private Vertex<T> getOrCreateVertex(int vertexIdx) {
        Vertex<T> vertex;
        if (vertexArr[vertexIdx] == null) {
            vertex = new Vertex<>(vertexIdx);
            vertexArr[vertexIdx] = vertex;
        }
        return vertexArr[vertexIdx];
    }

    protected void resetAllVisit() {
        for (Vertex<T> vertex : vertexArr) {
            if (vertex == null) continue;
            vertex.resetVisit();
        }
    }
}

class Vertex<T> {
    private int idx;
    private T value;
    private boolean isVisited = false;
    private List<Vertex<T>> adjVertexList;

    public Vertex(int idx) {
        this.idx = idx;
        adjVertexList = new ArrayList<>();
    }

    public int getIndex() {
        return idx;
    }

    public T getValue() {
        return value;
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

    public int getNumOfAdjacentVertex() {
        return adjVertexList.size();
    }

    public List<Vertex<T>> getAdjacentVertexList() {
        return adjVertexList;
    }

    public void addAdjacentVertex(Vertex<T> vertex) {
        adjVertexList.add(vertex);
    }

    public void removeAdjacentVertex(Vertex<T> vertex) {
        adjVertexList.remove(vertex);
    }
}