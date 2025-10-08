import java.io.*;
import java.util.*;

public class Solution6549 {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); // 출력 저장

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 종료 조건
            if (n == 0) break;

            long[] heights = new long[n + 1]; // 마지막 0을 추가하기 위해 +1
            for (int i = 0; i < n; i++) {
                heights[i] = Long.parseLong(st.nextToken());
            }
            heights[n] = 0; // 스택을 비우기 위한 마지막 높이

            Stack<Integer> stack = new Stack<>();
            long maxArea = 0;

            for (int i = 0; i <= n; i++) {
                while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                    long height = heights[stack.pop()];
                    int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                    maxArea = Math.max(maxArea, height * width);
                }
                stack.push(i);
            }

            sb.append(maxArea).append("\n");
        }

        System.out.print(sb.toString());
    }
}
