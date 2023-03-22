import java.util.*;
import java.io.*;

class node{
	int parent;
	int value;
	node(){
		this.parent = -1;
		this.value = -1;
	}
	node(int parent, int value){
		this.parent = parent;
		this.value = value;
	}
}

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(bf.readLine());
		node[] nodes = new node[1000001];
		for(int i=0;i<nodes.length;i++) {
			nodes[i] = new node();
		}
		nodes[1] = new node(-1,0);
		for(int i=1;i<=1000000;i++) {
			if(i+1<1000001 && 
					(nodes[i+1].value==-1 || nodes[i+1].value>nodes[i].value+1))
			{
				nodes[i+1] = new node(i,nodes[i].value+1);
			}
			if(i*2<1000001 && 
					(nodes[2*i].value==-1 || nodes[2*i].value>nodes[i].value+1))
				nodes[2*i] = new node(i,nodes[i].value+1);
			if(i*3<1000001 &&
					(nodes[3*i].value==-1 || nodes[3*i].value>nodes[i].value+1))
				nodes[3*i] = new node(i,nodes[i].value+1);
		}
		System.out.println(nodes[n].value);
		int value = n;
		for(int i=0;i<nodes[n].value;i++) {
			System.out.print(value+" ");
			value = nodes[value].parent;
		}
		System.out.println(1);
	}

}
