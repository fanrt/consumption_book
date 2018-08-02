package cn.fanrt.common.user.repository;

import cn.fanrt.common.user.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 14:28
 **/
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    public SysUser findByAccount(String account);
}
