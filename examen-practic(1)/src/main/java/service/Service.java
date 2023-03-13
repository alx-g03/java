package service;


import domain.Categorie;
import domain.Excursie;
import repository.DbRepo;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service {
    private DbRepo repo;

    public Service(DbRepo DbRepo) {
        this.repo = DbRepo;
    }

    public Excursie findOne(Long idExcursie) {
        return this.repo.findOne(idExcursie);
    }

    public Iterable<Excursie> findAll() {
        return this.repo.findAll();
    }

    public void save(String oras, String atractie, Categorie categorie, Double pret) {
        Excursie excursie = new Excursie(oras, atractie, categorie, pret);
        excursie.setId((long) excursie.hashCode());
        this.repo.save(excursie);
    }

    public Excursie search(String oras, String atractie, Categorie categorie, Double pret) {
        Excursie excursie = new Excursie(oras, atractie, categorie, pret);
        excursie.setId((long) excursie.hashCode());
        for (Excursie e : repo.findAll()) {
            if (e.equals(excursie))
                return e;
        }
        return null;
    }

    public Excursie cautareRomaColosseum() {
        for (Excursie e : repo.findAll())
            if (Objects.equals(e.getOras(), "roma") && Objects.equals(e.getAtractie(), "colosseum"))
                return e;
        return null;
    }

    public List<Excursie> filterIstorie() {
        List<Excursie> excursii = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Excursie> excursiiFiltrate = excursii.stream().filter(c -> c.getCategorie() == Categorie.ISTORIE).collect(Collectors.toList());
        return excursiiFiltrate;
    }

    public List<Excursie> filterIstoriePret() {
        List<Excursie> excursii = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Excursie> excursiiFiltrate = excursii.stream().filter(o -> o.getCategorie() == Categorie.ISTORIE && o.getPret() > 2500).collect(Collectors.toList());
        return excursiiFiltrate;
    }

    public List<Excursie> sortDupaCategorie() {
        List<Excursie> excursii = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Excursie> excursiiSortate = excursii.stream().sorted((x, y) -> x.getCategorie().compareTo(y.getCategorie())).toList();
        return excursiiSortate;
    }

    public List<Excursie> sortDupaOrasAtractie() {
        List<Excursie> excursii = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Excursie> excursiiSortate = excursii.stream().sorted((x, y) -> (x.getOras() + x.getAtractie()).compareTo((y.getOras() + y.getAtractie()))).toList();
        return excursiiSortate;
    }

    public List<Excursie> sortDescDupaPret() {
        List<Excursie> excursii = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Excursie> excursiiSortate = excursii.stream().sorted((x, y) -> y.getPret().compareTo(x.getPret())).toList();
        return excursiiSortate;
    }
}
