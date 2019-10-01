import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Busca{
	public static void main(String[] args){

		No n1 = new No("Arad");
		No n2 = new No("Zerind");
		No n3 = new No("Oradea");
		No n4 = new No("Sibiu");
		No n5 = new No("Fagaras");
		No n6 = new No("Rimnicu Vilcea");
		No n7 = new No("Pitesti");
		No n8 = new No("Timisoara");
		No n9 = new No("Lugoj");
		No n10 = new No("Mehadia");
		No n11 = new No("Drobeta");
		No n12 = new No("Craiova");
		No n13 = new No("Bucharest");
		No n14 = new No("Giurgiu");
 

		n1.adjacencies = new Edge[]{
			new Edge(n2,75),
			new Edge(n4,140),
			new Edge(n8,118)
		};
 		
		n2.adjacencies = new Edge[]{
			new Edge(n1,75),
			new Edge(n3,71)
		};
 		

 		
		n3.adjacencies = new Edge[]{
			new Edge(n2,71),
			new Edge(n4,151)
		};
		n4.adjacencies = new Edge[]{
			new Edge(n1,140),
			new Edge(n5,99),
			new Edge(n3,151),
			new Edge(n6,80),
		};
 		

		n5.adjacencies = new Edge[]{
			new Edge(n4,99),
			new Edge(n13,211)
		};
 		
		n6.adjacencies = new Edge[]{
			new Edge(n4,80),
			new Edge(n7,97),
			new Edge(n12,146)
		};
 		
		n7.adjacencies = new Edge[]{
			new Edge(n6,97),
			new Edge(n13,101),
			new Edge(n12,138)
		};
 		
		n8.adjacencies = new Edge[]{
			new Edge(n1,118),
			new Edge(n9,111)
		};
 		
		n9.adjacencies = new Edge[]{
			new Edge(n8,111),
			new Edge(n10,70)
		};

		n10.adjacencies = new Edge[]{
			new Edge(n9,70),
			new Edge(n11,75)
		};
 		
		n11.adjacencies = new Edge[]{
			new Edge(n10,75),
			new Edge(n12,120)
		};

		n12.adjacencies = new Edge[]{
			new Edge(n11,120),
			new Edge(n6,146),
			new Edge(n7,138)
		};

		n13.adjacencies = new Edge[]{
			new Edge(n7,101),
			new Edge(n14,90),
			new Edge(n5,211)
		};
 		
 		
		n14.adjacencies = new Edge[]{
			new Edge(n13,90)
		};
		CalcularMelhorCaminho(n1,n13);
		List<No> path = printPath(n13);
		System.out.println("Path: " + path);

	}

	public static void CalcularMelhorCaminho(No source, No goal){
		PriorityQueue<No> queue = new PriorityQueue<No>(20, new Comparator<No>(){
				public int compare(No i, No j){
					if(i.pathCost > j.pathCost){
						return 1;
					}

					else if (i.pathCost < j.pathCost){
						return -1;
					}

					else{
						return 0;
					}
				}
			}

		);
		
		queue.add(source);
		Set<No> explored = new HashSet<No>();
		boolean found = false;
		do{
			No current = queue.poll();
			explored.add(current);
			if(current.cidade.equals(goal.cidade)){
				found = true;
			}
			for(Edge e: current.adjacencies){
				No child = e.target;
				double cost = e.cost;

				if(!explored.contains(child) && !queue.contains(child)){
					child.pathCost = current.pathCost + cost;
					child.parent = current;
					queue.add(child);
						
				}
				else if((queue.contains(child))&&(child.pathCost>(current.pathCost+cost))){
					child.parent=current;
					child.pathCost = current.pathCost + cost;
					queue.remove(child);
					queue.add(child);
				}
			}
		}while(!queue.isEmpty()&& (found==false));

	}

	public static List<No> printPath(No target){
		List<No> path = new ArrayList<No>();
		for(No No = target; No!=null; No = No.parent){
			path.add(No);
		}
		Collections.reverse(path);
		return path;
	}

}

class No{
	public final String cidade;
	public double pathCost;
	public Edge[] adjacencies;
	public No parent;
	
	public No(String val){
		cidade = val;
	}

	public String toString(){
		return cidade;
	}

}

class Edge{
	public final double cost;
	public final No target;

	public Edge(No targetNo, double costVal){
		cost = costVal;
		target = targetNo;

	}
}