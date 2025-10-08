import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2933 {
    static int R, C;
    static char[][] cave;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cave = new char[R][C];
        
        for (int i = 0; i < R; i++) {
            cave[i] = br.readLine().toCharArray();
        }
        
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        boolean leftToRight = true; // 첫 번째 막대는 왼쪽에서 오른쪽으로 던짐
        
        for (int i = 0; i < N; i++) {
            int height = R - Integer.parseInt(st.nextToken()); // 높이를 배열 인덱스로 변환
            
            // 1. 막대 던져서 미네랄 파괴
            destroyMineral(height, leftToRight);
            
            // 2. 땅과 붙은 미네랄 방문 표시
            visited = new boolean[R][C];
            for (int j = 0; j < C; j++) {
                if (cave[R-1][j] == 'x' && !visited[R-1][j]) {
                    bfs(R-1, j);
                }
            }
            
            // 3. 땅에서 떨어진 클러스터 찾아서 중력 적용
            dropFloatingClusters();
            
            leftToRight = !leftToRight; // 방향 전환
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(cave[i]).append('\n');
        }
        System.out.print(sb);
    }
    
    // 막대 던져서 파괴
    static void destroyMineral(int height, boolean leftToRight) {
        if (leftToRight) {
            for (int i = 0; i < C; i++) {
                if (cave[height][i] == 'x') {
                    cave[height][i] = '.';
                    break;
                }
            }
        } else {
            for (int i = C - 1; i >= 0; i--) {
                if (cave[height][i] == 'x') {
                    cave[height][i] = '.';
                    break;
                }
            }
        }
    }
    
    // BFS로 땅과 연결된 미네랄 표시
    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0], cy = cur[1];
            
            for (int dir = 0; dir < 4; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];
                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                    if (!visited[nx][ny] && cave[nx][ny] == 'x') {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
    
    // 떠있는 클러스터를 찾아서 중력으로 아래로 떨어뜨림
    static void dropFloatingClusters() {
        List<int[]> floating = new ArrayList<>();
        
        // 1. 떠있는 미네랄 좌표 수집 (visited가 false인 x)
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (cave[i][j] == 'x' && !visited[i][j]) {
                    floating.add(new int[]{i, j});
                }
            }
        }
        
        // 떠있는 클러스터 없으면 return
        if (floating.isEmpty()) return;
        
        // 2. 클러스터의 최소 낙하 거리 계산
        int dropDistance = R; // 최대 낙하거리 초기화
        
        for (int[] cell : floating) {
            int x = cell[0];
            int y = cell[1];
            int dist = 0;
            
            // 땅이나 다른 미네랄(방문한 미네랄) 위에 떨어질 때까지 떨어짐
            for (int nx = x + 1; nx < R; nx++) {
                if (cave[nx][y] == 'x' && visited[nx][y]) {
                    break; // 다른 클러스터에 부딪힘
                }
                if (cave[nx][y] == 'x' && !visited[nx][y]) {
                    // 같은 떠있는 클러스터 미네랄임 -> 떨어질 수 있음 계속 진행
                    continue;
                }
                dist++;
            }
            dropDistance = Math.min(dropDistance, dist);
        }
        
        // 3. 클러스터 미네랄들 원래 위치에서 제거
        for (int[] cell : floating) {
            cave[cell[0]][cell[1]] = '.';
        }
        
        // 4. 클러스터를 낙하 거리만큼 아래로 이동해서 표시
        for (int[] cell : floating) {
            cave[cell[0] + dropDistance][cell[1]] = 'x';
        }
    }
}
