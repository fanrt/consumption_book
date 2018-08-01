package cn.fanrt.consumption.service;

import cn.fanrt.consumption.bean.ConsumptionGoodsEditInfo;
import cn.fanrt.consumption.domain.ConsumptionEveryday;
import cn.fanrt.utils.JsonObj;
import cn.fanrt.utils.Tools;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-31 21:15
 **/
@Service
@Transactional
public class ConsumptionGoodsService {

    public JsonObj saveConsuptionGoodsList(List<ConsumptionGoodsEditInfo> editInfoList, ConsumptionEveryday consumptionEveryday) {
        if (Tools.isNotEmptyList(editInfoList)) {
            for (ConsumptionGoodsEditInfo editInfo: editInfoList) {

            }
        }
        return new JsonObj("0", "保存成功");
    }
}
