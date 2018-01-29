package bookmanager.model.vo;

import bookmanager.annotation.Column;
import bookmanager.annotation.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by dela on 12/5/17.
 */

@Table(name = "cs_user")
public class UserLoginVO {
    @Column(name = "uid")
    private int uid;

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 16)
    private String name;

    @Column(name = "password")
    @NotNull
    @Size(min = 1, max = 16)
    private String password;

    public UserLoginVO() {
    }

    public UserLoginVO(int uid, String name, String password) {
        this.uid = uid;
        this.name = name;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}