import static org.junit.Assert.*;

import org.junit.Test;


public class JosephTwoThreeTreeTests
{

	@Test
	public void singleNodeTree()
	{
		TwoThreeTree t = new TwoThreeTree();
		int val = 9;
		t.insert(val);
		String expected = "9";

		assertEquals(expected, t.search(val));
		val = 8;
		assertEquals(expected, t.search(val));
		val = 10;
		assertEquals(expected, t.search(val));

		val = 15;
		t.insert(val);
		expected = "9 15";
		val = 9;
		assertEquals(expected, t.search(val));
		val = 8;
		assertEquals(expected, t.search(val));
		val = 10;
		assertEquals(expected, t.search(val));
		val = 15;
		assertEquals(expected, t.search(val));
		val = 18;
		assertEquals(expected, t.search(val));

		t = new TwoThreeTree();
		val = 15;
		t.insert(val);
		val = 9;
		t.insert(val);
		val = 9;
		assertEquals(expected, t.search(val));
		val = 8;
		assertEquals(expected, t.search(val));
		val = 10;
		assertEquals(expected, t.search(val));
		val = 15;
		assertEquals(expected, t.search(val));
		val = 18;
		assertEquals(expected, t.search(val));

	}

	@Test
	public void oneSplitLeft()
	{
		TwoThreeTree t = new TwoThreeTree();
		t.insert(9);
		t.insert(15);
		t.insert(1);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}

	@Test
	public void oneSplitRight()
	{
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(9);
		t.insert(15);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}

