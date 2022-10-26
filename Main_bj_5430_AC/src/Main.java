import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5430.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String cmd = bf.readLine();
			boolean reverse = false;
			int delhead = 0;
			int delend = 0;
			for (int i = 0; i < cmd.length(); i++) {
				switch (cmd.charAt(i)) {
				case 'R':
					reverse = !reverse;
					break;
				case 'D':
					if (reverse)
						delend++;
					else
						delhead++;
					break;
				}
			}
			int n = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine(), ",");
			
			if(n < delhead+delend)
			{
				bw.write("error"+'\n');
				continue;
			}
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				if (i == 0 && i == n - 1) {
					String str = st.nextToken();
					arr[i] = Integer.parseInt(str.substring(1, str.length() - 1));
				} else if (i == 0) {
					String str = st.nextToken();
					arr[i] = Integer.parseInt(str.substring(1, str.length()));
				} else if (i == n - 1) {
					String str = st.nextToken();
					arr[i] = Integer.parseInt(str.substring(0, str.length() - 1));
				} else {
					arr[i] = Integer.parseInt(st.nextToken());
				}
			}
			bw.write("[");
			if (reverse) {
				for (int i = arr.length - 1 - delend; i >= delhead; i--) {
					bw.write(Integer.toString(arr[i]));
					if (i != delhead)
						bw.write(",");
				}
			} else {
				for (int i = delhead; i < arr.length - delend; i++) {
					bw.write(Integer.toString(arr[i]));
					if (i != arr.length - 1 - delend)
						bw.write(",");
				}
			}
			bw.write("]"+'\n');
		}
		bw.flush();
		bf.close();
		bw.close();
	}
}
