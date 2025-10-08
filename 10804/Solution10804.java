import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution10804 {
    public static void main(String[] args) throws Exception {
        // TODO: 문제 풀이 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> result = new ArrayList<>(20);
        for(int i = 0; i < 20; i++) {
            result.add(i + 1);
        }
        for(int i = 0; i < 10; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            swap(result, start - 1, end - 1);
        }
        System.out.println(String.join(" ", result.stream().map(el -> String.valueOf(el)).collect(Collectors.toList())));
    }

    static void swap(List<Integer> input, int start, int end) {
        if(start == end) return;
        int[] queue = new int[end - start + 1];
        for(int i = start; i <= end; i++) {
            queue[i - start] = input.get(i);
        }
        for(int i = start; i <= end; i++) {
            input.set(i, queue[queue.length - 1 - (i - start)]);
        }
    }
}
