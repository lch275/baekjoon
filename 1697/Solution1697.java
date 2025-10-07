import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution1697 {
    static int[] array = new int[100001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0});  // 시작 위치와 시간
        array[n] = 1;  // 방문 표시 (0은 미방문)

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int location = current[0];
            int time = current[1];

            if (location == k) {
                System.out.println(time);
                return;
            }

            // 세 가지 이동 방식
            int[] nextPositions = {location - 1, location + 1, location * 2};

            for (int next : nextPositions) {
                if (next >= 0 && next < 100001 && array[next] == 0) {
                    array[next] = 1; // 방문 체크
                    queue.add(new int[]{next, time + 1});
                }
            }
        }
    }
}
