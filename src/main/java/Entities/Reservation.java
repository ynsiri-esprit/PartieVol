package Entities;

import java.util.Date;

public class Reservation {
    private int id;
    private Date date;                // Date de la réservation
    private int nbreParticipant;      // Nombre de participants
    private String modePaiement;      // Mode de paiement (ex: carte bancaire, espèces, etc.)

    // Constructeur non paramétré
    public Reservation() {}

    // Constructeur paramétré
    public Reservation(int id, Date date, int nbreParticipant, String modePaiement) {
        this.id = id;
        this.date = date;
        this.nbreParticipant = nbreParticipant;
        this.modePaiement = modePaiement;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbreParticipant() {
        return nbreParticipant;
    }

    public void setNbreParticipant(int nbreParticipant) {
        this.nbreParticipant = nbreParticipant;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    // toString() pour afficher les informations de la réservation
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", nbreParticipant=" + nbreParticipant +
                ", modePaiement='" + modePaiement + '\'' +
                '}';
    }


}
