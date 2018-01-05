//Generates a Random Graph
import java.util.*;

class RandomG {

	int num;
	static ArrayList<LinkedHashMap<Integer,Integer>> al;

	RandomG(int n) {

		num=n;
		al=new ArrayList<LinkedHashMap<Integer,Integer>>(num);
		for(int i=0;i<num;i++)
			al.add(new LinkedHashMap<Integer,Integer>());
	}

	void addEdge(int a,int b,int wg) {
		LinkedHashMap lm1 = al.get(a-1);
		lm1.put(b,wg);
		LinkedHashMap lm2 = al.get(b-1);
		lm2.put(a,wg);
		al.set(a-1,lm1);
		al.set(b-1,lm2);
	}

	void printMap() {
		for(int i=0;i<num;i++){
			if(al.get(i).keySet().isEmpty() == false){
				System.out.print("\n"+(i+1)+" connected to ");//+al[i]);
				for (Integer keys : al.get(i).keySet()){
					System.out.print("-->"+keys+"("+al.get(i).get(keys)+") ");
				}
			}
		}
	}

	void dfs(int v,boolean visited[]){
		visited[v] = true;

		for(Integer key: al.get(v).keySet()){
			if(!visited[key-1]){
				//System.out.println(visited.toString());
				dfs(key-1,visited);
			}
		}

	}
	boolean checkDFS(int n){
		boolean visited[] = new boolean[n];
		dfs(0,visited);
		for(boolean x: visited){
			if(x == false) 
				return false;
		}
		return true;

	}
	public static void main(String args[]) {
		Random rd = new Random();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the number of nodes in the graph");

		int n=in.nextInt();
		RandomG obj=new RandomG(n);
		int min = n-1;
		int max = (n*(n-1))/2;
		//System.out.println("Enter number of edges");
		int k= rd.nextInt((max - min) + 1) + min;
		System.out.println("Edges= "+k);
		//System.out.println("Enter the edges 	eg: node a node b edge weight");
		int i=0;
		while(i<k) {
			int a=rd.nextInt(n)+1;
			int b=rd.nextInt(n)+1;
			int wg=rd.nextInt(50+50+1)-50;
			if(a != b && wg != 0 && al.get(a-1).keySet().contains(b) == false ){
				obj.addEdge(a,b,wg);
				i++;
			}
		}
		obj.printMap();
		System.out.println("\n connected:"+obj.checkDFS(n));

	}
}
