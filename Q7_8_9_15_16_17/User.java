import java.util.*;

public class User {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Choice number\t Algorithm");
        System.out.println("\t1\t First Come First Serve");
        System.out.println("\t2\t Shortest Job First");
        System.out.println("\t3\t Shortest Remaining Time First");
        System.out.println("\t4\t RoundRobin");
        System.out.println("\t5\t Priority Algorithm");
        System.out.print("Enter choice - ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                FirstComeFirstServe();
                break;
            case 2:
                ShortestJobFirst();
                break;
            case 3:
                ShortestRemainingTimeFirst();
                break;
            case 4:
                RoundRobin();
                break;
            case 5:
                PriorityAlgo();
                break;

            default:
                System.out.println("Incorrect choice");
                break;
        }

    }

    private static void FirstComeFirstServe() {
        FCFS fcfs = new FCFS();
        System.out.print("Enter number of processes - ");
        int processes = sc.nextInt();
        System.out.println("Enter pid, AT, BT");
        while (processes-- > 0) {
            String pid = sc.next();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            Process current = new Process(pid, at, bt);
            current.priority = current.arrivalTime;
            fcfs.add(current);
        }
        fcfs.run();
        fcfs.printChart();
        fcfs.ganteChart();
        System.out.println("Average Waiting time = " + fcfs.getAvgWait());
    }

    private static void ShortestJobFirst() {
        SJF sjf = new SJF();
        System.out.print("Enter number of processes - ");
        int processes = sc.nextInt();
        System.out.println("Enter P-id, AT, BT for every process");
        // int priority = 0;
        while (processes-- > 0) {
            String pid = sc.next();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            Process current = new Process(pid, at, bt);
            sjf.add(current);
        }
        sjf.run();
        sjf.printChart();
        sjf.ganteChart();
        System.out.println("Average Waiting time = " + sjf.getAvgWait());
    }

    private static void ShortestRemainingTimeFirst() {
        SRTF srtf = new SRTF();
        System.out.print("Enter number of processes - ");
        int processes = sc.nextInt();
        System.out.println("Enter pid, AT, BT");
        // int priority = 0;
        while (processes-- > 0) {
            String pid = sc.next();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            Process current = new Process(pid, at, bt);
            srtf.add(current);
            current.bt = bt;
        }
        srtf.run();
        srtf.printChart();
        srtf.ganteChart();
        System.out.println("Average Waiting time = " + srtf.getAvgWait());
    }

    private static void RoundRobin() {
        RoundRobin rr = new RoundRobin();
        System.out.print("Enter number of processes - ");
        int processes = sc.nextInt();
        System.out.println("Enter pid, AT, BT");
        while (processes-- > 0) {
            String pid = sc.next();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            Process current = new Process(pid, at, bt);
            rr.add(current);
            current.bt = bt;
        }
        rr.run();
        rr.printChart();
        rr.ganteChart();
        System.out.println("Average Waiting time = " + rr.getAvgWait());
    }

    private static void PriorityAlgo() {
        Priority pa = new Priority();
        System.out.print("Enter number of processes - ");
        int processes = sc.nextInt();
        System.out.println("Enter pid, AT, BT, Priority");
        while (processes-- > 0) {
            String pid = sc.next();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int p = sc.nextInt();
            Process current = new Process(pid, at, bt, p);
            pa.add(current);
            current.bt = bt;
        }
        pa.run();
        pa.printChart();
        pa.ganteChart();
        System.out.println("Average Waiting time = " + pa.getAvgWait());
    }
}
