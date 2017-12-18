package bookmanager.dao.dbservice;

import bookmanager.model.po.UserPO;

/**
 * Created by dela on 11/23/17.
 */
public interface UserService {

    // 按用户名查询用户
    UserPO getUserByName(String name);

    // 按用户id查询用户
    UserPO getUserById(int id);

    int getPrivilegeByUid(int uid);
}
