package kakao_Test;

public class Solution6 {

	static int[] di = {-1, 0,0,1}; // 하 좌 우 상
    static int[] dj = {0,-1,1,0};
    static String[] ds = {"d","l","r","u"};
    static String res;
    public static void main(String[] args) {
    	int n,m,x,y,r,c,k;
    	n=3;
    	m=4;
    	x=2;
    	y=3;
    	r=3;
    	c=1;
    	k=5;
        String answer = "";
        // 길이제한 k;
        res = "impossible";
        /*
        l r u d // 왼,오,상,하 -> d l r u 순 (사전순) 
        */
        if(find_String(n,m,x,y,r,c,k,0,""))
            answer = res;
        else{ answer = "impossible";}
    }

    static boolean find_String(int n, int m, int x, int y, int r, int c, int k, int p, String str)
    {  
        if(k==p)
        {
            System.out.println(str+" "+x+" "+y+" "+r+" "+c);
            if(x==r && y==c)
            {
                System.out.println(str);
                res = str;
                return true;
            }
            return false;
        }
        for(int d=0;d<4;d++)
        {
            if(find_String(n,m,
            		x+di[d]<1?1:(x+di[d]>n?n:x+di[d]),
            		y+dj[d]<1?1:(y+dj[d]>n?n:y+dj[d]),
            				r,c,k,p,str+ds[d]))
            {
                return true;
            }
        }
        return false;
    }
}
