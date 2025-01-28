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
        String req = "INSERT INTO `supporttech` (`login`, `motdepasse`, `codeAppli`) VALUES (?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, supportTech.getLogin());
        pre.setString(2, supportTech.getMotDePasse());
        pre.setInt(3, supportTech.getCodeAppli());
        pre.executeUpdate();
        System.out.println("SupportTech ajouté avec succès !");
    }

    @Override
    public void supprimer(SupportTech supportTech) throws SQLException {
        String req = "DELETE FROM `supporttech` WHERE `login` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, supportTech.getLogin());
        pre.executeUpdate();
        System.out.println("SupportTech supprimé avec succès !");
    }

    @Override
    public void update(SupportTech supportTech) throws SQLException {
        String req = "UPDATE `supporttech` SET `motdepasse` = ?, `codeAppli` = ? WHERE `login` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, supportTech.getMotDePasse());
        pre.setInt(2, supportTech.getCodeAppli());
        pre.setString(3, supportTech.getLogin());
        pre.executeUpdate();
        System.out.println("SupportTech mis à jour avec succès !");
    }

    @Override
    public SupportTech getById(int id) throws SQLException {
        return null; //no id for this class
    }

    @Override
    public SupportTech getByLogin(String login) throws SQLException {
        String req = "SELECT * FROM `supporttech` WHERE `login` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, login);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return new SupportTech(
                    rs.getString("login"),
                    rs.getString("motdepasse"),
                    rs.getInt("codeAppli")
            );
        }
        return null;
    }

    @Override
    public SupportTech checkSupportByCode(int code) throws SQLException {
        String req = "SELECT * FROM `supporttech` WHERE `codeAppli` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, code);
        ResultSet rs = pre.executeQuery();

        if (rs.next()) {
            return new SupportTech(
                    rs.getString("login"),
                    rs.getString("motdepasse"),
                    rs.getInt("codeAppli")
            );
        }
        return null;
    }


    @Override
    public List<SupportTech> getAll() throws SQLException {
        List<SupportTech> supports = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `supporttech`");
        while (rs.next()) {
            supports.add(new SupportTech(
                    rs.getString("login"),
                    rs.getString("motdepasse"),
                    rs.getInt("codeAppli")
            ));
        }
        return supports;
    }
}

