package Services.Impl;

import Entities.SejourHotel;
import Services.ISejourHotelService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SejourHotelServiceImpl implements ISejourHotelService {
    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public SejourHotelServiceImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(SejourHotel sejourHotel) throws SQLException {
        String req = "INSERT INTO `sejour_hotel` (`id`, `description`, `tarif`, `nomHotel`, `nbrChambresDispo`, `adresse`, `nbEtoiles`, `tarifParNuit`, `noteMoyenne`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, sejourHotel.getId());
        pre.setString(2, sejourHotel.getDescription());
        pre.setFloat(3, sejourHotel.getTarif());
        pre.setString(4, sejourHotel.getNomHotel());
        pre.setInt(5, sejourHotel.getNbrChambresDispo());
        pre.setString(6, sejourHotel.getAdresse());
        pre.setInt(7, sejourHotel.getNbrEtoiles());
        pre.setDouble(8, sejourHotel.getTarifParNuit());
        pre.setDouble(9, sejourHotel.getNoteMoyenne());
        pre.executeUpdate();
        System.out.println("SejourHotel ajouté avec succès !");
    }

    @Override
    public void supprimer(SejourHotel sejourHotel) throws SQLException {
        String req = "DELETE FROM `sejour_hotel` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, sejourHotel.getId());
        pre.executeUpdate();
        System.out.println("SejourHotel supprimé avec succès !");
    }

    @Override
    public void update(SejourHotel sejourHotel) throws SQLException {
        String req = "UPDATE `sejour_hotel` SET `description` = ?, `tarif` = ?, `nomHotel` = ?, `nbrChambresDispo` = ?, `adresse` = ?, `nbEtoiles` = ?, `tarifParNuit` = ?, `noteMoyenne` = ? WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, sejourHotel.getDescription());
        pre.setFloat(2, sejourHotel.getTarif());
        pre.setString(3, sejourHotel.getNomHotel());
        pre.setInt(4, sejourHotel.getNbrChambresDispo());
        pre.setString(5, sejourHotel.getAdresse());
        pre.setInt(6, sejourHotel.getNbrEtoiles());
        pre.setDouble(7, sejourHotel.getTarifParNuit());
        pre.setDouble(8, sejourHotel.getNoteMoyenne());
        pre.setInt(9, sejourHotel.getId());
        pre.executeUpdate();
        System.out.println("SejourHotel mis à jour avec succès !");
    }

    @Override
    public SejourHotel getById(int id) throws SQLException {
        String req = "SELECT * FROM `sejour_hotel` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return new SejourHotel(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getFloat("tarif"),
                    rs.getString("nomHotel"),
                    rs.getInt("nbrChambresDispo"),
                    rs.getString("adresse"),
                    rs.getInt("nbEtoiles"),
                    rs.getDouble("tarifParNuit"),
                    rs.getDouble("noteMoyenne")
            );
        }
        return null;
    }

    @Override
    public List<SejourHotel> getAll() throws SQLException {
        List<SejourHotel> sejours = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `sejour_hotel`");
        while (rs.next()) {
            sejours.add(new SejourHotel(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getFloat("tarif"),
                    rs.getString("nomHotel"),
                    rs.getInt("nbrChambresDispo"),
                    rs.getString("adresse"),
                    rs.getInt("nbEtoiles"),
                    rs.getDouble("tarifParNuit"),
                    rs.getDouble("noteMoyenne")
            ));
        }
        return sejours;
    }
}
