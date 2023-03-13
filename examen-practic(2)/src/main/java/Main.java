import UI.UI;
import repository.DbRepo;
import service.Service;

public class Main {
    public static void main(String[] args) {
        DbRepo repo = new DbRepo("jdbc:postgresql://localhost:5432/RestantaPractic", "postgres", "alex");
        Service service = new Service(repo);
        UI ui = new UI(service);
        ui.runMeniu();
    }
}
