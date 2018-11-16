import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
	private ArrayList<Student> students;

	public MaxHeap(int capacity)
	{
		students = new ArrayList<Student>(capacity);
	}

	public MaxHeap(Collection<Student> collection)
	{
		students = new ArrayList<Student>(collection);
		for (int i = 0; i < size(); i++)
			students.get(i).setIndex(i); // have to update index when using ArrayList
		
		for(int i = size()/2; i >= 0; i--)
		{
			maxHeapify(i);
		}
	}

	public Student getMax() 
	{
		if(size() < 1)
		{
			throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
		}
		return students.get(0);
	}
	
	//this method is for testing purposes
	public Student getStudent(int index) {
		return students.get(index);
	}

	public Student extractMax()
	{
		Student value = getMax();
		students.set(0,students.get(size()-1));
		students.remove(size()-1);
		maxHeapify(0);
		return value;
	}
	
	//add student into array and heapify upwards
	public void insert(Student elt) {
		students.add(elt);
		elt.setIndex(size() - 1);
		heapifyUp(elt.index());
	}

	public void changeKey(Student s, double newGPA) {
		s.setGPA(newGPA);
		maxHeapify(s.index());
		heapifyUp(s.index());
	}

	//helper method for heapifying upwards
	private void heapifyUp(int index) {
		while (index != 0 && students.get(parent(index)).compareTo(students.get(index)) < 0) {
			swap(index, parent(index));
			index = parent(index);
		}
	}

	private int parent(int index)
	{
		return (index - 1)/2;
	}

	private int left(int index)
	{
		return 2 * index + 1;
	}

	private int right(int index)
	{
		return 2 * index + 2;
	}

	private int size()
	{
		return students.size();
	}

	//change coding style to make it easier for me
	private void swap(int from, int to) {
		Student s1 = students.get(from);
		Student s2 = students.get(to);
		students.set(from,  s2);
		students.set(to,  s1);
		s1.setIndex(to);
		s2.setIndex(from);
	}

	private void maxHeapify(int index)
	{
		int left = left(index);
		int right = right(index);
		int largest = index;
		if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
		{
			largest = left;
		}
		if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
		{
			largest = right;
		}
		if (largest != index)
		{
			swap(index, largest);
			maxHeapify(largest);
		}  
	} 
}
