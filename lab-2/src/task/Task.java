package task;

public abstract class Task {
    private Long taskID;
    private String descriere;

    public Task(Long taskID, String descriere) {
        this.taskID = taskID;
        this.descriere = descriere;
    }

    public abstract void execute();

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "id: " + taskID + " descriere: " + descriere;
    }
}
