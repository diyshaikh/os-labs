import java.util.ArrayList;

public class Priority {
    ArrayList<Process> processes = new ArrayList<>();
    ArrayList<Process> ganteChart = new ArrayList<>();
    int size = 0;

    public void add(Process p) {
        processes.add(p);
        size++;
    }

    public void run() {
        // boolean[] isComplete = new boolean[processes.size()];
        int countComplete = 0;
        int currentTime = 0;
        while (countComplete < size) {
            ArrayList<Process> temp = new ArrayList<>();
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime) {
                    temp.add(p);
                }
            }
            if (temp.size() == 0) {
                ganteChart.add(new Process("free", currentTime, 0));
                currentTime++;
                continue;
            }
            Process minp = null;
            int min = Integer.MAX_VALUE;
            for (Process p : temp) {
                if (p.priority < min) {
                    min = p.priority;
                    minp = p;
                }
            }
            countComplete++;
            minp.exitTime = currentTime + minp.burstTime;
            currentTime = minp.exitTime;
            minp.turnAroundTime = minp.exitTime - minp.arrivalTime;
            minp.waitTime = minp.turnAroundTime - minp.burstTime;
            processes.remove(minp);
            ganteChart.add(minp);
        }
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

    public double getAvgWait() {
        int avgWaiting = 0;
        for (Process process : ganteChart) {
            avgWaiting += process.waitTime;
        }

        return (double)avgWaiting / (double)size;
    }

}
