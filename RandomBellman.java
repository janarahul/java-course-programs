import java.util.*;

class RandomBellman {

	int num;
	static ArrayList<LinkedHashMap<Integer,Integer>> al;

	RandomBellman(int n) {

		num=n;
		al=new ArrayList<LinkedHashMap<Integer,Integer>>(num);
		for(int i=0;i<num;i++)
			al.add(new LinkedHashMap<Integer,Integer>());
	}

	void addEdge(int a,int b,int wg) {
		LinkedHashMap lm1 = al.get(a-1);
		lm1.put(b,wg);
		//LinkedHashMap lm2 = al.get(b-1);
		//lm2.put(a,wg);
		al.set(a-1,lm1);
		//al.set(b-1,lm2);
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
	boolean checkDFS(int z,int n){
		boolean visited[] = new boolean[n];
		dfs(z,visited);
		for(boolean x: visited){
			if(x == false) 
				return false;
		}
		return true;

	}
	void createRandomGraph(int n,int k,Random rd,RandomBellman obj) {
		int i=0;
		while(i<k) {
			int a=rd.nextInt(n)+1;
			int b=rd.nextInt(n)+1;
			int wg=rd.nextInt(50+50+1)-5;
			if(a != b && wg != 0 && al.get(a-1).keySet().contains(b) == false ){
				obj.addEdge(a,b,wg);
				i++;
			}
		}
		obj.printMap();
		for(int z=0;z<n;z++) {
			if (!obj.checkDFS(z,n)){
				System.out.println("not connected" );
				System.exit(1);
		
			}
		}		
		System.out.println("\n connected: true");
		
			//createRandomGraph(n,k,rd,obj);


	}
	void bellmanFord(int src, int n, int k) {
		int dist[] = new int[n];
		int p[] = new int[n];
		for (int i=0; i<n; ++i){
			dist[i] = 999;
			p[i] = -1;
		}
		dist[src] = 0;
		for(int i=1;i<n;i++){
			for(int j=0;j<n;j++){
				for(Integer key : al.get(j).keySet()){
					int u =j;
					int v = key-1;
					int w = al.get(j).get(key);
					if(dist[u]!= 999 && (dist[u]+w )< dist[v]){
						dist[v] = dist[u]+w;
						p[v] = u;
					}
				}
			}
		}
		for(int j=0;j<n;j++){
			for(Integer key : al.get(j).keySet()){
				int u =j;
				int v = key-1;
				int w = al.get(j).get(key);
				if(dist[u]+w < dist[v]){
				//if(dist[u]!= Integer.MAX_VALUE && dist[u]+w < dist[v]){
					dist[v] = dist[u]+w;
					System.out.println("Negative cycle");
					/*for(int x=0;x<n;x++){
						System.out.print(dist[x]+" ");
					}*/
					System.exit(1);
				}
			}
		}
		for(int x=0;x<n;x++){
			System.out.print("vertex 2 to "+(x+1)+" distance: "+dist[x]+"  :2   ");
			printParent(x,p);
			System.out.println();
		}

	}
	public void printParent(int x,int[] p) {
		if(p[x] == -1){
			return ;
		}
		printParent(p[x],p);
		System.out.print("-->"+(x+1)+" ");

	}
	public static void main(String args[]) {
		Random rd = new Random();
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the number of nodes in the graph");

		int n=in.nextInt();
		RandomBellman obj=new RandomBellman(n);
		int min = n-1;
		int max = (n*(n-1));///2;
		//System.out.println("Enter number of edges");
		int k= rd.nextInt((max - min) + 1) + min;
		System.out.println("Edges= "+k);
		//System.out.println("Enter the edges 	eg: node a node b edge weight");
		obj.createRandomGraph(n,k,rd,obj);
		obj.bellmanFord(1,n,k);

	}
}
