import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		int[] A = new int[n];
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		//System.out.println(Arrays.toString(A));
		n = Integer.parseInt(bf.readLine());
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			System.out.println(find(A, 0, A.length - 1, Integer.parseInt(st.nextToken())));
		}
	}

	static int find(int[] A, int low, int high, int n) {
		if (low > high)
			return 0;
		int mid = (low + high) / 2;
		//System.out.println(low + " , " + mid + " , " + high);
		if (A[mid] == n)
			return 1;
		else if (A[mid] > n)
			return find(A, low, mid - 1, n);
		else {
			return find(A, mid + 1, high, n);
		}
	}
}
