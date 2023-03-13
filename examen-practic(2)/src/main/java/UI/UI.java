package UI;

import domain.Obiect;
import service.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class UI {
    private Service service;

    public UI(Service service) {
        this.service = service;
    }

    private void printMeniu() {
        System.out.println("1. printAll");
        System.out.println("2. cautareConsultatiePacient");
        System.out.println("3. filterConsultatiiPediatrie");
        System.out.println("4. filterConsultatiiPediatrieDurata");
        System.out.println("5. sortPacientDurata");
        System.out.println("6. sortMedic");
        System.out.println("7. sortDescSectie");
        System.out.println("0. Exit ");
    }

    public void runMeniu() {
        Scanner input = new Scanner(System.in);
        int optiune;
        while (true) {
            this.printMeniu();
            System.out.println("Alegeti o optiune: ");
            optiune = input.nextInt();
            if (optiune == 0)
                break;
            else if (optiune == 1)
                this.printAll();
            else if (optiune == 2)
                this.cautareConsultatiiPacient();
            else if (optiune == 3)
                this.filterConsultatiiPediatrie();
            else if (optiune == 4)
                this.filterConsultatiiPediatrieDurata();
            else if (optiune == 5)
                this.sortPacientDurata();
            else if (optiune == 6)
                this.sortMedic();
            else if (optiune == 7)
                this.sortDescSectie();
        }
        input.close();
    }

    private void printAll() {
        for (Obiect obiect : this.service.findAll()) {
            System.out.println(obiect);
        }
    }

    public void cautareConsultatiiPacient() {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter name: ");
        String nume_pacient= sc.nextLine();
        for (Obiect obiect : this.service.cautareConsultatiiPacient(nume_pacient)) {
            System.out.println(obiect);
        }
    }

    public void filterConsultatiiPediatrie() {
        for (Obiect obiect : this.service.filterConsultatiiPediatrie()) {
            System.out.println(obiect);
        }
    }

    public void filterConsultatiiPediatrieDurata() {
        for (Obiect obiect : this.service.filterConsultatiiPediatrieDurata()) {
            System.out.println(obiect);
        }
    }

    public void sortPacientDurata() {
        List<Obiect> list = new ArrayList<>((Collection) this.service.findAll());
        System.out.println(list.stream().map(obiect -> this.service.sortPacientDurata()).toList().get(0));
    }

    public void sortMedic() {
        List<Obiect> list = new ArrayList<>((Collection) this.service.findAll());
        System.out.println(list.stream().map(obiect -> this.service.sortMedic()).toList().get(0));
    }

    public void sortDescSectie() {
        List<Obiect> list = new ArrayList<>((Collection) this.service.findAll());
        System.out.println(list.stream().map(obiect -> this.service.sortDescSectie()).toList().get(0));
    }
}
