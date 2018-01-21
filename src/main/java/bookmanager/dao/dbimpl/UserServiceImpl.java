package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.PagePO;
import bookmanager.model.po.UserPO;
import bookmanager.model.vo.login.UserLoginVO;
import bookmanager.dao.rowmapper.JdbcRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dela on 11/23/17.
 */

@Repository
public class UserServiceImpl implements UserService {
    private JdbcOperations jdbcOperations;

    private final static String GET_USER_BY_NAME = "SELECT * FROM cs_user WHERE name = ?";
    private final static String GET_USER_BY_ID = "SELECT * FROM cs_user WHERE uid = ?";
    private final static String GET_PASSWORD_AND_UID_BY_NAME = "SELECT uid, password FROM cs_user WHERE name = ?";
    private final static String GET_USERNAMES_BY_UIDS = "SELECT name FROM cs_user WHERE uid IN (?)";

    @Autowired
    public UserServiceImpl(JdbcOperations jdbc) {
        this.jdbcOperations = jdbc;
    }

    public UserPO getUserByName(String name) {
        return (UserPO) jdbcOperations.queryForObject(GET_USER_BY_NAME, JdbcRowMapper.newInstance(UserPO.class), name);
    }

    public UserPO getUserById(int id) {
        return (UserPO) jdbcOperations.queryForObject(GET_USER_BY_ID, JdbcRowMapper.newInstance(UserPO.class), id);
    }

    public UserLoginVO getPasswordAndUidByName(String name) {
        return (UserLoginVO) jdbcOperations.queryForObject(GET_PASSWORD_AND_UID_BY_NAME,
                JdbcRowMapper.newInstance(UserLoginVO.class), name);
    }

    public String getUsernameById(int id) {
        return jdbcOperations.queryForObject(GET_USERNAMES_BY_UIDS, String.class, id);
    }

}
