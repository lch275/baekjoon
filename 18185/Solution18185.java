import java.util.Scanner;

public class Solution18185 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 공장 수
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }

        int cost = 0;

        for (int i = 0; i < N; i++) {
            if (i + 2 < N && A[i + 1] > A[i + 2]) {
                int min = Math.min(A[i], A[i + 1] - A[i + 2]);
                int twoBuy = min;
                A[i] -= twoBuy;
                A[i + 1] -= twoBuy;
                cost += 5 * twoBuy;
            }

            if (i + 2 < N) {
                int min = Math.min(A[i], Math.min(A[i + 1], A[i + 2]));
                A[i] -= min;
                A[i + 1] -= min;
                A[i + 2] -= min;
                cost += 7 * min;
            }

            if (i + 1 < N) {
                int min = Math.min(A[i], A[i + 1]);
                A[i] -= min;
                A[i + 1] -= min;
                cost += 5 * min;
            }

            cost += 3 * A[i];
            A[i] = 0;
        }

        System.out.println(cost);
    }
}
