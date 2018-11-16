import java.util.ArrayList;

public class JobSchedule {
	private ArrayList<Job> 		jobs = new ArrayList<>();
	private boolean 			changed = true;
	private int 				minComplete;

	public Job addJob(int time) {
		Job j = new Job(time);
		jobs.add(j);
		return j;
	}

	public Job getJob(int index) {return jobs.get(index);}

	public int minCompletionTime() {
		if (changed) topSort(); // only top sort if changed
		return minComplete;
	}

	private void topSort() { // Kahn's algorithm for top sort
		ArrayList<Job> topSort = new ArrayList<>();
		for (Job j : jobs) {
			if (j.inDegree == 0) {
				j.startTime = 0;
				topSort.add(j);
			}
			else
				j.startTime = -1; // for all jobs with in degree > 0, assume they cannot start
		}
		int i = 0;
		while (i < topSort.size()) { // look at all jobs in topSort and decrement inDegrees of outgoing jobs
			Job j = topSort.get(i);
			for (Job e : j.enabledJobs) {
				e.inDegree--;
				if (e.inDegree == 0)
					topSort.add(e);	// if inDegree is 0, add it into topSort
			}	
			i++;
		}
		int maxCompTime = 0;
		for (Job t : topSort) { 				// for all jobs in top order, relax start time
			t.relax();
			if (t.startTime + t.timeWeight > maxCompTime)
				maxCompTime = t.startTime + t.timeWeight; 
		}
		if (topSort.size() != jobs.size())
			minComplete = -1;					// schedule cannot be completed
		else
			minComplete = maxCompTime;			// set min completion time
		for (Job j : jobs)
			j.inDegree = j.preReqs.size(); 		// reset in degree of all jobs
		changed = false;
	}

	class Job {
		private int 			timeWeight;
		private int 			inDegree;
		private int 			startTime;
		private ArrayList<Job> 	preReqs = new ArrayList<>();
		private ArrayList<Job> 	enabledJobs = new ArrayList<>();

		private Job(int timeWeight) { this.timeWeight = timeWeight; }

		public void requires(Job j) {
			preReqs.add(j);
			j.enabledJobs.add(this);
			inDegree++;
			changed = true; // requiring can possibly change ordering
		}

		public int getStartTime() {
			if (changed) topSort(); // Only top sort if changed
			return startTime;
		}

		private void relax() { // Relax a job based on the start time of its prereqs
			for (Job p : preReqs)
				if (p.startTime + p.timeWeight > this.startTime)
					this.startTime = p.startTime + p.timeWeight;
		}
	}
}
