import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class JosephMaxHeapTest {
	private MaxHeap heap;

	@Before
	public void setUp() {
		ArrayList<Student> overwatch = new ArrayList<>();
		overwatch.add(new Student("Genji", 60, 0.5));
		overwatch.add(new Student("Mercy", 70, 1.0));
		overwatch.add(new Student("Winston", 120, 4.0));
		overwatch.add(new Student("Lucio", 50, 3.0));
		overwatch.add(new Student("Reinhardt", 100, 2.0));
		heap = new MaxHeap(overwatch);
	}

	@Test
	public void testInsert() {
		heap.insert(new Student("Zenyatta", 40 , 5.0));
		assertEquals("Zenyatta", heap.getMax().getName());
		heap.insert(new Student("Zarya", 40 , 6.0));
		assertEquals("Zarya", heap.getMax().getName());
		heap.insert(new Student("Hanzo", 40 , 7.0));
		assertEquals("Hanzo", heap.getMax().getName());
		heap.insert(new Student("Moira", 40 , 9.0));
		assertEquals("Moira", heap.getMax().getName());
	}

	@Test
	public void testConstructor() {
		heap = new MaxHeap(4);
		heap.insert(new Student("Zenyatta", 40 , 5.0));
		heap.insert(new Student("Zarya", 40 , 6.0));
		heap.insert(new Student("Hanzo", 40 , 7.0));
		heap.insert(new Student("Moira", 40 , 9.0));

		assertEquals("Moira", heap.extractMax().getName());
		assertEquals("Hanzo", heap.extractMax().getName());
		assertEquals("Zarya", heap.extractMax().getName());
		assertEquals("Zenyatta", heap.extractMax().getName());
	}

	@Test
	public void testExtractMax() {
		assertEquals("Winston", heap.extractMax().getName());
		assertEquals("Lucio", heap.extractMax().getName());
		assertEquals("Reinhardt", heap.extractMax().getName());
		assertEquals("Mercy", heap.extractMax().getName());
		assertEquals("Genji", heap.extractMax().getName());
	}

	@Test
	public void testChangeKeyGreaterGPA() {
		heap.changeKey(heap.getMax(), 4.5); //heap should not change
		assertEquals("Winston", heap.getMax().getName()); 
	}

	@Test
	public void testChangeKeyLowerGPA() {
		heap.changeKey(heap.getMax(), 0); //Max student GPA -> 0 
		assertEquals("Lucio", heap.getMax().getName()); //Lucio should now be max
		heap.changeKey(heap.getMax(), 0);
		assertEquals("Reinhardt", heap.getMax().getName());	//Reinhardt should now be max
		heap.changeKey(heap.getMax(), 0);
		assertEquals("Mercy", heap.getMax().getName());//Mercy should now be max
		heap.changeKey(heap.getMax(), 0);
		assertEquals("Genji", heap.getMax().getName());	//Genji should now be max
	}

	@Test
	public void testChangeKeySameGPA() {
		heap.changeKey(heap.getMax(), 4.9); //Reed gpa not changed
		assertEquals("Winston", heap.getMax().getName()); //Winston should still be max
	}

	@Test 
	public void testChangeKeyIndex() {
		heap.changeKey(heap.getStudent(3), 5);
		assertEquals(5, heap.getMax().gpa(), 0);
		heap.changeKey(heap.getStudent(4), 6);
		assertEquals(6, heap.getMax().gpa(), 0);
	}
}
