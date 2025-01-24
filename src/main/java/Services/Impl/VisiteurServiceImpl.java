package Services.Impl;
import Entities.Visiteur;
import Services.IVisiteurService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisiteurServiceImpl implements IVisiteurService {
    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public VisiteurServiceImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(Visiteur visiteur) throws SQLException {
        String req = "INSERT INTO `visiteur` (`cin`, `nom`, `prenom`, `date_naissance`, `email`, `telephone`, `login`, `mot_de_passe`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, visiteur.getCin());
        pre.setString(2, visiteur.getNom());
        pre.setString(3, visiteur.getPrenom());
        pre.setDate(4, new java.sql.Date(visiteur.getDateNaissance().getTime()));
        pre.setString(5, visiteur.getEmail());
        pre.setString(6, visiteur.getTelephone());
        pre.setString(7, visiteur.getLogin());
        pre.setString(8, visiteur.getMotDePasse());
        pre.executeUpdate();
        System.out.println("Visiteur ajouté avec succès !");
    }

    @Override
    public void supprimer(Visiteur visiteur) throws SQLException {
        String req = "DELETE FROM `visiteur` WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, visiteur.getCin());
        pre.executeUpdate();
        System.out.println("Visiteur supprimé avec succès !");
    }

    @Override
    public void update(Visiteur visiteur) throws SQLException {
        String req = "UPDATE `visiteur` SET `nom` = ?, `prenom` = ?, `date_naissance` = ?, `email` = ?, `telephone` = ?, `login` = ?, `mot_de_passe` = ? " +
                "WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, visiteur.getNom());
        pre.setString(2, visiteur.getPrenom());
        pre.setDate(3, new java.sql.Date(visiteur.getDateNaissance().getTime()));
        pre.setString(4, visiteur.getEmail());
        pre.setString(5, visiteur.getTelephone());
        pre.setString(6, visiteur.getLogin());
        pre.setString(7, visiteur.getMotDePasse());
        pre.setString(8, visiteur.getCin());
        pre.executeUpdate();
        System.out.println("Visiteur mis à jour avec succès !");
    }

    @Override
    public Visiteur getById(int id) throws SQLException {
        String req = "SELECT * FROM `visiteur` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return new Visiteur(
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
    public List<Visiteur> getAll() throws SQLException {
        List<Visiteur> visiteurs = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `visiteur`");
        while (rs.next()) {
            visiteurs.add(new Visiteur(
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
        return visiteurs;
    }
}
