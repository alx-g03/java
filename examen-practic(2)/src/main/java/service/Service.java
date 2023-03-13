package service;


import domain.Obiect;
import repository.DbRepo;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Service {
    private DbRepo repo;

    public Service(DbRepo DbRepo) {
        this.repo = DbRepo;
    }

    public Iterable<Obiect> findAll() {
        return this.repo.findAll();
    }

    public Iterable<Obiect> cautareConsultatiiPacient(String numePacient) {
        Set<Obiect> obiecte = new HashSet<>();
        for (Obiect o : repo.findAll())
            if (Objects.equals(o.getNumePacient(), numePacient))
                obiecte.add(o);
        return obiecte;
    }

    public List<Obiect> filterConsultatiiPediatrie() {
        List<Obiect> obiecte = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Obiect> obiecteFiltrate = obiecte.stream().filter(o -> Objects.equals(o.getSectie(), "Pediatrie")).collect(Collectors.toList());
        return obiecteFiltrate;
    }

    public List<Obiect> filterConsultatiiPediatrieDurata() {
        List<Obiect> obiecte = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Obiect> obiecteFiltrate = obiecte.stream().filter(o -> Objects.equals(o.getSectie(), "Pediatrie") && o.getDurata() > 30).collect(Collectors.toList());
        return obiecteFiltrate;
    }

    public List<Obiect> sortPacientDurata() {
        List<Obiect> obiecte = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Obiect> obiecteSortate = obiecte.stream().sorted((x, y) -> (x.getNumePacient() + x.getDurata().toString()).compareTo((y.getNumePacient() + y.getDurata().toString()))).toList();
        return obiecteSortate;
    }

    public List<Obiect> sortMedic() {
        List<Obiect> obiecte = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Obiect> obiecteSortate = obiecte.stream().sorted((x, y) -> x.getMedic().compareTo(y.getMedic())).toList();
        return obiecteSortate;
    }

    public List<Obiect> sortDescSectie() {
        List<Obiect> obiecte = StreamSupport.stream(this.repo.findAll().spliterator(), false).toList();
        List<Obiect> obiecteSortate = obiecte.stream().sorted((x, y) -> y.getSectie().compareTo(x.getSectie())).toList();
        return obiecteSortate;
    }
}
