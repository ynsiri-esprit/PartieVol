package Services.Impl;
import Entities.Client;
import Services.IService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientServiceImpl implements IService<Client> {

    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public ClientServiceImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(Client client) throws SQLException {
        String req = "INSERT INTO `client` (`cin`, `nom`, `prenom`, `date_naissance`, `email`, `telephone`, `login`, `mot_de_passe`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, client.getCin());
        pre.setString(2, client.getNom());
        pre.setString(3, client.getPrenom());
        pre.setDate(4, new java.sql.Date(client.getDateNaissance().getTime()));
        pre.setString(5, client.getEmail());
        pre.setString(6, client.getTelephone());
        pre.setString(7, client.getLogin());
        pre.setString(8, client.getMotDePasse());
        pre.executeUpdate();
        System.out.println("Client ajouté avec succès !");
    }

    @Override
    public void supprimer(Client client) throws SQLException {
        String req = "DELETE FROM `client` WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, client.getCin());
        pre.executeUpdate();
        System.out.println("Client supprimé avec succès !");
    }

    @Override
    public void update(Client client) throws SQLException {
        String req = "UPDATE `client` SET `nom` = ?, `prenom` = ?, `date_naissance` = ?, `email` = ?, `telephone` = ?, `login` = ?, `mot_de_passe` = ? " +
                "WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, client.getNom());
        pre.setString(2, client.getPrenom());
        pre.setDate(3, new java.sql.Date(client.getDateNaissance().getTime()));
        pre.setString(4, client.getEmail());
        pre.setString(5, client.getTelephone());
        pre.setString(6, client.getLogin());
        pre.setString(7, client.getMotDePasse());
        pre.setString(8, client.getCin());
        pre.executeUpdate();
        System.out.println("Client mis à jour avec succès !");
    }

    @Override
    public Client getById(int id) throws SQLException {
        String req = "SELECT * FROM `client` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return new Client(
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
    public List<Client> getAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `client`");
        while (rs.next()) {
            clients.add(new Client(
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
        return clients;
    }
    public static boolean seConnecter(String login, String motDePasse) {  // Méthode Se connecter
        String sql = "SELECT * FROM client WHERE login = ? AND motDePasse = ?";
        try (Connection conn = DataSource.getInstance().getConn();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, motDePasse);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        //test
    }

}


