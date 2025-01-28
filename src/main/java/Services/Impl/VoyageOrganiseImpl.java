package Services.Impl;

import Entities.TypeVol;
import Entities.Vol;
import Entities.VoyageOrganise;
import Services.IVoyageOrganiseService;
import Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VoyageOrganiseImpl implements IVoyageOrganiseService {

    private Connection con = DataSource.getInstance().getConn();
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
        String inteniraire= "";
        String pointInteret="";
        for(String iteniraire:voyageOrganise.getItineraires())
        {
            inteniraire+=iteniraire+"/";
        }
        for(String point:voyageOrganise.getPointsIneret())
        {
            pointInteret+=point+"/";
        }
        String req = "INSERT INTO `voyageorganise` (`id`, `itineraires`, `dateDepart`, `pointsInteret`, `guideDisponible`,`description`,`Tarif`) " +
                "VALUES (?, ?, ?, ?, ?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, voyageOrganise.getId());
        pre.setString(2,inteniraire);
        pre.setDate(3,new java.sql.Date(voyageOrganise.getDateDepart().getTime()));
        pre.setString(4,pointInteret);
        pre.setBoolean(5,voyageOrganise.isGuideDisponible());
        pre.setString(6,voyageOrganise.getDescription());
        pre.setFloat(7,voyageOrganise.getTarif());

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
        String inteniraire= "";
        String pointInteret="";
        for(String iteniraire:voyageOrganise.getItineraires())
        {
            inteniraire+=iteniraire+"/";
        }
        for(String point:voyageOrganise.getPointsIneret())
        {
            pointInteret+=point+"/";
        }
        String req = "UPDATE `voyageorganise` SET `itineraires` = ?, `dateDepart` = ?, `pointsInteret` = ?, `guideDisponible` = ?,`description` = ?,`Tarif` = ?" +
                "WHERE `id` = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1,inteniraire);
        pre.setDate(2,new java.sql.Date(voyageOrganise.getDateDepart().getTime()));
        pre.setString(3,pointInteret);
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
}
