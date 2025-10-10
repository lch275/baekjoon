import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution23971 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int H = Integer.parseInt(inputs[0]); // 세로
        int W = Integer.parseInt(inputs[1]); // 가로
        int N = Integer.parseInt(inputs[2]); // 세로 거리두기
        int M = Integer.parseInt(inputs[3]); // 가로 거리두기

        int rows = (H + N) / (N + 1);
        int cols = (W + M) / (M + 1);

        System.out.println(rows * cols);
    }
}
