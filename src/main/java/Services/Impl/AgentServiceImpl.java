package Services.Impl;

import Entities.Agent;
import Services.IAgentService;
import Utils.DataSource;

import java.sql.*;
import java.time.LocalDate;
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
        String req = "INSERT INTO `utilisateur` (`cin`, `nom`, `prenom`, `date_naissance`, `email`, `telephone`, `login`, `mot_de_passe`) " +
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
        String req = "DELETE FROM `utilisateur` WHERE `cin` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, agent.getCin());
        pre.executeUpdate();
        System.out.println("Agent supprimé avec succès !");
    }

    @Override
    public void update(Agent agent) throws SQLException {
        String req = "UPDATE `utilisateur` SET `nom` = ?, `prenom` = ?, `date_naissance` = ?, `email` = ?, `telephone` = ?, `login` = ?, `mot_de_passe` = ? " +
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
        String req = "SELECT * FROM `utilisateur` WHERE `id` = ?";
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
        ResultSet rs = st.executeQuery("SELECT * FROM `utilisateur`");
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

    @Override
    public boolean Login(String login,String password) throws SQLException {
        int count=0;
        String req = "SELECT count(*) AS NB FROM `utilisateur` WHERE `login` = ? AND `motDePasse` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, login);
        pre.setString(2, password);

        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            count = rs.getInt("NB");
            return count > 0;    }
        return false;
}

    @Override
    public int StatsBookingToDay() throws SQLException {
        LocalDate today = LocalDate.now();
        String req = "SELECT count(*) AS NB FROM `reservation` where `date`=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1,Date.valueOf(today));
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return rs.getInt("NB");
               }
    return 0;}

    @Override
    public int StatsBookingGeneral() throws SQLException {
        LocalDate today = LocalDate.now();
        String req = "SELECT count(*) AS NB FROM `reservation`";
        PreparedStatement pre = con.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return rs.getInt("NB");
        }
        return 0;    }


    @Override
    public int StatsFlightToDay() throws SQLException {
        LocalDate today = LocalDate.now();
        String req = "SELECT count(*) AS NB FROM `voyageorganise` where `dateDepart`=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1,Date.valueOf(today));
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            return rs.getInt("NB");
        }
        return 0;    }

    @Override
    public int StatsFlightGneral() throws SQLException {
        String req = "SELECT count(*) AS NB FROM `voyageorganise`";
        int nbFlights = 0;
        try (PreparedStatement pre = con.prepareStatement(req);
             ResultSet rs = pre.executeQuery()) {

            if (rs.next()) {
                nbFlights = rs.getInt("NB");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération du nombre de vols", e);
        }

        return nbFlights;
    }
    public int StatsSumGneral() throws SQLException {
        String req = "SELECT SUM(Tarif) AS NB FROM `voyageorganise`";
        int SumGeneral = 0;
PreparedStatement pre = con.prepareStatement(req);
             ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                SumGeneral = rs.getInt("NB");
            }


        return SumGeneral;
    }

    public int StatsSumToDay() throws SQLException {
        LocalDate today = LocalDate.now();
        String req = "SELECT SUM(Tarif) AS NB FROM `voyageorganise` where `dateDepart`=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setDate(1,Date.valueOf(today));
        int SumGeneral = 0;
             ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                SumGeneral = rs.getInt("NB");
            }
        return SumGeneral;
    }
}

