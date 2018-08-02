package cn.fanrt.common.user.bean;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 23:47
 **/
public class SysUserEditInfo {

    private Long userId;

    /** 姓名 */
    private String name;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 手机号码 */
    private String phone;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
