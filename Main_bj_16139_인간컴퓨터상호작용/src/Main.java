import java.io.*;
import java.util.*;

시간복잡도를 줄일수 있는방법?
public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		// a = 0x61, z = 0x61+25
		String str = bf.readLine();
		int[] top = new int[26];
		int[][] chk = new int[str.length()][26];
		for (int i = 0; i <str.length(); i++) {
			chk[top[(str.charAt(i) - 0x61)]++][(str.charAt(i) - 0x61)] = i;
		}
		int t = Integer.parseInt(bf.readLine());
		for(int i=0;i<chk.length;i++)
		{
			System.out.println(Arrays.toString(chk[i]));
		}
		StringTokenizer st;
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(bf.readLine());
			int n = st.nextToken().charAt(0)-0x61;
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int n_a=0, n_b=top[n];
			boolean key = true;
//			System.out.println(n+" , "+a+" , "+b);
			for(int j=0;j<top[n];j++) {
				if(key && a<=chk[j][n]){
					n_a = j;
					key = false;
				}
				if(b<=chk[j][n]) {
					n_b = j+1;
					break;
				};
			}
			System.out.println(n_b-n_a);
		}
	}

}