	@Test
	public void oneSplitMiddle()
	{
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(15);
		t.insert(9);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "15";
		assertEquals(expected, t.search(15));
		assertEquals(expected, t.search(17));
		assertEquals(expected, t.search(11));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));
	}


	@Test
	public void testDuplicates()
	{
		TwoThreeTree t = new TwoThreeTree();
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(1);
		t.insert(9);
		t.insert(15);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);
		t.insert(13);
		t.insert(20);
		t.insert(7);
		t.insert(4);

		String expected = "9";
		assertEquals(expected, t.search(9));
		expected = "4";
		assertEquals(expected, t.search(4));
		expected = "15";
		assertEquals(expected, t.search(15));

		expected = "13";
		assertEquals(expected, t.search(12));
		assertEquals(expected, t.search(13));
		assertEquals(expected, t.search(14));
		expected = "20";
		assertEquals(expected, t.search(19));
		assertEquals(expected, t.search(20));
		assertEquals(expected, t.search(21));

		expected = "1";
		assertEquals(expected, t.search(1));
		assertEquals(expected, t.search(0));
		assertEquals(expected, t.search(3));

		expected = "7";
		assertEquals(expected, t.search(6));
		assertEquals(expected, t.search(7));
		assertEquals(expected, t.search(8));
	}

	@Test
	public void testTree() {
		TwoThreeTree tree = new TwoThreeTree();
		tree.insert(1);
		tree.insert(0);
		assertEquals("0 1", tree.search(1));
	}

	@Test
	public void testTreeSplit() {
		TwoThreeTree tree = new TwoThreeTree();
		tree.insert(1);
		tree.insert(0);
		tree.insert(2);
		assertEquals("1", tree.search(1));
	}

	@Test
	public void testTreeDoubleSplit() {
		TwoThreeTree tree = new TwoThreeTree();
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		assertEquals("5", tree.search(5));
		assertEquals("2 4", tree.search(2));

	}

	@Test
	public void testTripleSplitRight() {
		TwoThreeTree tree = new TwoThreeTree();
		
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7); // double
		tree.insert(8);
		tree.insert(9);
		tree.insert(10);
		tree.insert(11);
		tree.insert(12);
		tree.insert(13);
		tree.insert(14);
		assertEquals("4 8", tree.search(8));
		assertEquals("10 12", tree.search(10));
	}
	
	@Test
	public void testTripleSplitLeft() {
		TwoThreeTree tree = new TwoThreeTree();

		tree.insert(14);
		tree.insert(13);
		tree.insert(12);
		tree.insert(11);
		tree.insert(10);
		tree.insert(9);
		tree.insert(8);
		tree.insert(7);
		tree.insert(6);
		tree.insert(5);
		tree.insert(4);
		tree.insert(3);
		tree.insert(2);
		tree.insert(1);
	
		assertEquals("7 11", tree.search(7));
		assertEquals("3 5", tree.search(3));
	}
	
	@Test
	public void testTripleSplitMiddle() {
		TwoThreeTree tree = new TwoThreeTree();
		int i = 0;
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		tree.insert(i+=10);
		assertEquals("40 80", tree.search(40));
		assertEquals("70", tree.search(70));
		tree.insert(55);
		tree.insert(59);
		assertEquals("40 80", tree.search(40));
		assertEquals("70", tree.search(70));
		assertEquals("55 60", tree.search(55));
		tree.insert(51);
		tree.insert(52);
		assertEquals("55", tree.search(55));

	}
	
	@Test
    public void ultimateTest() // Dev
    {
        TwoThreeTree t = new TwoThreeTree();
        t.insert(0);
        t.insert(1);
        t.insert(2);
        t.insert(3);
        t.insert(4);
        t.insert(5);
        t.insert(6);
        t.insert(7);
        t.insert(8);
        t.insert(9);
        t.insert(10);
        t.insert(11);
        t.insert(12);
        t.insert(13);
        t.insert(14);
        t.insert(15);
        t.insert(16);
        t.insert(17);
        t.insert(18);
        t.insert(19);
        t.insert(20);
        t.insert(21);
        t.insert(22);
        t.insert(23);
        t.insert(24);
        t.insert(25);
        t.insert(26);
        t.insert(27);
        t.insert(28);
        t.insert(29);
        t.insert(30);
        t.insert(31);
        t.insert(32);
        t.insert(33);
        t.insert(34);
        t.insert(35);
        t.insert(36);
        t.insert(37);
        t.insert(38);
        t.insert(39);
        t.insert(40);
        t.insert(41);
        t.insert(42);
        t.insert(43);
        t.insert(44);
        t.insert(45);
        t.insert(46);
        t.insert(47);
        t.insert(48);
        t.insert(49);
        t.insert(50);
        t.insert(51);
        t.insert(52);
        t.insert(53);
        t.insert(54);
        t.insert(55);
        t.insert(56);
        t.insert(57);
        t.insert(58);
        t.insert(59);
        t.insert(60);
        t.insert(61);
        t.insert(62);
        t.insert(63);
        t.insert(64);
        t.insert(65);
        t.insert(66);
        t.insert(67);
        t.insert(68);
        t.insert(69);
        t.insert(70);
        t.insert(71);
        t.insert(72);
        t.insert(73);
        t.insert(74);
        t.insert(75);
        t.insert(76);
        t.insert(77);
        t.insert(78);
        t.insert(79);
        t.insert(80);
        t.insert(81);
        t.insert(82);
        t.insert(83);
        t.insert(84);
        t.insert(85);
        t.insert(86);
        t.insert(87);
        t.insert(88);
        t.insert(89);
        t.insert(90);
        t.insert(91);
        t.insert(92);
        t.insert(93);
        t.insert(94);
        t.insert(95);
        t.insert(96);
        t.insert(97);
        t.insert(98);
        t.insert(99);
        t.insert(100);
        assertEquals("88", t.search(88));
        assertEquals("41", t.search(41));
        assertEquals("64", t.search(64));
        assertEquals("11", t.search(11));
        assertEquals("67", t.search(67));
        assertEquals("83", t.search(83));
        assertEquals("97 99", t.search(99));
        assertEquals("65", t.search(65));
        assertEquals("77", t.search(77));
        assertEquals("62", t.search(62));
        assertEquals("36", t.search(36));
        assertEquals("9", t.search(9));
        assertEquals("30", t.search(30));
        assertEquals("0", t.search(0));
        assertEquals("87", t.search(87));
        assertEquals("7", t.search(7));
        assertEquals("3", t.search(3));
        assertEquals("93", t.search(93));
        assertEquals("56", t.search(56));
        assertEquals("69", t.search(69));
        assertEquals("22", t.search(22));
        assertEquals("85", t.search(85));
        assertEquals("12", t.search(12));
        assertEquals("25", t.search(25));
        assertEquals("48", t.search(48));
        assertEquals("31 63", t.search(31));
        assertEquals("84", t.search(84));
        assertEquals("58", t.search(58));
        assertEquals("82", t.search(82));
        assertEquals("44", t.search(44));
        assertEquals("55", t.search(55));
        assertEquals("18", t.search(18));
        assertEquals("39", t.search(39));
        assertEquals("76", t.search(76));
        assertEquals("32", t.search(32));
        assertEquals("6", t.search(6));
        assertEquals("28", t.search(28));
        assertEquals("98", t.search(98));
        assertEquals("23", t.search(23));
        assertEquals("68", t.search(68));
        assertEquals("53", t.search(53));
        assertEquals("42", t.search(42));
        assertEquals("40", t.search(40));
        assertEquals("26", t.search(26));
        assertEquals("38", t.search(38));
        assertEquals("13", t.search(13));
        assertEquals("35", t.search(35));
        assertEquals("21", t.search(21));
        assertEquals("43", t.search(43));
        assertEquals("19", t.search(19));
        assertEquals("14", t.search(14));
        assertEquals("59", t.search(59));
        assertEquals("50", t.search(50));
        assertEquals("78", t.search(78));
        assertEquals("47", t.search(47));
        assertEquals("5", t.search(5));
        assertEquals("31 63", t.search(63));
        assertEquals("61", t.search(61));
        assertEquals("49", t.search(49));
        assertEquals("24", t.search(24));
        assertEquals("33", t.search(33));
        assertEquals("80", t.search(80));
        assertEquals("74", t.search(74));
        assertEquals("92", t.search(92));
        assertEquals("17", t.search(17));
        assertEquals("75", t.search(75));
        assertEquals("29", t.search(29));
        assertEquals("20", t.search(20));
        assertEquals("89", t.search(89));
        assertEquals("90", t.search(90));
        assertEquals("51", t.search(51));
        assertEquals("34", t.search(34));
        assertEquals("100", t.search(100));
        assertEquals("1", t.search(1));
        assertEquals("45", t.search(45));
        assertEquals("97 99", t.search(97));
        assertEquals("27", t.search(27));
        assertEquals("16", t.search(16));
        assertEquals("15", t.search(15));
        assertEquals("2", t.search(2));
        assertEquals("94", t.search(94));
        assertEquals("66", t.search(66));
        assertEquals("96", t.search(96));
        assertEquals("8", t.search(8));
        assertEquals("70", t.search(70));
        assertEquals("46", t.search(46));
        assertEquals("79", t.search(79));
        assertEquals("86", t.search(86));
        assertEquals("57", t.search(57));
        assertEquals("91 95", t.search(95));
        assertEquals("52", t.search(52));
        assertEquals("81", t.search(81));
        assertEquals("73", t.search(73));
        assertEquals("71", t.search(71));
        assertEquals("10", t.search(10));
        assertEquals("4", t.search(4));
        assertEquals("91 95", t.search(91));
        assertEquals("37", t.search(37));
        assertEquals("54", t.search(54));
        assertEquals("60", t.search(60));
        assertEquals("72", t.search(72));
    }

}






