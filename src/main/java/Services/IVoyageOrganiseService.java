package Services;

import Entities.VoyageOrganise;
import Services.Impl.VoyageOrganiseImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IVoyageOrganiseService extends IService<VoyageOrganise>{
    public List<VoyageOrganise> getByDate(LocalDate date) throws SQLException;
        }

