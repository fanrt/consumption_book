package cn.fanrt.consumption.controller;

import cn.fanrt.consumption.bean.ConsumptionEverydayEditInfo;
import cn.fanrt.consumption.domain.ConsumptionEveryday;
import cn.fanrt.consumption.domain.ConsumptionGoods;
import cn.fanrt.consumption.repository.ConsumptionEverydayRepository;
import cn.fanrt.consumption.repository.ConsumptionGoodsRepository;
import cn.fanrt.consumption.service.ConsumptionEverydayService;
import cn.fanrt.utils.JsonObj;
import com.alibaba.fastjson.JSON;
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
    private ConsumptionEverydayService consumptionEverydayService;

    @Autowired
    private ConsumptionGoodsRepository consumptionGoodsRepository;

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
    public Map<String, Object> caseApplyListData(Integer pageNumber, Integer pageSize) {
        Map<String, Object> result = new HashMap<String, Object>();
        Page page = this.consumptionEverydayService.findConsumptionEverydayPage(pageNumber, pageSize);
//        List<ConsumptionEveryday> consumptionEverydayList = this.consumptionEverydayService.findConsumptionEverydayList(pageNumber, pageSize);
        result.put("rows", page.getContent());
        result.put("total", 1);
        return result;
    }

    /**
     * 进入每日消费编辑页面
     * @param
     * @return String
     */
    @RequestMapping("/consumptionEdit.do")
    public String controlModelEdit(Long consumptionId, Model model) {
        if (consumptionId != null) {
            ConsumptionEveryday consumptionEveryday = this.consumptionEverydayRepository.findById(consumptionId).get();
            List<ConsumptionGoods> consumptionGoodsList = this.consumptionGoodsRepository.findByConsumptionId(consumptionId);
            model.addAttribute("consumptionEveryday", consumptionEveryday);
            model.addAttribute("consumptionGoodsList", JSON.toJSONString(consumptionGoodsList));
        }
        return "consumption/consumptionEdit";
    }

    /**
     * 保存每日消费
     * @return
     */
    @RequestMapping("/consumptionSave.jo")
    @ResponseBody
    public JsonObj controleModelSave(ConsumptionEverydayEditInfo info) {
        JsonObj jsonObj = this.consumptionEverydayService.saveConsumptionEveryday(info);
        return jsonObj;
    }
}
