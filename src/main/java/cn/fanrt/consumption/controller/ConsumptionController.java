package cn.fanrt.consumption.controller;

import cn.fanrt.consumption.repository.ConsumptionEverydayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-29 15:44
 **/
@Controller
@RequestMapping("/consumption")
public class ConsumptionController {

    @Autowired
    private ConsumptionEverydayRepository consumptionEverydayRepository;

    /**
     * 进入每日消费列表页面
     * @param
     * @return String
     */
    @RequestMapping("/consumptionList.do")
    public String controlModelList() {
        return "consumption/consumptionList";
    }

    /**
     * 获取每日消费数据
     * @return
     */
    @RequestMapping("/consumptionPageData.jo")
    @ResponseBody
    public Map<String, Object> caseApplyListData() {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    /**
     * 进入每日消费编辑页面
     * @param
     * @return String
     */
    @RequestMapping("/consumptionEdit.do")
    public String controlModelEdit(Long controlModelId, Model model) {
        if (controlModelId != null) {
//            ControlModel controlModel = this.controlModelService.selectAObject(ControlModel.class, controlModelId);
//            model.addAttribute("controlModel", controlModel);
        }
        return "consumption/consumptionEdit";
    }
}
