import java.util.*;
import java.io.*;

/*
 * 백준 . 6987 월드컵 (G5)
 */

public class Main {
	static boolean possibleA,possibleB, possible;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_6987.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.println("#"+test_case+" ");
			
			for (int line = 0; line < 4; line++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int[][] country = new int[6][3];
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 3; j++) {
						country[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				possible = false;
				setPossible(country,0,1);
				System.out.print((possible?1:0)+" ");
			}
		}
	}

	static void setPossible(int[][] country , int a, int b)
	{
		if(a==6)
		{
			possible = true;
			for(int i=0;i<6;i++)
			{
				if(country[i][0]!=0||country[i][1]!=0||country[i][2]!=0)
				{
					possible = false;
					return;
				}
			}
			return;
		}
		if(b==6) setPossible(country,a+1,a+2);
		else
		{
			// 1 - 2 3 4 5 6
			// 2 - 3 4 5 6
			// 3 - 4 5 6
			//...
			if(country[a][0]>0 && country[b][2]>0)
			{
				country[a][0]--;
				country[b][2]--;
				setPossible(country,a,b+1);
				country[a][0]++;
				country[b][2]++;
			}
			if(country[a][1]>0 && country[b][1]>0)
			{
				country[a][1]--;
				country[b][1]--;
				setPossible(country,a,b+1);
				country[a][1]++;
				country[b][1]++;
			}
			if(country[b][0]>0 && country[a][2]>0)
			{
				country[b][0]--;
				country[a][2]--;
				setPossible(country,a,b+1);
				country[b][0]++;
				country[a][2]++;
			}
		}
	}
    /*
	static void setPossible(int[][] country, int winA,int winB ,int loseA,int loseB, int cnt)
	{
		//chk[bit] = true;
		// A그룹 승리수 = B그룹 패배수, B그룹 승리수 = A그룹 패배수인경우 해당 그룹이 정답이 된다.
		if(winA==loseB && loseA==winB)
			possibleA = true;
		if(possibleA||cnt==country.length)
			return;
		// 채용했을때 (종료범위에 들지 않는 한)
		if(winA-country[cnt][0]>=loseB+country[cnt][2] && loseA-country[cnt][2]>=winB+country[cnt][0])
		{
			setPossible(country, winA-country[cnt][0], winB+country[cnt][0],
					loseA-country[cnt][2], loseB+country[cnt][2], cnt+1);
		}
		// 채용하지 않았을때
		setPossible(country, winA, winB, loseA, loseB, cnt+1);
	}
	
	static void setDrow(int[][] country, int drowA,int drowB, int cnt)
	{
		if(drowA==drowB)
			possibleB = true;
		if(possibleB||cnt==country.length)
			return;
		setDrow(country, drowA - country[cnt][1],drowB + country[cnt][1], cnt+1);
		setDrow(country, drowA, drowB,cnt+1);
	}
	*/
}
