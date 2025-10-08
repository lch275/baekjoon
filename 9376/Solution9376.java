import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution9376 {
    static int h, w;
    static char[][] map;
    static int[][] fromOutside, fromPrisoner1, fromPrisoner2;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h + 2][w + 2]; // 외부 확장
            List<int[]> prisoners = new ArrayList<>();

            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(map[i], '.'); // 외곽은 빈칸 처리
            }

            for (int i = 1; i <= h; i++) {
                String line = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = line.charAt(j - 1);
                    if (map[i][j] == '$') {
                        prisoners.add(new int[]{i, j});
                    }
                }
            }

            fromOutside = bfs(0, 0);
            fromPrisoner1 = bfs(prisoners.get(0)[0], prisoners.get(0)[1]);
            fromPrisoner2 = bfs(prisoners.get(1)[0], prisoners.get(1)[1]);

            int result = INF;

            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    if (map[i][j] == '*') continue;

                    int sum = fromOutside[i][j] + fromPrisoner1[i][j] + fromPrisoner2[i][j];
                    if (map[i][j] == '#') sum -= 2; // 문을 세 명이 중복해서 연 경우

                    result = Math.min(result, sum);
                }
            }

            System.out.println(result);
        }
    }

    static int[][] bfs(int sx, int sy) {
        int[][] dist = new int[h + 2][w + 2];
        for (int[] row : dist) Arrays.fill(row, INF);

        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx, sy});
        dist[sx][sy] = 0;

        while (!dq.isEmpty()) {
            int[] now = dq.poll();
            int x = now[0], y = now[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir], ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2) continue;
                if (map[nx][ny] == '*') continue;

                int cost = (map[nx][ny] == '#') ? 1 : 0;
                if (dist[nx][ny] > dist[x][y] + cost) {
                    dist[nx][ny] = dist[x][y] + cost;
                    if (cost == 1) dq.offerLast(new int[]{nx, ny});
                    else dq.offerFirst(new int[]{nx, ny});
                }
            }
        }
        return dist;
    }
}
