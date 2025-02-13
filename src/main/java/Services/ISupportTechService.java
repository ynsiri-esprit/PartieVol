package Services;

import Entities.SupportTech;

import java.sql.SQLException;

public interface ISupportTechService extends IService<SupportTech> {

    SupportTech getByLogin(String login) throws SQLException;
    SupportTech checkSupportByCode(int code) throws SQLException;
}
