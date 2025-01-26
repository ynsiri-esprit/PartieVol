package Services.Impl;

import Entities.TypeVol;
import Entities.Visiteur;
import Entities.Vol;
import Services.IVisiteurService;
import Services.IVolServices;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolServicesImpl implements IVolServices {

    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public VolServicesImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    public void ajouter(Vol vol) throws SQLException {
        String req = "INSERT INTO `vol` (`numeroVol`, `description`, `tarif`, `type`, `compagnie`, `aeroportDepart`, `aeroportArrivee`,`heureDepart`,`heureArrivee`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, vol.getId());
        pre.setString(2, vol.getDescription());
        pre.setFloat(3, vol.getTarif());
        pre.setInt(4, vol.getNumeroVol());
        pre.setString(5, vol.getType().toString());
        pre.setString(6, vol.getCompagnie());
        pre.setString(7, vol.getAeroportDepart());
        pre.setString(8, vol.getAeroportArrivee());
        pre.setDate(9, new java.sql.Date(vol.getHeureDepart().getTime()));
        pre.setDate(10, new java.sql.Date(vol.getHeureArrivee().getTime()));

        pre.executeUpdate();
        System.out.println("Vol ajouté avec succès !");
    }

    public void supprimer(Vol vol) throws SQLException {
        String req = "DELETE FROM `vol` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, vol.getId());
        pre.executeUpdate();
        System.out.println("Vol supprimé avec succès !");
    }

    public void update(Vol vol) throws SQLException {
        String req = "UPDATE `vol` SET `id` = ?, `description` = ?, `tarif` = ?, `numeroVol` = ?, `type` = ?, `compagnie` = ?, `aeroportDepart` = ?, `aeroportArrivee` = ?, `heureDepart` = ?, `heureArrivee` = ?" +
                "WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, vol.getId());
        pre.setString(2, vol.getDescription());
        pre.setFloat(3, vol.getTarif());
        pre.setInt(4, vol.getNumeroVol());
        pre.setString(5, vol.getType().toString());
        pre.setString(6, vol.getCompagnie());
        pre.setString(7, vol.getAeroportDepart());
        pre.setString(8, vol.getAeroportArrivee());
        pre.setDate(9, new java.sql.Date(vol.getHeureDepart().getTime()));
        pre.setDate(10, new java.sql.Date(vol.getHeureArrivee().getTime()));
        pre.executeUpdate();
        System.out.println("Vol mis à jour avec succès !");
    }

    @Override
    public Vol getById(int id) throws SQLException {
        String req = "SELECT * FROM `vol` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            Vol v= new Vol();
                    v.setId(rs.getInt("id"));
                    v.setDescription(rs.getString("description"));
                    v.setTarif(rs.getFloat("tarif"));
                    v.setNumeroVol(rs.getInt("numeroVol"));
                    v.setNumeroVol(rs.getInt("numeroVol"));
                    v.setType(TypeVol.valueOf(rs.getString("type")));
                    v.setCompagnie(rs.getString("compagnie"));
                    v.setAeroportDepart(rs.getString("aeroportDepart"));
                    v.setAeroportArrivee(rs.getString("aeroportArrivee"));
                    v.setHeureDepart(rs.getDate("heureDepart"));
                    v.setHeureArrivee(rs.getDate("heureArrivee"));
            return  v;
        }
        return null;
    }

    @Override
    public List<Vol> getAll() throws SQLException {
        List<Vol> vols = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `vol`");
        while (rs.next()) {
            Vol v= new Vol();
            v.setId(rs.getInt("id"));
            v.setDescription(rs.getString("description"));
            v.setTarif(rs.getFloat("tarif"));
            v.setNumeroVol(rs.getInt("numeroVol"));
            v.setType(TypeVol.valueOf(rs.getString("type")));
            v.setCompagnie(rs.getString("compagnie"));
            v.setAeroportDepart(rs.getString("aeroportDepart"));
            v.setAeroportArrivee(rs.getString("aeroportArrivee"));
            v.setHeureDepart(rs.getDate("heureDepart"));
            v.setHeureArrivee(rs.getDate("heureArrivee"));
            vols.add(v);
        }
        return vols;
    }
}
