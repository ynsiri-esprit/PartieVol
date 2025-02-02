package Services.Impl;

import Entities.SupportTech;
import Services.ISupportTechService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupportTechServiceImpl implements ISupportTechService {

    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public SupportTechServiceImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(SupportTech supportTech) throws SQLException {
        String req = "INSERT INTO `utilisateur` (`cin`, `nom`, `prenom`, `date_naissance`, `email`, `telephone`, `login`, `mot_de_passe`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, supportTech.getCin());
        pre.setString(2, supportTech.getNom());
        pre.setString(3, supportTech.getPrenom());
        pre.setDate(4, new java.sql.Date(supportTech.getDateNaissance().getTime()));
        pre.setString(5, supportTech.getEmail());
        pre.setString(6, supportTech.getTelephone());
        pre.setString(7, supportTech.getLogin());
        pre.setString(8, supportTech.getMotDePasse());
        pre.executeUpdate();
        System.out.println("SupportTech ajouté avec succès !");
    }

    @Override
    public void supprimer(SupportTech supportTech) throws SQLException {
        String req = "DELETE FROM `utilisateur` WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, supportTech.getLogin());
        pre.executeUpdate();
        System.out.println("SupportTech supprimé avec succès !");
    }

    @Override
    public void update(SupportTech supportTech) throws SQLException {
        String req = "UPDATE `utilisateur` SET `nom` = ?, `prenom` = ?, `date_naissance` = ?, `email` = ?, `telephone` = ?, `login` = ?, `mot_de_passe` = ? " +
                "WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, supportTech.getNom());
        pre.setString(2, supportTech.getPrenom());
        pre.setDate(3, new java.sql.Date(supportTech.getDateNaissance().getTime()));
        pre.setString(4, supportTech.getEmail());
        pre.setString(5, supportTech.getTelephone());
        pre.setString(6, supportTech.getLogin());
        pre.setString(7, supportTech.getMotDePasse());
        pre.setString(8, supportTech.getCin());
        pre.executeUpdate();
        System.out.println("SupportTech mis à jour avec succès !");
    }

    @Override
    public SupportTech getById(int id) throws SQLException {
        String req = "SELECT * FROM `utilisateur` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return new SupportTech(
                    rs.getString("cin"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getDate("date_naissance"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getString("login"),
                    rs.getString("mot_de_passe")
            );
        }
        return null;
    }



    @Override
    public List<SupportTech> getAll() throws SQLException {
        List<SupportTech> supports = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `utilisateur`");
        while (rs.next()) {
            supports.add(new SupportTech(
                    rs.getString("cin"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getDate("date_naissance"),
                    rs.getString("email"),
                    rs.getString("telephone"),
                    rs.getString("login"),
                    rs.getString("mot_de_passe")
            ));
        }
        return supports;
    }
}

