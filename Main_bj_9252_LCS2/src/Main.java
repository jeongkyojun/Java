import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_9252.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= t; tc++) {
			System.out.println("##" + tc);

			String A = bf.readLine();
			String B = bf.readLine();
			int[][] mat = new int[A.length()][B.length()]; // i는 A j는 B이다
			int[][] dir = new int[A.length()][B.length()]; // 추적용
			char[][] str = new char[A.length()][B.length()]; // 일치하는 단어
			for (int i = 0; i < A.length(); i++) {
				for (int j = 0; j < B.length(); j++) {
					if (i - 1 >= 0) {
						mat[i][j] = mat[i - 1][j]; // mat[i][j] = A의 i지점과 B의 j지점
						str[i][j] = str[i - 1][j];
						dir[i][j] = 1;
					}
					if (j - 1 >= 0) {
						if (mat[i][j] < mat[i][j - 1]) {
							mat[i][j] = mat[i][j - 1];
							str[i][j] = str[i][j - 1];
							dir[i][j] = 2;
						}
					}
					// i와 j-1를 비교한것과 i-1과 j를 비교한 것중 큰 값을 가져온다.
					if (A.charAt(i) == B.charAt(j)) {
						dir[i][j] = 3;
						str[i][j] = A.charAt(i);
						if (i - 1 >= 0 && j - 1 >= 0) {
							mat[i][j] = mat[i - 1][j - 1] + 1;
						} else {
							mat[i][j] = 1;
						}
					}
				}
			}
	
			System.out.println(mat[A.length() - 1][B.length() - 1]);
			if (mat[A.length() - 1][B.length() - 1] != 0)
			{
				int di = A.length()-1;
				int dj = B.length()-1;
				String res = "";
				while(di>=0 && dj>=0) {
					if(dir[di][dj]==3) {
						res+=str[di--][dj--];
					}
					// 위나 아래에서 옮겨온 단어는 포함하지 않는다
					else if(dir[di][dj]==2)
						dj--;
					else if(dir[di][dj]==1)
						di--;
					else break;
				}
				for(int i=res.length()-1;i>=0;i--) {
					System.out.print(res.charAt(i));
				}
				System.out.println();
			}
		}
	}
}
