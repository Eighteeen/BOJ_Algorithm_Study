import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            int M = Integer.parseInt(br.readLine().split(" ")[1]);
            Queue<Integer> printerQueue = new LinkedList<>(Arrays.stream(br.readLine().split(" "))
                                            .map(Integer::valueOf).collect(Collectors.toList()));

            bw.write(getPrintNum(printerQueue, M) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int getPrintNum(Queue<Integer> printer, int wonderIndex){
        int cntPrint = 0, cntPoll = 0;
        int max = Collections.max(printer);

        while(true){
            int current = printer.poll();
            if(current == max){
                cntPrint++;
                if(cntPoll == wonderIndex) return cntPrint;
                max = Collections.max(printer);
            }else{
                printer.offer(current);
                if(cntPoll == wonderIndex) wonderIndex += printer.size();
            }
            cntPoll++;
        }
    }
}