package Entities;

import Services.Impl.ClientServiceImpl;
//import Services.Impl.ReservationServiceImpl;
import java.util.Date;
import java.sql.SQLException;

public class Client extends Visiteur {
    // Constructeur non paramétré
    public Client() {}

    // Constructeur paramétré
    public Client(String cin, String nom, String prenom, Date dateNaissance, String email, String telephone, String login, String motDePasse) {
        super(cin, nom, prenom, dateNaissance, email, telephone, login, motDePasse);
    }

    public boolean seConnecter() {
        return ClientServiceImpl.seConnecter(this.getLogin(), this.getMotDePasse());  // Appel direct à la méthode statique
    }

//
    //  une réservation
   /* public void annulerReservation(int reservationId) {
        ReservationServiceImpl reservationService = new ReservationServiceImpl();
        try {
            reservationService.annulerReservation(reservationId);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'annulation de la réservation : " + e.getMessage());
        }
    }*/








}
