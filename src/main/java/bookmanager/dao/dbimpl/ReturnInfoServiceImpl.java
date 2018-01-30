package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.ReturnInfoService;
import bookmanager.model.po.ReturnInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class ReturnInfoServiceImpl implements ReturnInfoService {
    private JdbcOperations jdbcOperations;

    private final static String SAVE = "INSERT INTO return_info(book_info_pk_id, cs_user_uid, return_date) VALUES(?, ?, ?)";

    @Autowired
    public ReturnInfoServiceImpl(JdbcOperations jdbc) {
        this.jdbcOperations = jdbc;
    }

    @Override
    public void save(ReturnInfoPO returnInfoPO) {
        jdbcOperations.update(SAVE, returnInfoPO.getBookInfoPkId(), returnInfoPO.getCsUserId(), new Date());
    }
}