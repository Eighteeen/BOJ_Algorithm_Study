import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

//// 깔꼼해요~
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if (input.equals("-1")) break;

            String[] gamePieceInfo = input.split(",");
            int n = Integer.parseInt(gamePieceInfo[0]);
            int m = Integer.parseInt(gamePieceInfo[1]);
            int k = Integer.parseInt(gamePieceInfo[2]);
            
            sb.append(input + ": ")
            	.append(numOfWhitePieceAfterPlace(n, m, k))
            	.append("\n");
        }

        System.out.println(sb);
        br.close();
    }
    
    static int numOfWhitePieceAfterPlace(int totalPiece, int whitePiece, int placeLoop) {
    	BigInteger bitmask = BigInteger.ZERO;
    	for (int i = 0; i < whitePiece; i++) {
    		bitmask = bitmask.or(BigInteger.ONE.shiftLeft(i));
    	}
    	
        //// 오.. BitInteger가 비트연산도 지원하는군요. 배워갑니다
    	for (int i = 0; i < placeLoop; i++) {
    		BigInteger lastPiece = bitmask.and(BigInteger.ONE).shiftLeft(totalPiece - 1);
    		bitmask = bitmask.xor(bitmask.shiftRight(1).or(lastPiece));
    	}
    	
    	return bitmask.bitCount();
    }
}
