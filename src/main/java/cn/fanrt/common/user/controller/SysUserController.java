package cn.fanrt.common.user.controller;

import cn.fanrt.common.user.bean.SysUserEditInfo;
import cn.fanrt.common.user.service.SysUserService;
import cn.fanrt.utils.JsonObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-08-02 23:46
 **/
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 保存用户
     * @return
     */
    @RequestMapping("/saveSysUser.action")
    @ResponseBody
    public JsonObj saveSysUser(SysUserEditInfo info) {
        JsonObj jsonObj = this.sysUserService.saveSysUser(info);
        return jsonObj;
    }
}
