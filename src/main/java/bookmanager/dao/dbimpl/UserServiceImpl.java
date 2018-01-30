package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.UserService;
import bookmanager.model.vo.UserLoginVO;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class UserServiceImpl implements UserService {
    private JdbcOperations jdbcOperations;

    private final static String GET_PASSWORD_AND_UID_BY_USERNAME = "SELECT uid, name, password FROM cs_user WHERE name = ?";

    private final static String GET_USERNAME_BY_UID = "SELECT name FROM cs_user WHERE uid = ?";

    @Autowired
    public UserServiceImpl(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public UserLoginVO getPasswordAndUidByUsername(String name) {
        try {
            return (UserLoginVO) jdbcOperations.queryForObject(GET_PASSWORD_AND_UID_BY_USERNAME,
                    JdbcRowMapper.newInstance(UserLoginVO.class), name);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String getUserNameByUid(int uid) {
        return jdbcOperations.queryForObject(GET_USERNAME_BY_UID, String.class, uid);
    }
}