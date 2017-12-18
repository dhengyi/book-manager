package bookmanager.dao.dbimpl;

import bookmanager.dao.dbservice.UserService;
import bookmanager.model.po.UserPO;
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
public class UserServiceImpl implements UserService {
    private JdbcOperations jdbc;

    private final static String GET_USER_BY_NAME = "SELECT * FROM cs_user WHERE name = ?";
    private final static String GET_USER_BY_ID = "SELECT * FROM cs_user WHERE uid = ?";
    private final static String QUERY_PRIVILEGE_BY_UID = "SELECT privilege FROM cs_user " +
            "WHERE uid = ?";

    @Autowired
    public UserServiceImpl(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public UserPO getUserByName(String name) {
        return jdbc.queryForObject(GET_USER_BY_NAME, new UserRowMapper(), name);
    }

    @Override
    public int getPrivilegeByUid(int uid) {
        return jdbc.queryForObject(QUERY_PRIVILEGE_BY_UID, int.class, uid);
    }

    public UserPO getUserById(int id) {
        return jdbc.queryForObject(GET_USER_BY_ID, new UserRowMapper(), id);
    }


    private final static class UserRowMapper implements RowMapper<UserPO> {

        public UserPO mapRow(ResultSet resultSet, int i) throws SQLException {
            return new UserPO(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12),
                    resultSet.getString(13),
                    resultSet.getString(14),
                    resultSet.getString(15),
                    resultSet.getString(16)
            );
        }
    }
}
