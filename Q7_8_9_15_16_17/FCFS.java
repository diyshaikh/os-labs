import java.util.ArrayList;

public class FCFS {
    private PriorityQueue q = new PriorityQueue();
    ArrayList<Process> ganteChart = new ArrayList<>();
    private int avgWaiting = 0;

    public void add(Process p) {
        q.enqueue(p);
    }

    public void run() {
        int prevExitTime = 0;
        while (!q.isEmpty()) {
            Process currentProcess = q.dequeue();
            if (prevExitTime < currentProcess.arrivalTime)
                ganteChart.add(new Process("free", prevExitTime, currentProcess.arrivalTime));
            int extraFactor = (prevExitTime < currentProcess.arrivalTime) ? currentProcess.arrivalTime : prevExitTime;
            currentProcess.exitTime = currentProcess.burstTime + extraFactor;
            currentProcess.turnAroundTime = currentProcess.exitTime - currentProcess.arrivalTime;
            currentProcess.waitTime = currentProcess.turnAroundTime - currentProcess.burstTime;
            avgWaiting += currentProcess.waitTime;
            prevExitTime = currentProcess.exitTime;
            ganteChart.add(currentProcess);
        }
        System.out.println("Processes scheduled sucessfully!");
        avgWaiting /= ganteChart.size(); 
    }

    public void printChart() {
        System.out.println("\nPid\t AT\t BT\t ET\t TAT\t WT");
        for (Process p : ganteChart) {
            if (p.id == "free")
                continue;
            System.out.println(p.id + "\t " + p.arrivalTime + "\t " + p.burstTime + "\t "
                    + p.exitTime + "\t " + p.turnAroundTime + "\t " + p.waitTime
                    + "\t ");
        }
    }

    public void ganteChart() {
        System.out.println();
        System.out.print("|");
        for (Process p : ganteChart) {
            System.out.print("  " + p.id + "  |");
        }
        System.out.println();
    }

    public int getAvgWait() {
        return avgWaiting;
    }

}
