import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution5073 {
    public static void main(String[] args) throws Exception {
        // TODO: 문제 풀이 작성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (!line.equals("0 0 0")) {
            List<Integer> lines = Arrays.stream(line.split(" ")).map(el -> Integer.parseInt(el)).sorted(Integer::compareTo).collect(Collectors.toList());            
            if(lines.get(2) >= lines.get(0) + lines.get(1)) {
                sb.append("Invalid");
            } else {
                Set<Integer> table = new HashSet<>(lines);
                if(table.size() == 1) {
                    sb.append("Equilateral");
                } else if(table.size() == 2) {
                    sb.append("Isosceles");
                } else {
                    sb.append("Scalene");
                }
            }
            sb.append("\n");
            line = br.readLine();
        }
        System.out.println(sb.toString());
    }
}
