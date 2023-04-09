import java.io.*;
import java.util.*;
import java.math.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		BigInteger[][] mat = new BigInteger[101][101];
		/*
		 * [n][1] = n
		 * [n-1][r-1] + [n-1][r] = [n][r]
		 */
		for(int i=0;i<101;i++)
		{
			for(int j=0;j<101;j++)
				mat[i][j] = new BigInteger("0");
		}
		for(int i=1;i<=100;i++)
		{
			for(int j=1;j<=i;j++) {
				if(j==1)
					mat[i][j] = new BigInteger(Integer.toString(i));
				else
					mat[i][j] = mat[i-1][j-1].add(mat[i-1][j]);
			}
		}
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		System.out.println(mat[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].toString());
	}

}
