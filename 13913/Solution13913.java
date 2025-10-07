import java.io.*;
import java.util.*;

public class Solution13913 {
    static int MAX = 100001;
    static int[] parent = new int[MAX];
    static int[] dist = new int[MAX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, -1); // -1로 초기화 (방문하지 않음 표시)
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        dist[n] = 0;  // 시작 위치는 시간 0
        parent[n] = -1;  // 시작점의 부모는 없음

        while (!queue.isEmpty()) {
            int now = queue.poll();

            if (now == k) break;

            int[] nexts = {now - 1, now + 1, now * 2};
            for (int next : nexts) {
                if (next >= 0 && next < MAX && dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    parent[next] = now;
                    queue.add(next);
                }
            }
        }

        // 결과 출력
        System.out.println(dist[k]);

        // 경로 추적
        Stack<Integer> stack = new Stack<>();
        for (int i = k; i != -1; i = parent[i]) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
