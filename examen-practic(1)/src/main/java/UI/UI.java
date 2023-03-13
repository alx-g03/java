package UI;

import domain.Excursie;
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
        System.out.println("1. Print all");
        System.out.println("2. Cautare Roma Colosseum");
        System.out.println("3. Filter istorie");
        System.out.println("4. Filter istorie pret");
        System.out.println("5. Sort dupa categorie");
        System.out.println("6. Sort dupa oras atractie");
        System.out.println("7. Sort desc dupa pret");
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
                this.cautareRomaColosseum();
            else if (optiune == 3)
                this.filterIstorie();
            else if (optiune == 4)
                this.filterIstoriePret();
            else if (optiune == 5)
                this.sortDupaCategorie();
            else if (optiune == 6)
                this.sortDupaOrasAtractie();
            else if (optiune == 7)
                this.sortDescDupaPret();
        }
        input.close();
    }

    private void printAll() {
        for (Excursie excursie : this.service.findAll()) {
            System.out.println(excursie);
        }
    }

    public void cautareRomaColosseum() {
        System.out.println(service.cautareRomaColosseum());
    }

    public void filterIstorie() {
        for (Excursie excursie : this.service.filterIstorie()) {
            System.out.println(excursie);
        }
    }

    public void filterIstoriePret() {
        for (Excursie excursie : this.service.filterIstoriePret()) {
            System.out.println(excursie);
        }
    }

    public void sortDupaCategorie() {
        List<Excursie> list = new ArrayList<>((Collection) this.service.findAll());
        for (Excursie excursie : list.stream().map(excursie -> this.service.sortDupaCategorie()).toList().get(0)) {
            System.out.println("{" + excursie.getOras());
            System.out.println(excursie.getAtractie());
            System.out.println(excursie.getCategorie() + "}");
        }
    }

    public void sortDupaOrasAtractie() {
        List<Excursie> list = new ArrayList<>((Collection) this.service.findAll());
        System.out.println(list.stream().map(excursie -> this.service.sortDupaOrasAtractie()).toList().get(0));
    }

    public void sortDescDupaPret() {
        List<Excursie> list = new ArrayList<>((Collection) this.service.findAll());
        System.out.println(list.stream().map(excursie -> this.service.sortDescDupaPret()).toList().get(0));
    }
}
