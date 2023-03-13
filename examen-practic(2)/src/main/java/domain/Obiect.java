package domain;

import java.util.Objects;

public class Obiect extends Entity<Long> {
    private String numePacient;
    private String medic;
    private String sectie;
    private Long durata;

    public Obiect() {}

    public Obiect(String numePacient, String medic, String sectie, Long durata) {
        this.numePacient = numePacient;
        this.medic = medic;
        this.sectie = sectie;
        this.durata = durata;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public String getMedic() {
        return medic;
    }

    public String getSectie() {
        return sectie;
    }

    public Long getDurata() {
        return durata;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public void setMedic(String medic) {
        this.medic = medic;
    }

    public void setSectie(String sectie) {
        this.sectie = sectie;
    }

    public void setDurata(Long durata) {
        this.durata = durata;
    }

    @Override
    public String toString() {
        return "Consultatie{" +
                "numePacient='" + numePacient + '\'' +
                ", medic='" + medic + '\'' +
                ", sectie='" + sectie + '\'' +
                ", durata=" + durata +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obiect obiect)) return false;
        return Objects.equals(getNumePacient(), obiect.getNumePacient()) && Objects.equals(getMedic(), obiect.getMedic()) && Objects.equals(getSectie(), obiect.getSectie()) && Objects.equals(getDurata(), obiect.getDurata());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumePacient(), getMedic(), getSectie(), getDurata());
    }
}
