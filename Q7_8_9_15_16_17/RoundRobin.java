import java.util.ArrayList;
import java.util.HashSet;

public class RoundRobin {
    int tq = 2;
    ArrayList<Process> processes = new ArrayList<>();
    ArrayList<Process> ganteChart = new ArrayList<>();
    HashSet<Process> set = new HashSet<>();
    int size = 0;

    public void add(Process p) {
        processes.add(p);
        size++;
    }

    public void run() {
        int countComplete = 0;
        int currentTime = 0;
        Queue temp = new Queue();
        for (Process p : processes) {
            if (!p.isComplete && p.arrivalTime <= currentTime) {
                temp.enqueue(p);
            }
        }
        while (countComplete <= processes.size()) {

            while (!temp.isEmpty() || countComplete <= processes.size()) {
                Process top = temp.dequeue();
                if (top == null)
                    return;

                top.burstTime -= tq;
                if (top.burstTime <= 0) {
                    top.exitTime = currentTime;
                    top.burstTime = top.bt;
                    top.turnAroundTime = top.exitTime - top.arrivalTime;
                    top.waitTime = top.turnAroundTime - top.burstTime;
                    top.isComplete = true;
                    set.add(top);
                    countComplete++;
                }
                currentTime += tq;
                for (Process p : processes) {
                    if (!p.isComplete && p.arrivalTime <= currentTime && !temp.contains(p) && top.id != p.id) {
                        temp.enqueue(p);
                    }
                }
                if (temp.size == 0) {
                    ganteChart.add(new Process("free", currentTime, 0));
                    currentTime++;
                    continue;
                }
                if (!top.isComplete)
                    temp.enqueue(top);
                ganteChart.add(top);
            }

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
        int avgWaiting = 0;
        for (Process process : set) {
            if (process.isComplete)
            avgWaiting += process.waitTime;
        }

        return avgWaiting / size;
    }

}
