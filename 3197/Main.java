import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] lake;
    static boolean[][] swanVisited, waterVisited;
    static Queue<int[]> swanQueue = new LinkedList<>();
    static Queue<int[]> nextSwanQueue = new LinkedList<>();
    static Queue<int[]> waterQueue = new LinkedList<>();
    static Queue<int[]> nextWaterQueue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] swan1, swan2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        lake = new char[R][C];
        swanVisited = new boolean[R][C];
        waterVisited = new boolean[R][C];

        int swanCount = 0;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                lake[i][j] = line.charAt(j);

                if (lake[i][j] == 'L') {
                    if (swanCount == 0) {
                        swan1 = new int[]{i, j};
                        swanCount++;
                    } else {
                        swan2 = new int[]{i, j};
                    }
                    lake[i][j] = '.'; // 백조는 물로 처리
                }

                if (lake[i][j] == '.') {
                    waterQueue.add(new int[]{i, j});
                    waterVisited[i][j] = true;
                }
            }
        }

        // 백조의 첫 위치에서 BFS 시작
        swanQueue.add(swan1);
        swanVisited[swan1[0]][swan1[1]] = true;

        int day = 0;
        while (true) {
            if (moveSwan()) {
                System.out.println(day);
                break;
            }
            meltIce();
            swanQueue = nextSwanQueue;
            nextSwanQueue = new LinkedList<>();

            waterQueue = nextWaterQueue;
            nextWaterQueue = new LinkedList<>();
            day++;
        }
    }

    // 백조가 이동 가능한 곳까지 이동
    static boolean moveSwan() {
        while (!swanQueue.isEmpty()) {
            int[] cur = swanQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (!inRange(nx, ny) || swanVisited[nx][ny]) continue;

                swanVisited[nx][ny] = true;

                if (nx == swan2[0] && ny == swan2[1]) {
                    return true;
                }

                if (lake[nx][ny] == '.') {
                    swanQueue.add(new int[]{nx, ny});
                } else if (lake[nx][ny] == 'X') {
                    // 다음에 갈 수 있는 리스트를 저장 후, 재탐색 때 활용
                    nextSwanQueue.add(new int[]{nx, ny});
                }
            }
        }
        return false;
    }

    // 얼음 녹이기
    static void meltIce() {
        while (!waterQueue.isEmpty()) {
            int[] cur = waterQueue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (!inRange(nx, ny) || waterVisited[nx][ny]) continue;

                if (lake[nx][ny] == 'X') {
                    lake[nx][ny] = '.';
                    nextWaterQueue.add(new int[]{nx, ny});
                    waterVisited[nx][ny] = true;
                }
            }
        }
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
