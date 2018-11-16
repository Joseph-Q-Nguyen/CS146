import java.util.ArrayList;

public class TwoThreeTree 
{
	private Node root;

	public TwoThreeTree()	{ root = null; }

	public boolean insert(int x)
	{
		if (root == null) { root = new Node(x); }
		Node finishSplit = root.insert(x);
		if (finishSplit == null)
			return false;
		else if (finishSplit.size() == 3)
			root = finishSplit.split();
		return true;
	}

	public String search(int x) 
	{
		Node node = root.search(x);
		return node.toString();		
	}

	class Node 
	{
		private ArrayList<Integer> 		data;
		private ArrayList<Node>			children;

		public Node(int x) 
		{
			data = new ArrayList<>();
			children = new ArrayList<>();
			addData(x);
		}

		public Node insert(int x) 
		{
			if (contains(x)) 		{ return null; } 
			else if (isLeaf()) 		
			{ 
				addData(x); 
				return this;
			}
			else 
			{
				int i = 0;
				while (i < data.size() && x > data.get(i)) 
					i++; 	
				Node node = children.get(i).insert(x);
				if (node == null)
				{
					return null;
				}
				else 
				{
					if (node.size() == 3)
					{
						Node temp = node.split();
						addData(temp.data.get(0));
						removeChild(node);
						addChild(temp.children);
					}
					return this;
				}
			}
		}

		public Node search(int x) 
		{
			if (isLeaf() || data.contains(x))
				return this;
			else 
			{
				int i = 0;
				while (i < size() && x > data.get(i))
					i++;
				return children.get(i).search(x);
			}
		}

		public String toString() 
		{
			String numbers = "";
			for (int i = 0; i < size(); i++) 
			{
				if (i == 0) { numbers = numbers + data.get(i); } 
				else { numbers =  numbers + " " + data.get(i); }
			}	
			return numbers;
		}

		public Node split() 
		{
			Node parent = new Node(data.get(1));
			parent.addChild(new Node(data.get(0)));
			parent.addChild(new Node(data.get(2)));
			if (children.size() != 0)
			{
				parent.children.get(0).addChild(children.get(0));
				parent.children.get(0).addChild(children.get(1));
				parent.children.get(1).addChild(children.get(2));
				parent.children.get(1).addChild(children.get(3));
			}
			return parent;
		}

		public void addData(int x) 
		{
			int i = 0;
			while (i < size() && x > data.get(i))
				i++;
			data.add(i, x);
		}

		public void addChild(Node c) 
		{
			int i = 0;
			while (i < children.size() && c.data.get(0) > children.get(i).data.get(0))
				i++;
			children.add(i, c);
		}

		public void addChild(ArrayList<Node> c) 
		{
			for (Node n : c)
				addChild(n);
		}

		public void removeChild(Node c) 
		{
			children.remove(c);
		}

		public boolean isLeaf() 
		{
			for (Node n : children)
				if (n != null)
					return false;
			return true;
		}

		public boolean contains(int x) 
		{
			return data.contains(x);
		}

		public int size() 
		{
			return data.size();
		}
	}
}
