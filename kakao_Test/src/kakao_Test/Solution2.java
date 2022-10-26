package kakao_Test;

import java.util.*;
import java.io.*;

class houses implements Comparable<houses>{
	int point;
	int cap;
	houses(int point, int cap){
		this.point = point;
		this.cap = cap;
	}
	@Override
	public int compareTo(houses o) {
		// TODO Auto-generated method stub
		return o.point - point;
	}
}

public class Solution2 {

	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        PriorityQueue<houses> pq_go = new PriorityQueue<houses>();
        PriorityQueue<houses> pq_back = new PriorityQueue<houses>();
        for(int i=0;i<n;i++)
        {
            if(deliveries[i]!=0)
                pq_go.offer(new houses(i,deliveries[i]));
            if(pickups[i]!=0)
                pq_back.offer(new houses(i,pickups[i]));
        }

        while(!pq_go.isEmpty() || !pq_back.isEmpty())
        {
            int cap_go = cap;   // 갈때 담을수 있는 물류의 최대
            int cap_back = cap; // 올때 수거할 수 있는 물류의 최대
            int max = -1;       // 제일 멀리 간 집
            houses tmp;         // 큐에서 꺼내는 값

            // 배달할때
            while(!pq_go.isEmpty())
            {
                tmp = pq_go.poll(); // 꺼낸다
                if(max < tmp.point) { max = tmp.point;}   // 최댓값인지 확인해서 갱신한다.
                if(tmp.cap<=cap_go) // 적재물류 확인 최대값보다 작으면
                {
                    cap_go -= tmp.cap; // 빼고 끝
                }else               // 최댓값보다 크면?
                { 
                    pq_go.offer(new houses(tmp.point, tmp.cap-cap_go));
                    cap_go = 0;
                }
                if(cap_go==0)
                    break;
            }
            while(!pq_back.isEmpty() && cap_back>0)
            {
                tmp = pq_back.poll();
                if(max<tmp.point) { max = tmp.point;}
                if(tmp.cap<=cap_back)
                {
                    cap_back -= tmp.cap; // 빼고 끝
                }else
                {
                    pq_back.offer(new houses(tmp.point, tmp.cap-cap_back));
                    cap_back = 0;
                }
                if(cap_back==0)
                    break;
            }
            answer+=(max+1)*2;
        }
        return answer;
    }
}
