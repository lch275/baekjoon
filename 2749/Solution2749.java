import java.io.BufferedReader;
import java.io.InputStreamReader;

public class  Solution2749 {
    static final long MOD = 1000000;
    static final long PISANO = 1500000;  // Pisano period for MOD = 10^6

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        n %= PISANO;

        long[][] result = fib(n);
        System.out.println(result[0][1]);  // F(n)
    }

    // 피보나치 수를 구하는 행렬 거듭제곱
    static long[][] fib(long n) {
        if (n == 0) return new long[][]{{0, 0}, {0, 0}};
        if (n == 1) return new long[][]{{1, 1}, {1, 0}};

        long[][] half = fib(n / 2);
        long[][] full = multiply(half, half);

        if (n % 2 == 1) {
            long[][] base = {{1, 1}, {1, 0}};
            full = multiply(full, base);
        }

        return full;
    }

    // 2x2 행렬 곱셈 (mod 적용)
    static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
        return result;
    }
}
