import java.util.ArrayList;
import java.util.HashSet;

public class SRTF {
    private HashSet<Process> set = new HashSet<>();
    ArrayList<Process> processes = new ArrayList<>();
    ArrayList<Process> list = new ArrayList<>();
    ArrayList<Process> ganteChart = new ArrayList<>();
    private int avgWaiting = 0;
    int size = 0;

    public void add(Process p) {
        processes.add(p);
        size++;
    }

    public void run() {
        // boolean[] isComplete = new boolean[processes.size()];
        for (Process process : processes) {
            list.add(process);
        }
        int countComplete = 0;
        int currentTime = 0;
        while (countComplete < size) {
            ArrayList<Process> temp = new ArrayList<>();
            for (Process p : list) {
                if (p.arrivalTime <= currentTime) {
                    temp.add(p);
                }
            }
            Process minp = null;
            int min = Integer.MAX_VALUE;
            for (Process p : temp) {
                if (p.burstTime < min) {
                    min = p.burstTime;
                    minp = p;
                }
            }

            if (temp.size() == 0) {
                ganteChart.add(new Process("free", currentTime, 0));
                currentTime++;
                continue;
            }

            minp.burstTime--; 

            // ganteChart.add(minp);

            if (minp.burstTime <= 0) {
                minp.isComplete = true;
                minp.exitTime = currentTime + 1;
                minp.burstTime = minp.bt;
                minp.turnAroundTime = minp.exitTime - minp.arrivalTime;
                minp.waitTime = minp.turnAroundTime - minp.burstTime;
                list.remove(minp);
                countComplete++;
            }

            currentTime++;
            ganteChart.add(minp);
            set.add(minp);
        }
    }

    public void printChart() {
        System.out.println("\nPid\t AT\t BT\t ET\t TAT\t WT");
        for (Process p : set) {
            if (p.id == "free")
                continue;
            if (p.isComplete)
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
        for (Process process : set) {
            if (process.isComplete)
            avgWaiting += process.waitTime;
        }

        return avgWaiting / size;
    } 

}
