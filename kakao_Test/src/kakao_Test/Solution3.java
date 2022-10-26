package kakao_Test;

public class Solution3 {
	static int head;
    static int cost;
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        int n = emoticons.length;
        int[] discount = new int[]{10,20,30,40};
        head = 0;
        cost = 0;
        
        // 4의 7제곱 = 2의 14제곱 = 1024 * 16 = 16만 , 여기에 인원수 100을 곱해도 1600만
        // 연산 1회를 1나노초라 할때 1초 = 10억
        // 10나노초로 해도 1억 = 완전탐색 가능
        subset(n,0,new int[emoticons.length],users,emoticons,discount);
        answer[0] = head; // 가입자
        answer[1] = cost; // 수익
        return answer;
    }
    /* 
     * num : 이모티콘의 수, 
     * point : 현재 지점, 
     * bits : 선택한 가격, 
     * users : 회원 정보, 
     * emoticons : 이모티콘 가격,
     * discount : 할인률
     */
    public void subset(int num, int point, int[] bits,
        int[][] users, int[] emoticons, int[] discount)
    {
    	// 종료조건 : 모든 이모티콘의 할인률을 정한 경우 
        if(point == num)
        {
            int[] usercost = new int[users.length];
            int membership = 0;
            int all_money = 0;
            // 유저 한명씩 확인한다.
            for(int i=0;i<users.length;i++)
            {
            	// 이모티콘을 확인한다.
                for(int j=0;j<num;j++)
                {
                    if(users[i][0]<=bits[j]) // 할인률이 기준 이상인 경우
                    {
                        usercost[i] += calculate(emoticons[j], bits[j]); // 구입한다
                        // 구입 가격이 기준 이상인경우
                        if(usercost[i]>=users[i][1])
                        {
                            // 모두다 내려놓고
                            usercost[i] = 0;
                            // 멤버십 가입
                            membership++;
                            break; // 종료
                        }
                    }
                }
                // 수익금 수집
                all_money += usercost[i];
                // 남은 모두를 가입시켜도 안되는 경우 종료
                // 현재 (membership) , 남은 모두 (length - (i+1)) , 현재까지의 최댓값 head
                if(membership+users.length-(i+1)<head)
                    return;
            }
            // 무사히 넘어간 경우 최댓값인지 확인하여 갱신
            if(head < membership) // 가입자가 더 많으면 묻지도 따지지도 않고
            {
                head = membership;
                cost = all_money;
            }
            else if(head == membership && cost < all_money) // 가입자가 같으면 수익을 비교해서
            {
                cost = all_money;
            }
            return; // 종료
        }

        // 배열 갱신
        int[] tmp = new int[bits.length]; // 그대로 쓰면 문제 발생 (사실 dfs라 문제 안생김 )
        for(int i=0;i<point;i++)
        {
            tmp[i] = bits[i];
        }
        
        for(int i=0;i<discount.length;i++)
        {
            tmp[point] = discount[i];   
            subset(num,point+1,tmp,users,emoticons,discount);
        }
    }
    public int calculate(int cost, int discount){
        return (cost * (100-discount) / 100);
    }
}
