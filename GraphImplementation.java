import java.util.*;

public class GraphImplementation implements Graph
{
	//two dimensional array
	int[][] matrix;
	int vertices;
	
	//constructor
	public GraphImplementation(int vertices)
	{
		this.vertices = vertices;
		matrix = new int[vertices][vertices];
	}
	
	//adds edge between vertices
	public void addEdge(int v1, int v2)
	{
		matrix[v1][v2] = 1;
	}
	
	public List<Integer> topologicalSort()
	{
		List<Integer> order = new LinkedList<Integer>();
		int visited[] = new int[vertices];
		Arrays.fill(visited, 0); //vertices begin as not visited

		for (int row = 0; row < vertices; row++)
		{
			for (int column = 0; column < vertices; column++)
			{
				visited[column] += matrix[row][column]; //adds up rows in columns in visited array
			}
		}

		//find zeroes in array
		for (int x = 0; x < vertices; x++)
		{
			for (int y = 0; y < vertices; y++)
			{
				if (visited[y] == 0) //if not visited
				{
					int[] neighbors = this.neighbors(y); //call in neighbor
					for(int z = 0; z < neighbors.length; z++)
					{
						visited[neighbors[z]]--; //decrease amount of visits
					}
					order.add(y); //add index to list
					visited[y] = -1; //prevents from being returned again and again
				}
			}
		}
		return order;
	}
	
	//return array of neighbors
	public int[] neighbors(int vertex)
	{
		int[] temp = new int[vertices];
		int index = 0;
		for(int i = 0; i < vertices ; i++)
		{
			if (matrix[vertex][i] == 1 && i != vertex)
			{
				temp[index] = i;
				index++;		
			}
		}
		int[] neighbor = new int[index];
		System.arraycopy(temp, 0, neighbor, 0, index);
		return neighbor;
	}
}