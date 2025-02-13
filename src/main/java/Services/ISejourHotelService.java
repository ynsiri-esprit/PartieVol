package Services;

import java.sql.SQLException;
import java.util.List;
import Entities.SejourHotel ;
public interface ISejourHotelService extends IService<SejourHotel> {
    List<SejourHotel> getSejourHotelByCriteria(SejourHotel criteria) throws SQLException;
}
