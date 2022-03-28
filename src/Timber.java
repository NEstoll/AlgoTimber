import java.util.ArrayList;
import java.util.Scanner;

public class Timber {
    static int[][] results;
    static ArrayList<Integer>[][] results2;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();
        input = input.substring(1, input.length()-1);
        System.out.println(getMax(input.split(" ")));
    }

    private static int getMax(String[] segments) {
        int[] log = new int[segments.length];
        results = new int[log.length][log.length];
        for (int i = 0; i < segments.length; i++) {
            log[i] = Integer.parseInt(segments[i]);
            //System.out.println(log[i]);
        }
        return timber(log, 0, log.length-1);
    }

    private static int timber(int[] log, int m, int n) {
        if (m==n) {
            return log[n];
        }
        if (m==n-1) {
            return Math.max(log[m], log[n]);
        }
        if (results[m][n] != 0) {
            return results[m][n];
        }
        results[m][n] = Math.max(log[m] + Math.min(timber(log, m+2, n), timber(log, m+1, n-1)), log[n] + Math.min(timber(log, m, n-2), timber(log, m+1, n-1)));
        return results[m][n];
    }

    private static Integer[] timber2(int[] log, int m, int n) {
        if (m==n) {
            results2[m][n].add(log[m]);
            results2[m][n].add(m);
        }
        if (m==n-1) {
            results2[m][n].add(Math.max(log[m], log[n]));
            results2[m][n].add(log[m]>log[n]?m:n);
        }
        if (results2[m][n].size() == 0) {
            results2[m][n].add(Math.max(log[m] + Math.min(timber2(log, m + 2, n)[0], timber2(log, m + 1, n - 1)[0]), log[n] + Math.min(timber2(log, m, n - 2)[0], timber2(log, m + 1, n - 1)[0])));
            //TODO add the traceback
        }
        return results2[m][n].toArray(new Integer[]{});
    }
}
