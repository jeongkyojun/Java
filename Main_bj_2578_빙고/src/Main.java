import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2578.txt"));
		BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test = 1; test<=T;test++)
		{
			System.out.print("#"+test+" ");
			
			boolean[][] board = new boolean[5][5];
			int[][] mat = new int[5][5];
			for(int i=0;i<5;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<5;j++)
				{
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int line = 0;
			bingo:for(int i=0;i<5;i++)
			{
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j=0;j<5;j++)
				{
					int call = Integer.parseInt(st.nextToken());
					for(int a=0;a<5;a++)
					{
						for(int b=0;b<5;b++)
						{
							if(mat[a][b] == call)
							{
								board[a][b] = true;
								line+=BINGO(board,a,b);
							}
						}
					}
					if(line==3)
					{
						System.out.println(i*5+(j+1));
						break bingo;
					}
				}
			}
		}
	}
	
	static int BINGO(boolean[][] mat,int a,int b)
	{
		int res = 0;
		boolean bingo = true;
		for(int i=0;i<5;i++) // 가로
		{
			if(!mat[a][i])
			{
				bingo = false;
				break;
			}
		}
		if(bingo)
			res++;
		bingo = true;
		for(int i=0;i<5;i++) // 세로
		{
			if(!mat[i][b])
			{
				bingo = false;
				break;
			}
		}
		if(bingo)
			res++;
		bingo = true;
		if(a+b==4)// -a+4 = b (4,0)(3,1)(2,2)(1,3)(0,4)
		{
			bingo = true;
			for(int i=0;i<5;i++) // 우하향 대각선
			{
				if(!mat[i][4-i])
				{
					bingo = false;
					break;
				}
			}
			if(bingo)
				res++;
		}
		if(a==b)
		{
			bingo = true;
			for(int i=0;i<5;i++) // 우상향 대각선
			{
				if(!mat[i][i])
				{
					bingo = false;
					break;
				}
			}
			if(bingo)
				res++;
		}
		return res;
	}

}
