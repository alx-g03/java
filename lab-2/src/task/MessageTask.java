package task;

import task.Task;
import utils.Utils;

import java.time.LocalDateTime;

public class MessageTask extends Task {
    private String mesaj;
    private String from;
    private String to;
    private LocalDateTime date;

    public MessageTask(Long taskID, String descriere, String mesaj, String from, String to, LocalDateTime date) {
        super(taskID, descriere);
        this.mesaj = mesaj;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public void execute() {
        System.out.println("task.Task-ul " + super.toString() + " a fost executat la data de: " + date.format(Utils.formatter));
    }
}
