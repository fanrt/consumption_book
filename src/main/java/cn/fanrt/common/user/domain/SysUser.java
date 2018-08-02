package cn.fanrt.common.user.domain;

import javax.persistence.*;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 14:23
 **/
@Entity
@Table(name="SYS_USER" , schema="PUBLIC_SYS" )
@SequenceGenerator(name="SYS_USER_ID" , schema="PUBLIC_SYS" , sequenceName="SYS_USER_ID", allocationSize=1)
public class SysUser {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_USER_ID")
    @Column(name="USER_ID", unique=true, nullable=false, precision=18)
    private Long userId;

    /** 姓名 */
    @Column(name = "NAME", length = 20)
    private String name;

    /** 账号 */
    @Column(name = "ACCOUNT", length = 20)
    private String account;

    /** 密码 */
    @Column(name = "PASSWORD", length = 40)
    private String password;

    /** 手机号码 */
    @Column(name = "PHONE", length = 20)
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
