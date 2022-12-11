public class Process {
    String id;
    int burstTime;
    int arrivalTime;
    int exitTime = 0;
    int turnAroundTime = 0;
    int waitTime = 0;
    int priority = 0;
    boolean isComplete = false;
    int bt = 0;

    public Process(String id, int arrivalTime, int burstTime) {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.id = id;
    }
    public Process(String id, int arrivalTime, int burstTime, int priority) {
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.id = id;
        this.priority = priority;
    }

}
