package bookmanager.model.po;

/**
 * Created by dela on 11/22/17.
 */

// cs用户表
public class UserPO {
    private int uid;
    private String name;
    private int privilege;
    private String password;
    private int sex;
    private String phone;
    private String mail;
    private String qq;
    private String wechat;
    private String blog;
    private String github;
    private String _native;     // native是java的一个关键字, 但是数据库里有名为native的属性, 所以用前置下划线在这里区分
    private String grade;
    private String major;
    private String workplace;
    private String job;

    public UserPO() { }

    public UserPO(int uid, String name, int privilege, String password, int sex, String phone, String mail, String qq,
                  String wechat, String blog, String github, String _native, String grade, String major, String workplace, String job) {
        this.uid = uid;
        this.name = name;
        this.privilege = privilege;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.mail = mail;
        this.qq = qq;
        this.wechat = wechat;
        this.blog = blog;
        this.github = github;
        this._native = _native;
        this.grade = grade;
        this.major = major;
        this.workplace = workplace;
        this.job = job;
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

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String get_native() {
        return _native;
    }

    public void set_native(String _native) {
        this._native = _native;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
