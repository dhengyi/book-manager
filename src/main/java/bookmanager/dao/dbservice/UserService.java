package bookmanager.dao.dbservice;

import bookmanager.model.vo.UserLoginVO;

/**
 * Created by dela on 11/23/17.
 */
public interface UserService {
    // 按用户的名字查询密码和uid(将密码和uid放进UserLoginVO对象中)
    UserLoginVO getPasswordAndUidByUsername(String name);

    // 通过uid得到User的名字
    String getUserNameByUid(int uid);
}
