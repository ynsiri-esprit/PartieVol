package Services.Impl;

import Entities.Agent;
import Services.IAgentService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgentServiceImpl implements IAgentService {
    private Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public AgentServiceImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(Agent agent) throws SQLException {
        String req = "INSERT INTO `agent` (`cin`, `nom`, `prenom`, `date_naissance`, `email`, `telephone`, `login`, `mot_de_passe`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, agent.getCin());
        pre.setString(2, agent.getNom());
        pre.setString(3, agent.getPrenom());
        pre.setDate(4, new java.sql.Date(agent.getDateNaissance().getTime()));
        pre.setString(5, agent.getEmail());
        pre.setString(6, agent.getTelephone());
        pre.setString(7, agent.getLogin());
        pre.setString(8, agent.getMotDePasse());
        pre.executeUpdate();
        System.out.println("Agent ajouté avec succès !");
    }

    @Override
    public void supprimer(Agent agent) throws SQLException {
        String req = "DELETE FROM `agent` WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, agent.getCin());
        pre.executeUpdate();
        System.out.println("Agent supprimé avec succès !");
    }

    @Override
    public void update(Agent agent) throws SQLException {
        String req = "UPDATE `agent` SET `nom` = ?, `prenom` = ?, `date_naissance` = ?, `email` = ?, `telephone` = ?, `login` = ?, `mot_de_passe` = ? " +
                "WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, agent.getNom());
        pre.setString(2, agent.getPrenom());
        pre.setDate(3, new java.sql.Date(agent.getDateNaissance().getTime()));
        pre.setString(4, agent.getEmail());
        pre.setString(5, agent.getTelephone());
        pre.setString(6, agent.getLogin());
        pre.setString(7, agent.getMotDePasse());
        pre.setString(8, agent.getCin());
        pre.executeUpdate();
        System.out.println("Agent mis à jour avec succès !");
    }

    @Override
    public Agent getById(int id) throws SQLException {
        String req = "SELECT * FROM `agent` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return new Agent(
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
    public List<Agent> getAll() throws SQLException {
        List<Agent> agents = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `agent`");
        while (rs.next()) {
            agents.add(new Agent(
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
        return agents;
    }

    @Override
    public void sendEmailToAgent(Agent agent) {
        EmailService emailService = new EmailService();

        String subject = "Vos informations de connexion";
        String messageBody = "Bonjour " + agent.getNom() + " " + agent.getPrenom() + ",\n\n" +
                "Voici vos informations de connexion :\n" +
                "Login : " + agent.getLogin() + "\n" +
                "Mot de passe : " + agent.getMotDePasse() + "\n\n" +
                "Cordialement,\nL'équipe support";

        emailService.sendEmail(agent.getEmail(), subject, messageBody);
    }
}
