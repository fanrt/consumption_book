package cn.fanrt.consumption.service;

import cn.fanrt.consumption.bean.ConsumptionGoodsEditInfo;
import cn.fanrt.consumption.domain.ConsumptionEveryday;
import cn.fanrt.consumption.domain.ConsumptionGoods;
import cn.fanrt.consumption.repository.ConsumptionGoodsRepository;
import cn.fanrt.utils.JsonObj;
import cn.fanrt.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-31 21:15
 **/
@Service
@Transactional
public class ConsumptionGoodsService {

    @Autowired
    private ConsumptionGoodsRepository consumptionGoodsRepository;

    public JsonObj saveConsuptionGoodsList(List<ConsumptionGoodsEditInfo> editInfoList, ConsumptionEveryday consumptionEveryday) {
        List<ConsumptionGoods> consumptionGoodsList = this.consumptionGoodsRepository.findByConsumptionId(consumptionEveryday.getConsumptionId());
        if (Tools.isNotEmptyList(editInfoList)) {
            for (ConsumptionGoodsEditInfo editInfo: editInfoList) {
                if (editInfo.getGoodsId() == null) {
                    ConsumptionGoods consumptionGoods = new ConsumptionGoods();
                    consumptionGoods.setConsumptionId(consumptionEveryday.getConsumptionId());
                    consumptionGoods.setCreateTime(new Date());
                    consumptionGoods.setGoodsName(editInfo.getGoodsName());
                    consumptionGoods.setPrice(editInfo.getPrice());
                    consumptionGoods.setGoodsType(editInfo.getGoodsType());
                    this.consumptionGoodsRepository.save(consumptionGoods);
                    continue;
                }
                Iterator<ConsumptionGoods> iterator = consumptionGoodsList.iterator();
                while(iterator.hasNext()) {
                    ConsumptionGoods consumptionGoods = iterator.next();
                    if (consumptionGoods.getGoodsId().equals(editInfo.getGoodsId())) {
                        consumptionGoods.setGoodsName(editInfo.getGoodsName());
                        consumptionGoods.setPrice(editInfo.getPrice());
                        consumptionGoods.setGoodsType(editInfo.getGoodsType());
                        this.consumptionGoodsRepository.save(consumptionGoods);
                        iterator.remove();
                    }
                }
            }
        }
        this.consumptionGoodsRepository.deleteAll(consumptionGoodsList);
        return new JsonObj("0", "保存成功");
    }
}
