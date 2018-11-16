public class Utility 
{
	public static boolean[] findMax(Graph g) 
	{
		int max = g.size();
		while (max > 0 && !g.has(max)) 
			max--;							// get the max clique size
		boolean[] clique = new boolean[g.size()];
		Graph subG = null; 
		int k = 0;
		for (int i = 0; i < clique.length; i++) 
		{
			Graph tempG = subG == null ? g.remove(k) : subG.remove(k); 	
			if (!tempG.has(max)) // if removing this vertex decreases the max clique size, it is guaranteed to be in a max clique
			{ 	
				clique[i] = true;
				k++;
			}
			else subG = tempG;
		}
		return clique;
	}
}
