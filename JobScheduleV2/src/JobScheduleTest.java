import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JobScheduleTest
{
	private JobSchedule schedule;

	@Before 
	public void setUp()
	{
		schedule = new JobSchedule();
	}

	@Test
	public void testLoop()
	{
		JobSchedule.Job j1 = schedule.addJob(8);
		JobSchedule.Job j2 = schedule.addJob(3);
		JobSchedule.Job j3 = schedule.addJob(5);
		JobSchedule.Job j4 = schedule.addJob(1);
		JobSchedule.Job j5 = schedule.addJob(2);
		JobSchedule.Job j6 = schedule.addJob(1);
		JobSchedule.Job j7 = schedule.addJob(5);
		assertEquals(0, j1.getStartTime());
		j1.requires(j2);
		j1.requires(j3);
		j1.requires(j4);
		j2.requires(j5);
		j2.requires(j6);
		j3.requires(j7);
		j4.requires(j7);
		j7.requires(j1);

		assertEquals(-1, j1.getStartTime());
	}

	@Test
	public void testStartTime()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(2);
		JobSchedule.Job j3 = schedule.addJob(3);
		JobSchedule.Job j4 = schedule.addJob(4);
		JobSchedule.Job j5 = schedule.addJob(5);
		JobSchedule.Job j6 = schedule.addJob(6);
		JobSchedule.Job j7 = schedule.addJob(7);
		assertEquals(0, j1.getStartTime());
		j1.requires(j2);
		j1.requires(j3);
		j1.requires(j4);
		j2.requires(j5);
		j2.requires(j6);
		j3.requires(j7);
		j4.requires(j7);

		assertEquals(11, j1.getStartTime());
	}

	@Test
	public void testTaylor()
	{
		schedule.addJob(8); //adds job 0 with time 8
		JobSchedule.Job j1 = schedule.addJob(3); //adds job 1 with time 3
		schedule.addJob(5); //adds job 2 with time 5
		assertEquals(schedule.minCompletionTime(), 8); //should return 8, since job 0 takes time 8 to complete.
		/* Note it is not the min completion time of any job, but the earliest the entire set can complete. */
		schedule.getJob(0).requires(schedule.getJob(2)); //job 2 must precede job 0
		assertEquals(13, schedule.minCompletionTime()); //should return 13 (job 0 cannot start until time 5)
		schedule.getJob(0).requires(j1); //job 1 must precede job 0
		assertEquals(13, schedule.minCompletionTime()); //should return 13
		assertEquals(5, schedule.getJob(0).getStartTime()); //should return 5
		assertEquals(0, j1.getStartTime()); //should return 0
		assertEquals(0, schedule.getJob(2).getStartTime()); //should return 0
		j1.requires(schedule.getJob(2)); //job 2 must precede job 1
		assertEquals(16, schedule.minCompletionTime()); //should return 16
		assertEquals(8, schedule.getJob(0).getStartTime()); //should return 8
		assertEquals(5, schedule.getJob(1).getStartTime()); //should return 5
		assertEquals(0, schedule.getJob(2).getStartTime()); //should return 0
		schedule.getJob(1).requires(schedule.getJob(0)); //job 0 must precede job 1 (creates loop)
		assertEquals(-1, schedule.minCompletionTime()); //should return -1
		assertEquals(-1, schedule.getJob(0).getStartTime()); //should return -1
		assertEquals(-1, schedule.getJob(1).getStartTime()); //should return -1
		assertEquals(0, schedule.getJob(2).getStartTime()); //should return 0 (no loops in prerequisites)
	}

	@Test
	public void testLoop2()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(2);
		JobSchedule.Job j3 = schedule.addJob(3);
		JobSchedule.Job j4 = schedule.addJob(4);
		JobSchedule.Job j5 = schedule.addJob(5);
		JobSchedule.Job j6 = schedule.addJob(6);
		JobSchedule.Job j7 = schedule.addJob(7);
		assertEquals(0, j1.getStartTime());
		j1.requires(j2);
		j1.requires(j3);
		j1.requires(j4);
		j2.requires(j5);
		j2.requires(j6);
		j3.requires(j7);
		j4.requires(j7);
		j7.requires(j3);

		assertEquals(-1, j1.getStartTime());
	}
	//	
	@Test
	public void testStartTime2()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(2);
		JobSchedule.Job j3 = schedule.addJob(3);
		JobSchedule.Job j4 = schedule.addJob(4);
		JobSchedule.Job j5 = schedule.addJob(5);
		JobSchedule.Job j6 = schedule.addJob(6);
		JobSchedule.Job j7 = schedule.addJob(7);
		JobSchedule.Job j8 = schedule.addJob(8);
		JobSchedule.Job j9 = schedule.addJob(9);
		JobSchedule.Job j10 = schedule.addJob(10);
		JobSchedule.Job j11 = schedule.addJob(11);
		JobSchedule.Job j12 = schedule.addJob(12);
		JobSchedule.Job j13 = schedule.addJob(13);
		JobSchedule.Job j14 = schedule.addJob(14);
		JobSchedule.Job j15 = schedule.addJob(15);
		JobSchedule.Job j16 = schedule.addJob(16);
		JobSchedule.Job j17 = schedule.addJob(17);

		assertEquals(0, j1.getStartTime());
		j1.requires(j4);
		j1.requires(j3);
		j2.requires(j5);

		j4.requires(j6);
		j5.requires(j6);
		j3.requires(j6);

		j6.requires(j7);
		j6.requires(j8);
		j6.requires(j9);

		j7.requires(j10);
		j8.requires(j10);
		j9.requires(j11);
		j9.requires(j12);

		j10.requires(j13);
		j11.requires(j13);
		j12.requires(j13);

		j13.requires(j14);
		j13.requires(j15);
		j13.requires(j16);

		j14.requires(j17);
		j15.requires(j17);
		j16.requires(j17);

		assertEquals(77, j1.getStartTime());
	}

	@Test
	public void testMinTime()
	{
		JobSchedule.Job j1 = schedule.addJob(3);
		JobSchedule.Job j2 = schedule.addJob(4);
		JobSchedule.Job j3 = schedule.addJob(5);
		JobSchedule.Job j4 = schedule.addJob(7);
		JobSchedule.Job j5 = schedule.addJob(12);
		assertEquals(12,schedule.minCompletionTime());
		j5.requires(j4);
		assertEquals(19,schedule.minCompletionTime());
		j4.requires(j3);
		assertEquals(24,schedule.minCompletionTime());
		j1.requires(j2);
		assertEquals(24,schedule.minCompletionTime());
		j3.requires(j1);
		assertEquals(31,schedule.minCompletionTime());
	}

	@Test
	public void testLoop3()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(1);
		JobSchedule.Job j3 = schedule.addJob(5);
		JobSchedule.Job j4 = schedule.addJob(1);
		JobSchedule.Job j5 = schedule.addJob(1);
		JobSchedule.Job j6 = schedule.addJob(1);
		JobSchedule.Job j7 = schedule.addJob(1);
		JobSchedule.Job j8 = schedule.addJob(1);
		JobSchedule.Job j9 = schedule.addJob(1);
		JobSchedule.Job j10 = schedule.addJob(1);
		JobSchedule.Job j11 = schedule.addJob(1);
		JobSchedule.Job j12 = schedule.addJob(1);
		JobSchedule.Job j13 = schedule.addJob(1);
		JobSchedule.Job j14 = schedule.addJob(1);
		JobSchedule.Job j15 = schedule.addJob(1);
		JobSchedule.Job j16 = schedule.addJob(1);
		JobSchedule.Job j17 = schedule.addJob(1);

		assertEquals(0, j1.getStartTime());
		j1.requires(j4);
		j1.requires(j3);
		j2.requires(j5);

		j4.requires(j6);
		j5.requires(j6);
		j3.requires(j6);

		j6.requires(j7);
		j6.requires(j8);
		j6.requires(j9);

		j7.requires(j10);
		j8.requires(j10);
		j9.requires(j11);
		j9.requires(j12);

		j10.requires(j13);
		j11.requires(j13);
		j12.requires(j13);
		j2.requires(j5);

		j13.requires(j14);
		j13.requires(j15);
		j13.requires(j16);

		j14.requires(j17);
		j15.requires(j17);
		j16.requires(j17);

		assertEquals(12, schedule.minCompletionTime());

		j15.requires(j6);
		assertEquals(-1, schedule.minCompletionTime());
	}

	@Test
	public void testLoop4()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(1);
		JobSchedule.Job j3 = schedule.addJob(5);
		JobSchedule.Job j4 = schedule.addJob(1);

		j1.requires(j1);
		j1.requires(j2);
		j1.requires(j3);
		j1.requires(j4);

		j2.requires(j1);
		j2.requires(j2);
		j2.requires(j3);
		j2.requires(j4);

		j3.requires(j1);
		j3.requires(j2);
		j3.requires(j3);
		j3.requires(j4);

		j4.requires(j1);
		j4.requires(j2);
		j4.requires(j3);
		j4.requires(j4);

		assertEquals(-1, schedule.minCompletionTime());
	}

	@Test
	public void testManyJobs()
	{
		int numJobs = 1000000;
		for (int i = 0; i < numJobs; i++)
		{
			schedule.addJob(1);
		}
		for (int i = 0; i < numJobs - 1; i++)
		{
			schedule.getJob(i).requires(schedule.getJob(i + 1));
		}
		for (int i = 0; i < numJobs; i++)
		{
			System.out.println(schedule.getJob(i).getStartTime());
			System.out.println(schedule.minCompletionTime());
		}
		schedule.getJob(numJobs - 1).requires(schedule.getJob(0));
		assertEquals(-1, schedule.minCompletionTime());
	}

	@Test
	public void testLoop5()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(1);
		JobSchedule.Job j3 = schedule.addJob(5);
		JobSchedule.Job j4 = schedule.addJob(1);
		JobSchedule.Job j5 = schedule.addJob(1);
		JobSchedule.Job j6 = schedule.addJob(1);
		JobSchedule.Job j7 = schedule.addJob(1);
		JobSchedule.Job j8 = schedule.addJob(1);
		JobSchedule.Job j9 = schedule.addJob(1);
		JobSchedule.Job j10 = schedule.addJob(1);
		JobSchedule.Job j11 = schedule.addJob(1);
		JobSchedule.Job j12 = schedule.addJob(1);
		JobSchedule.Job j13 = schedule.addJob(1);
		JobSchedule.Job j14 = schedule.addJob(1);
		JobSchedule.Job j15 = schedule.addJob(1);
		JobSchedule.Job j16 = schedule.addJob(1);
		JobSchedule.Job j17 = schedule.addJob(1);

		assertEquals(0, j1.getStartTime());
		j1.requires(j4);
		j1.requires(j3);
		j2.requires(j5);

		j4.requires(j6);
		j5.requires(j6);
		j3.requires(j6);

		j6.requires(j7);
		j6.requires(j8);
		j6.requires(j9);

		j7.requires(j10);
		j8.requires(j10);
		j9.requires(j11);
		j9.requires(j12);

		j10.requires(j13);
		j11.requires(j13);
		j12.requires(j13);
		j2.requires(j5);

		j13.requires(j14);
		j13.requires(j15);
		j13.requires(j16);

		j14.requires(j17);
		j15.requires(j17);
		j16.requires(j17);

		assertEquals(12, schedule.minCompletionTime());

		j17.requires(j1);
		j17.requires(j2);
		j17.requires(j3);
		j17.requires(j4);
		j17.requires(j5);
		j17.requires(j6);
		j17.requires(j7);
		j17.requires(j8);
		j17.requires(j9);
		j17.requires(j10);
		j17.requires(j11);
		j17.requires(j12);
		j17.requires(j13);
		j17.requires(j14);
		j17.requires(j15);
		j17.requires(j16);
		j17.requires(j17);
		assertEquals(-1, schedule.minCompletionTime());
	}
	
	@Test
	public void testLoop6()
	{
		JobSchedule.Job j1 = schedule.addJob(1);
		JobSchedule.Job j2 = schedule.addJob(1);
		JobSchedule.Job j3 = schedule.addJob(1);
		
		j1.requires(j2);
		j2.requires(j1);
		j3.requires(j1);
		j1.requires(j3);
		
		assertEquals(-1, j1.getStartTime());
		assertEquals(-1, j2.getStartTime());
		assertEquals(-1, j3.getStartTime());
	}
	
	
}
