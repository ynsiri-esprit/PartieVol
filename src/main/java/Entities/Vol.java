package Entities;

import java.util.Date;
import java.util.Objects;

public class Vol extends Offre{
    public Vol(){
    }

    public Vol(int id, String description, float tarif, int numeroVol, TypeVol type, String compagnie, String aeroportDepart, String aeroportArrivee, Date heureDepart, Date heureArrivee) {
        super(id, description, tarif);
        this.numeroVol = numeroVol;
        this.type = type;
        this.compagnie = compagnie;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrivee = aeroportArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
    }

    private int numeroVol;
    private TypeVol type;
    private String compagnie;
    private String aeroportDepart;
    private String aeroportArrivee;
    private Date heureDepart;
    private Date heureArrivee;


    public int getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(int numeroVol) {
        this.numeroVol = numeroVol;
    }

    public TypeVol getType() {
        return type;
    }

    public void setType(TypeVol type) {
        this.type = type;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }

    public String getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(String aeroportDepart) {
        this.aeroportDepart = aeroportDepart;
    }

    public String getAeroportArrivee() {
        return aeroportArrivee;
    }

    public void setAeroportArrivee(String aeroportArrivee) {
        this.aeroportArrivee = aeroportArrivee;
    }

    public Date getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Date heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Date getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Date heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vol vol)) return false;
        if (!super.equals(o)) return false;
        return numeroVol == vol.numeroVol && type == vol.type && Objects.equals(compagnie, vol.compagnie) && Objects.equals(aeroportDepart, vol.aeroportDepart) && Objects.equals(aeroportArrivee, vol.aeroportArrivee) && Objects.equals(heureDepart, vol.heureDepart) && Objects.equals(heureArrivee, vol.heureArrivee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroVol, type, compagnie, aeroportDepart, aeroportArrivee, heureDepart, heureArrivee);
    }

    @Override
    public String toString() {
        return "Vol{" +
                "numeroVol=" + numeroVol +
                ", type=" + type +
                ", compagnie='" + compagnie + '\'' +
                ", aeroportDepart='" + aeroportDepart + '\'' +
                ", aeroportArrivee='" + aeroportArrivee + '\'' +
                ", heureDepart=" + heureDepart +
                ", heureArrivee=" + heureArrivee +
                '}';
    }
}
