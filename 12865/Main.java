import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int count = Integer.parseInt(stringTokenizer.nextToken());
        int size = Integer.parseInt(stringTokenizer.nextToken());
        int[] dp = new int[size + 1];
        for(int i = 0; i < count; ++i) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int w = Integer.parseInt(stringTokenizer.nextToken());
            int v = Integer.parseInt(stringTokenizer.nextToken());
            for(int j = size; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j-w] + v);
            }
        }
        System.out.println(dp[size]);
    }
}
