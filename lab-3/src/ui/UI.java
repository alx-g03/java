package ui;

import domain.User;
import repository.RepoInMemory;

import java.util.Scanner;

public class UI {
    private RepoInMemory<Integer, User> RepoUser;

    public UI() {
        RepoUser = new RepoInMemory<Integer, User>();
    }

    private void printMeniu() {
        System.out.println("1. Adaugare utilizator");
        System.out.println("2. Stergere utilizator");
        System.out.println("3. Adaugare prieten");
        System.out.println("4. Stergere prieten");
        System.out.println("5. Afisare toti utilizatorii");
        System.out.println("0. Exit ");
    }

    public void runMeniu() {
        Scanner input = new Scanner(System.in);
        int optiune;
        while(true) {
            this.printMeniu();
            System.out.println("Alegeti o optiune: ");
            optiune = input.nextInt();
            if (optiune == 0)
                break;
            else if (optiune == 1)
                this.addUser();
            else if (optiune == 2)
                this.deleteUser();
            else if (optiune == 3)
                this.addFriend();
            else if (optiune == 4)
                this.deleteFriend();
            else if (optiune == 5)
                this.printAllUsers();
        }
        input.close();
    }

    private void addUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduceti numele: ");
        String name = input.next();

        User user = new User(name);
        user.setId(user.hashCode());
        this.RepoUser.save(user);
    }

    private void deleteUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduceti id-ul: ");
        Integer id = input.nextInt();

        this.RepoUser.delete(id);
    }

    private void addFriend() {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduceti id-ul user-ului: ");
        Integer idUser = input.nextInt();

        User user = RepoUser.findOne(idUser);

        System.out.println("Introduceti id-ul prietenului: ");
        Integer idFriend = input.nextInt();

        User friend = RepoUser.findOne(idFriend);
        user.addFriend(friend);
    }

    private void deleteFriend() {
        Scanner input = new Scanner(System.in);
        System.out.println("Introduceti id-ul user-ului: ");
        Integer idUser = input.nextInt();

        User user = RepoUser.findOne(idUser);

        System.out.println("Introduceti id-ul prietenului: ");
        Integer idFriend = input.nextInt();

        User friend = RepoUser.findOne(idFriend);
        user.deleteFriend(friend);
    }

    private void printAllUsers() {
        for (User user : this.RepoUser.findAll()) {
            System.out.print("id: " + user.getId() + " ");
            System.out.println(user);
        }
    }
}
