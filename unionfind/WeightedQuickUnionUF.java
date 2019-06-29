public class WeightedQuickUnionUF {
	// every object is represented as a natural number/ index.
	private int[] parent; // the object's parent, the root of one union has its parent as itself.  
	private int[] size; // the size of the union (if the object is a root, the size[i] represents 
	// the according union size.)
	private int count; // the total number of unions

	
	public WeightedQuickUnionUF(int n){
		// constructor  
		count = n;
		parent = new int[n];
		size = new int[n];

		for(int i=0; i<n; i++){
			parent[i]=i;
			size[i]=1;
		}
	}

	public int count(){
		// the method for getting the count of unions
		return count;
	}

	public int find(int p){
		// return the identifier(the root) of the union the object p belongs to
		// first validate
		validate(p);
		while (p != parent[p])
			p = parent[p];
		return p;

	}

	private void validate(int p){
		int n = size.length;
		if(p<0 || p>=n)
			throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
	}

	public boolean connected(int p, int q){
		// check if object p, q are in the same union
		return find(p) == find(q);
	}

	public void union(int p, int q){
		int rootp = find(p);
		int rootq = find(q);
		if(rootp == rootq) return;

		int sizeq = size[rootq];
		int sizep = size[rootp];
		if(sizep<sizeq){
			parent[rootp] = rootq;
			size[rootq] += sizep; 
		}
		else{
			parent[rootq] = rootp;
			size[rootp] += sizeq;
		}
		count--;
	}

	public static void main(String[] args){
		WeightedQuickUnionUF a = new WeightedQuickUnionUF(7);
		System.out.println(a.count());//a.connected(1,2));
		a.union(1,2);
		System.out.println(a.count());//(a.connected(1,2));

	}

}
