package domain;

import java.util.Objects;

public class Excursie extends Entity<Long> {
    private String oras;
    private String atractie;
    private Categorie categorie;
    private Double pret;


    public Excursie() {}

    public Excursie(String oras, String atractie, Categorie categorie, Double pret) {
        this.oras = oras;
        this.atractie = atractie;
        this.categorie = categorie;
        this.pret = pret;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getAtractie() {
        return atractie;
    }

    public void setAtractie(String atractie) {
        this.atractie = atractie;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Excursie{" +
                "oras='" + oras + '\'' +
                ", atractie='" + atractie + '\'' +
                ", categorie=" + categorie +
                ", pret=" + pret +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Excursie excursie)) return false;
        return getOras().equals(excursie.getOras()) && getAtractie().equals(excursie.getAtractie()) && getCategorie() == excursie.getCategorie() && getPret().equals(excursie.getPret());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOras(), getAtractie(), getCategorie(), getPret());
    }
}
