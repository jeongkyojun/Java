package kakao_Test;
import java.util.*;
import java.io.*;

public class Solution5 {

	public static void main(String[] args) {
		String[] commands = new String[]{"UPDATE 1 1 menu",
				"PRINT 1 4", "UNMERGE 1 4"};
		String[] answer = {};
        String[][] matrix = new String[51][51];
        String[] res = new String[commands.length];
        boolean[][] ismerge = new boolean[51][51]; // merge 여부 확인
        int[][][] merge_point = new int[51][51][2]; // merge 위치 기록
        int cnt = 0;
        for(int i=0;i<commands.length;i++)
        {
            StringTokenizer st = new StringTokenizer(commands[i]);
            String command = st.nextToken();
            System.out.print(command+"  ");
            if(command.equals("UPDATE"))
            {
                String value1 = st.nextToken();
                String value2 = st.nextToken();
                if(st.hasMoreTokens())
                {
                    int r = Integer.parseInt(value1);
                    int c = Integer.parseInt(value2);
                    matrix[r][c] = st.nextToken();
                    // 병합된 셀 확인하여 추가 입력
                    if(ismerge[r][c])
                    {
                        matrix[merge_point[r][c][0]][merge_point[r][c][1]] = matrix[r][c];
                    }
                }
                else
                {
                    for(int r=1;r<51;r++)
                    {
                        for(int c=1;c<51;c++)
                        {
                            // 어짜피 전체 다 탐색하므로 병학 확인 안함
                            if(matrix[r][c].equals(value1))
                                matrix[r][c] = value2;   
                        }
                    }
                }
            }
            else if(command.equals("MERGE"))
            {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                ismerge[r1][c1] = true;
                ismerge[r2][c2] = true;
                merge_point[r1][c1] = new int[]{r2,c2};
                merge_point[r2][c2] = new int[]{r1,c1};
                if(matrix[r1][c1] != null)
                    matrix[r2][c2] = matrix[r1][c1];
                else if(matrix[r2][c2] != null)
                    matrix[r1][c1] = matrix[r2][c2];
            }
            else if(command.equals("UNMERGE"))
            {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
            }
            else if(command.equals("PRINT"))
            {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if(matrix[r][c]== null)
                    res[cnt++] = "EMPTY";
                else
                    res[cnt++] = matrix[r][c];
            }
            System.out.println();
        } 
        answer = new String[cnt];
        for(int i=0;i<cnt;i++)
        {
            answer[i] = res[i];
        }
	}

}
