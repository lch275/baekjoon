import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            int[] files = new int[k + 1]; // 1-indexed
            int[] sum = new int[k + 1];   // 누적합 배열

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + files[i];
            }

            int[][] dp = new int[k + 1][k + 1];

            for (int len = 2; len <= k; len++) { // 구간 길이
                for (int i = 1; i <= k - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int m = i; m < j; m++) {
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][m] + dp[m + 1][j] + sum[j] - sum[i - 1]);
                    }
                }
            }

            sb.append(dp[1][k]).append('\n');
        }

        System.out.print(sb);
    }
}
