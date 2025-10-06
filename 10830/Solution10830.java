import java.io.*;
import java.util.StringTokenizer;

public class Solution10830 {
    static int N;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = matrixPower(A, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }

    // 행렬 곱셈 (모듈러 1000)
    static int[][] multiply(int[][] X, int[][] Y) {
        int[][] C = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += X[i][k] * Y[k][j];
                }
                C[i][j] = sum % 1000;
            }
        }

        return C;
    }

    // 행렬 거듭제곱
    static int[][] matrixPower(int[][] matrix, long power) {
        if (power == 1) return matrix;

        int[][] half = matrixPower(matrix, power / 2);
        int[][] halfSquared = multiply(half, half);

        if (power % 2 == 0) {
            return halfSquared;
        } else {
            return multiply(halfSquared, matrix);
        }
    }
}