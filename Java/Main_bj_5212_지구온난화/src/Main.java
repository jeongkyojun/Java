
import java.util.*;
import java.io.*;

public class Main {

	
	static int[] di = new int[] {-1,0,1,0}; //상, 좌, 우, 하
	static int[] dj = new int[] {0,-1,0,1};
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_5212.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case = 1; test_case<=T;test_case++)
		{
			System.out.println("#"+test_case);
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			char[][] mat = new char[R][C];
			char[][] tmp = new char[R][C];
			for(int i=0;i<R;i++)
			{
				String str = bf.readLine();
				mat[i] = str.toCharArray();
			}
			
			for(int i=0;i<R;i++)
			{
				for(int j=0;j<C;j++)
				{
					// 바다인경우 -> 50년 뒤에도 바다
					if(mat[i][j]=='.')
					{
						tmp[i][j] = '.';
						continue;
					}
					// 육지인 경우 계산하여 복사
					int cnt = 0;
					for(int d=0;d<4;d++)
					{
						if(0<=i+di[d]&&i+di[d]<R&&0<=j+dj[d]&&j+dj[d]<C)
						{
							if(mat[i+di[d]][j+dj[d]]=='.')
								cnt++;
						}
						else
						{
							cnt++;
						}
					}
					if(cnt>=3)
					{
						tmp[i][j] = '.';
					}
					else
					{
						tmp[i][j] = 'X';
					}
				}
			}
			
			System.out.println();
			for(int i=0;i<R;i++)
			{
				System.out.println(Arrays.toString(tmp[i]));
			}
			System.out.println();
			
			int is=0,ie=R;
			int js=0,je=C;
			
			boolean isSea = true;
			// 시작점
			for(int i=0;i<R;i++)
			{
				isSea = true;
				for(int j=0;j<C;j++)
				{
					if(tmp[i][j]=='X')
					{
						isSea = false;
						break;
					}
				}
				if(isSea)
					is++;
				else
					break;
			}
			//끝점
			for(int i=R-1;i>=is;i--)
			{
				isSea = true;
				for(int j=0;j<C;j++)
				{
					if(tmp[i][j]=='X')
					{
						isSea = false;
						break;
					}
				}
				if(isSea)
					ie--;
				else
					break;
			}
			
			// 시작점
			for(int j=0;j<C;j++)
			{
				isSea = true;
				for(int i=is;i<ie;i++)
				{
					if(tmp[i][j]=='X')
					{
						isSea = false;
						break;
					}
				}
				if(isSea)
					js++;
				else
					break;
			}
			//끝점
			for(int j=C-1;j>=js;j--)
			{
				isSea = true;
				for(int i=is;i<ie;i++)
				{
					if(tmp[i][j]=='X')
					{
						isSea = false;
						break;
					}
				}
				if(isSea)
					je--;
				else
					break;
			}

			for(int i=is;i<ie;i++)
			{
				for(int j=js;j<je;j++)
					System.out.print(tmp[i][j]);
				System.out.println();
			}
		}
	}
}
