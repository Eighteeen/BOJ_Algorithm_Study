import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

class Main {
    static Map<Integer, Land> landMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] realEstateInfo = br.readLine().split(" ");
        int N = Integer.parseInt(realEstateInfo[0]);
        int Q = Integer.parseInt(realEstateInfo[1]);

        for (int i = 0; i < Q; i++) {
            int wantLandNum = Integer.parseInt(br.readLine());
            getLand(wantLandNum).occupy();
            sb.append(getOccupiedLandNum(wantLandNum)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static int getOccupiedLandNum(int num) {
        if (num == 0) return 0;

        Land land = getLand(num);
        int occupiedLandNum = getOccupiedLandNum(num / 2);

        Land occupiedAncestorLand = land.getOccupiedAncestorLand();
        if (occupiedAncestorLand != null) {
            int currentOccupiedLandNum = occupiedAncestorLand.getNum();
            return (occupiedLandNum == 0 ? currentOccupiedLandNum : occupiedLandNum);
        }

        if (occupiedLandNum == 0 && land.isOccupied()) {
            land.setOccupiedAncestorLand(land);
            return 0;
        }

        land.setOccupiedAncestorLand(landMap.get(occupiedLandNum));
        return occupiedLandNum;
    }

    static Land getLand(int num) {
        Land land;
        if (landMap.containsKey(num)) {
            land = landMap.get(num);
        } else {
            land = new Land(num);
            landMap.put(num, land);
        }
        return land;
    }
}

class Land {
    private int num;
    private boolean isOccupied = false;
    private Land occupiedAncestorLand;

    public Land(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
    
    public Land getOccupiedAncestorLand() {
        return occupiedAncestorLand;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void setOccupiedAncestorLand(Land land) {
        occupiedAncestorLand = land;
    }
}