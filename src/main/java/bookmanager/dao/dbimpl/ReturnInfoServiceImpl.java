package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.ReturnInfoService;
import bookmanager.model.po.ReturnInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class ReturnInfoServiceImpl implements ReturnInfoService {
    private JdbcOperations jdbc;

    private final static String SAVE = "INSERT INTO return_info(book_info_pk_id, cs_user_uid, return_date) VALUES(?, ?, ?)";


    @Autowired
    public ReturnInfoServiceImpl(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public void save(ReturnInfoPO returnInfo) {
        jdbc.update(SAVE, returnInfo.getBookInfoPkId(), returnInfo.getUserId(), returnInfo.getReturnDate());
    }

    private final static class ReturnInfoRowMapper implements RowMapper<ReturnInfoPO> {

        public ReturnInfoPO mapRow(ResultSet resultSet, int i) throws SQLException {
            return new ReturnInfoPO(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4)
            );
        }
    }
}
