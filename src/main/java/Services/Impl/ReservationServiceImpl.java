package Services.Impl;

import Entities.Reservation;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImpl {

    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public ReservationServiceImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }





    // Annuler une réservation
    public void annulerReservation(int reservationId) throws SQLException {
        String req = "DELETE FROM `reservation` WHERE `id` = ?";
        try (PreparedStatement pre = con.prepareStatement(req)) {
            pre.setInt(1, reservationId);
            pre.executeUpdate();
            System.out.println("Réservation annulée avec succès !");
        }
    }



    // Récupérer toutes les réservations
    public List<Reservation> getAllReservations() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `reservation`");
        while (rs.next()) {
            reservations.add(new Reservation(
                    rs.getInt("id"),
                    rs.getDate("date_reservation"),
                    rs.getInt("nbre_participant"),
                    rs.getString("mode_paiement")
            ));
        }
        return reservations;
    }
}
