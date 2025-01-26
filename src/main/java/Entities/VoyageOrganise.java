package Entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class VoyageOrganise extends Offre {

    private List<String> itineraires;
    private Date dateDepart;
    private List<String> pointsIneret;
    private boolean guideDisponible;

    public List<String> getItineraires() {
        return itineraires;
    }

    public void setItineraires(List<String> itineraires) {
        this.itineraires = itineraires;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public List<String> getPointsIneret() {
        return pointsIneret;
    }

    public void setPointsIneret(List<String> pointsIneret) {
        this.pointsIneret = pointsIneret;
    }

    public boolean isGuideDisponible() {
        return guideDisponible;
    }

    public void setGuideDisponible(boolean guideDisponible) {
        this.guideDisponible = guideDisponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoyageOrganise that)) return false;
        return guideDisponible == that.guideDisponible && Objects.equals(itineraires, that.itineraires) && Objects.equals(dateDepart, that.dateDepart) && Objects.equals(pointsIneret, that.pointsIneret);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itineraires, dateDepart, pointsIneret, guideDisponible);
    }

    @Override
    public String toString() {
        return "VoyageOrganise{" +
                "itineraires=" + itineraires +
                ", dateDepart=" + dateDepart +
                ", pointsIneret=" + pointsIneret +
                ", guideDisponible=" + guideDisponible +
                '}';
    }
}
