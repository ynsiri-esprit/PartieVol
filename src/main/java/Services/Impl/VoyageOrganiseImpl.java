package Services.Impl;
import Entities.VoyageOrganise;
import Services.IVoyageOrganiseService;
import Utils.DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoyageOrganiseImpl implements IVoyageOrganiseService {

    private final Connection con = DataSource.getInstance().getConn();
    private Statement st;

    public VoyageOrganiseImpl() {
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création du Statement : " + e.getMessage());
        }
    }

    @Override
    public void ajouter(VoyageOrganise voyageOrganise) throws SQLException {
        StringBuilder pointInteret = new StringBuilder();
        StringBuilder itineraire = new StringBuilder();
        if(voyageOrganise.getItineraires()!=null&&voyageOrganise.getItineraires().size()>0)
        for(String iteniraire:voyageOrganise.getItineraires())
        {
            itineraire.append(iteniraire).append("/");
        }
        if(voyageOrganise.getPointsIneret()!=null&&voyageOrganise.getPointsIneret().size()>0)
            for(String point:voyageOrganise.getPointsIneret())
        {
            pointInteret.append(point).append("/");
        }
        String req = "INSERT INTO `voyageorganise` (`itineraires`, `dateDepart`, `pointsInteret`, `guideDisponible`,`description`,`Tarif`) " +
                "VALUES ( ?, ?, ?, ?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,itineraire.toString());
        if (voyageOrganise.getDateDepart() != null) {
            pre.setDate(2, new java.sql.Date(voyageOrganise.getDateDepart().getTime()));
        } else {
            pre.setDate(2, null);
        }        pre.setString(3,pointInteret.toString());
        pre.setBoolean(4,voyageOrganise.isGuideDisponible());
        pre.setString(5,voyageOrganise.getDescription());
        pre.setFloat(6,voyageOrganise.getTarif());

        pre.executeUpdate();
        System.out.println("Voyage ajouté avec succès !");
    }

    @Override
    public void supprimer(VoyageOrganise voyageOrganise) throws SQLException {
        String req = "DELETE FROM `voyageorganise` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, voyageOrganise.getId());
        pre.executeUpdate();
        System.out.println("Voyage supprimé avec succès !");
    }

    @Override
    public void update(VoyageOrganise voyageOrganise) throws SQLException {
        StringBuilder pointInteret = new StringBuilder();
        StringBuilder itineraire = new StringBuilder();
        if(voyageOrganise.getItineraires()!=null&&voyageOrganise.getItineraires().size()>0)
            for(String iteniraire:voyageOrganise.getItineraires())
            {
                itineraire.append(iteniraire).append("/");
            }
        if(voyageOrganise.getPointsIneret()!=null&&voyageOrganise.getPointsIneret().size()>0)
            for(String point:voyageOrganise.getPointsIneret())
            {
                pointInteret.append(point).append("/");
            }
        System.out.println("voyage"+voyageOrganise);
        String req = "UPDATE `voyageorganise` SET `itineraires` = ?, `dateDepart` = ?, `pointsInteret` = ?, `guideDisponible` = ?,`description` = ?,`Tarif` = ?" +
                "WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,itineraire.toString());
        if (voyageOrganise.getDateDepart() != null) {
            pre.setDate(2, new java.sql.Date(voyageOrganise.getDateDepart().getTime()));
        } else {
            pre.setDate(2, null);
        }
        pre.setString(3,pointInteret.toString());
        pre.setBoolean(4,voyageOrganise.isGuideDisponible());
        pre.setString(5,voyageOrganise.getDescription());
        pre.setFloat(6,voyageOrganise.getTarif());
        pre.setInt(7,voyageOrganise.getId());

        pre.executeUpdate();
        System.out.println("Voyage mis à jour avec succès !");

    }

    @Override
    public VoyageOrganise getById(int id) throws SQLException {
        String req = "SELECT * FROM `voyageorganise` WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        if (rs.next()) {
            VoyageOrganise v= new VoyageOrganise();
            v.setId(rs.getInt("id"));
            v.setDateDepart(rs.getDate("dateDepart"));
            v.setGuideDisponible(rs.getBoolean("guideDisponible"));
            v.setItineraires(Arrays.stream(rs.getString("itineraires").split("/")).toList());
            v.setPointsIneret(Arrays.stream(rs.getString("pointsInteret").split("/")).toList());
            v.setDescription(rs.getString("description"));
            v.setTarif(rs.getFloat("Tarif"));

            return  v;
        }
        return null;
    }

    @Override
    public List<VoyageOrganise> getAll() throws SQLException {
        List<VoyageOrganise> vols = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM `voyageorganise`");
        while (rs.next()) {
            VoyageOrganise v= new VoyageOrganise();
            v.setId(rs.getInt("id"));
            v.setDateDepart(rs.getDate("dateDepart"));
            v.setGuideDisponible(rs.getBoolean("guideDisponible"));
            v.setItineraires(Arrays.stream(rs.getString("itineraires").split("/")).toList());
            v.setPointsIneret(Arrays.stream(rs.getString("pointsInteret").split("/")).toList());
            v.setDescription(rs.getString("description"));
            v.setTarif(rs.getFloat("Tarif"));
            vols.add(v);
        }
        return vols;    }

    @Override
    public List<VoyageOrganise> getByDate(LocalDate date) throws SQLException {
        System.out.println(date);
        List<VoyageOrganise> voyages = new ArrayList<>();
        if(date==null)
        {
           return getAll();
        }
        else {
            String req = "SELECT * FROM `voyageorganise` WHERE `dateDepart` = ?";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setDate(1, Date.valueOf(date));
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                VoyageOrganise v = new VoyageOrganise();
                v.setId(rs.getInt("id"));
                v.setDateDepart(rs.getDate("dateDepart"));
                v.setGuideDisponible(rs.getBoolean("guideDisponible"));
                v.setItineraires(Arrays.stream(rs.getString("itineraires").split("/")).toList());
                v.setPointsIneret(Arrays.stream(rs.getString("pointsInteret").split("/")).toList());
                v.setDescription(rs.getString("description"));
                v.setTarif(rs.getFloat("Tarif"));

                voyages.add(v);
            }
        }
        return voyages;
    }

}
