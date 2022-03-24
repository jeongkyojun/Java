import java.util.*;
import java.io.*;
import java.math.BigInteger;

다익스트라를 공부해서 다시해보자

class pair implements Comparable<pair>
{
	int v;
	BigInteger w;
	
	pair(){this(0,0);}
	pair(int v,int w)
	{
		this.v = v;
		this.w = new BigInteger(Integer.toString(w));
	}
	pair(int v,BigInteger w)
	{
		this.v = v;
		this.w = w;
	}
	@Override
	public int compareTo(pair o) {
		// TODO Auto-generated method stub
		// 시작점 우선, 가중치 이후, 마지막으로 도착점
		return (w.subtract(o.w).equals(0))?v-o.v:w.compareTo(o.w);
	}
	
}

public class Main {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17396.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++)
		{
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			ArrayList<ArrayList<pair>> lst = new ArrayList<ArrayList<pair>>();
			PriorityQueue<pair> pq = new PriorityQueue<pair>(); 
			pq.offer(new pair(0,0));
			
			int[] look = new int[N];
			st = new StringTokenizer(bf.readLine());
			for(int i=0;i<N;i++)
			{
				lst.add(new ArrayList<pair>());
				look[i] = Integer.parseInt(st.nextToken());
			}
			
			look[N-1] = 0; // 마지막지점은 이동가능 처리
			
			boolean[] chk = new boolean[N];
			BigInteger[] vnear = new BigInteger[N];
			Arrays.fill(vnear, new BigInteger("-1"));
			vnear[0] = new BigInteger("0");
			for(int i=0;i<M;i++)
			{
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				if(look[a]==1||look[b]==1) continue;
				lst.get(a).add(new pair(b,w));
				lst.get(b).add(new pair(a,w));
				pq.offer(new pair(b,w));
				pq.offer(new pair(a,w));
			}
			
			while(!pq.isEmpty())
			{
				
				pair tmp = pq.poll();
				
				System.out.println(tmp.v+" , "+tmp.w);
				System.out.println(Arrays.toString(chk));
				if(vnear[tmp.v].compareTo(new BigInteger("-1"))!=0&&tmp.w.compareTo(vnear[tmp.v])>0) continue; // tmp.w > vnear[tmp.v]인경우 comtinue
				// no : 번호, mindistance : 최단거리
				if(chk[tmp.v]) continue; // 이미 방문했으면 제외
				
				System.out.println("1");
				
				chk[tmp.v] = true; // 방문처리
				// 단계2 :  선택된 정점을 경유지로 하여 아직 최소비용이 확정되지 않은 다른정점의 최소비용을 고려
				for (pair node : lst.get(tmp.v)) {
					if (!chk[node.v] && 
						vnear[tmp.v].compareTo(new BigInteger("-1"))!=0 &&
							(vnear[node.v].compareTo(new BigInteger("-1"))==0 || 
							vnear[node.v].compareTo(vnear[tmp.v].add(node.w))>0)) {
						vnear[node.v] = vnear[tmp.v].add(node.w);
						pq.offer(new pair(node.v,vnear[node.v]));
					}
				}
				System.out.println("2");
				System.out.println(Arrays.toString(vnear));
			}
			
			System.out.println("######");
			System.out.println(vnear[N-1]);
			System.out.println("######");
		}
	}
}
