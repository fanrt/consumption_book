package cn.fanrt.common.user.service;

import cn.fanrt.common.user.bean.SysUserEditInfo;
import cn.fanrt.common.user.domain.SysUser;
import cn.fanrt.common.user.repository.SysUserRepository;
import cn.fanrt.utils.JsonObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 23:48
 **/
@Service
@Transactional
public class SysUserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    public JsonObj saveSysUser(SysUserEditInfo editInfo) {
        SysUser sysUser = new SysUser();
        if (editInfo.getUserId() != null) {
            sysUser = this.sysUserRepository.getOne(editInfo.getUserId());
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        sysUser.setAccount(editInfo.getAccount());
        sysUser.setName(editInfo.getName());
        sysUser.setPassword(bCryptPasswordEncoder.encode(editInfo.getPassword()));
        sysUser.setPhone(editInfo.getPhone());
        this.sysUserRepository.save(sysUser);
        return new JsonObj("0", "保存成功");
    }
}
