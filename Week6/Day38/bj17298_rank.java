// https://www.acmicpc.net/source/25905567

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        String str = bf.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int arr[] = new int[T];

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < T; i++) {
            if (stack.isEmpty()) {
                stack.addFirst(i);
            } else if (arr[stack.peekFirst()] >= arr[i]) {
                stack.addFirst(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peekFirst()] < arr[i]) {
                    arr[stack.removeFirst()] = arr[i];
                }
                stack.addFirst(i);
            }
        }

        while(!stack.isEmpty()){
            arr[stack.removeFirst()] =-1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < arr.length; i++) {
            bw.write(arr[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}