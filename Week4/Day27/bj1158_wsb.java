import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int orders[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Queue<Integer> personQueue = new LinkedList<>();

        for(int i = 1; i <= orders[0]; i++){
            personQueue.add(i);
        }

        bw.write("<");
        while(personQueue.size() != 1){
            for(int i = 1; i < orders[1]; i++){
                personQueue.add(personQueue.remove());
            }
            bw.write(personQueue.remove() + ", ");
        }
        bw.write(personQueue.remove() + ">");

        bw.flush();
        br.close();
        bw.close();
    }
}