import java.util.*;
import java.io.*;
/*
 * 한줄 씩 지형 차이에 따른 그래프를 만든다
 * 1. 각 지형 높이를 나타내는 그래프
 * 2. 해당 높이의 지형이 얼마나 길게 되어있는지 나타내는 그래프
 * 
 * Ex) 3 2 2 1 1 2 3 의 경우
 * 1. [3,2,1,2,3]
 * 2. [1,2,2,1,1]
 * 과 같은 2개의 그래프가 만들어진다.
 * 
 * 이후, n 지점과 n+1 지점 간 그래프를 비교하여 두 지점의 높이 차이가 1인지를 비교한다.
 * 높이 차이가 1이 된다면 이후 2번 배열의 작은쪽 인덱스값을 경사로 길이 l 만큼 뺀다.
 * 만약 0미만으로 된다면 개수에서 제외한다.
 * 
 * 위의 과정을 모든 세로줄과 모든 가로줄에 적용한다.
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14890.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		for(int test_case=1;test_case<=T;test_case++)
		{
			System.out.print("#"+test_case+" ");
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int[][] mat = new int[N][N];
			for(int i=0;i<N;i++)
			{
				st = new StringTokenizer(bf.readLine());
				for(int j=0;j<N;j++)
				{
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			// 가로 줄
			chk:for(int i=0;i<N;i++)
			{
				int[] line = new int[N+1]; // 각 높이 별 길이
				int[] type = new int[N+1]; // 인덱스 별 높이 간소화
				line[0] = 1;
				line[1] = 1;
				type[1] = mat[i][0];
				
				// 그래프 완성
				for(int j=0;j<N-1;j++)
				{
					if(mat[i][j]==mat[i][j+1])
						line[line[0]]++;
					else
					{
						line[++line[0]]++;
						type[line[0]] = mat[i][j+1];
					}
				}
				
				//System.out.println(Arrays.toString(type)+" "+Arrays.toString(line));
				// 그래프를 이용한 참 거짓 계산
				if(find_set(line,type,l))
					cnt++;
			}
			
			//세로줄
			chk:for(int i=0;i<N;i++)
			{
				int[] line = new int[N+1];
				int[] type = new int[N+1];
				line[0] = 1;
				line[1] = 1;
				type[1] = mat[0][i];
				for(int j=0;j<N-1;j++)
				{
					if(mat[j][i]==mat[j+1][i])
						line[line[0]]++;
					else
					{
						line[++line[0]]++;
						type[line[0]] = mat[j+1][i];
					}
				}
				//System.out.println(Arrays.toString(type)+" "+Arrays.toString(line));	
				//System.out.println(i+" ok");
				if(find_set(line,type,l))
					cnt++;
			}
			System.out.println(cnt);
		}
	}
	
	static boolean find_set(int[] line, int[] type, int l)
	{
		for(int j=1;j<line[0];j++)
		{
			// 경사가 1일때
			if(type[j]-type[j+1]==1||type[j]-type[j+1]==-1)
			{
				if(type[j]>type[j+1])
				{
					line[j+1]-=l;
					if(line[j+1]<0)
						return false;
				}
				else
				{
					line[j]-=l;
					if(line[j]<0)
						return false;
				}
			}
			else return false;
		}
		
		return true;
	}
}
