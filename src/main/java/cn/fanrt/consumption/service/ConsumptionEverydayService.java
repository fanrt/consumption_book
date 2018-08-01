package cn.fanrt.consumption.service;

import cn.fanrt.consumption.bean.ConsumptionEverydayEditInfo;
import cn.fanrt.consumption.domain.ConsumptionEveryday;
import cn.fanrt.consumption.repository.ConsumptionEverydayRepository;
import cn.fanrt.utils.JsonObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-30 23:57
 **/
@Service
@Transactional
public class ConsumptionEverydayService {

    @Autowired
    private ConsumptionEverydayRepository consumptionEverydayRepository;

    public Page<ConsumptionEveryday> findConsumptionEverydayPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page - 1, size);
        return this.consumptionEverydayRepository.findAll(pageable);
    }

    public List<ConsumptionEveryday> findConsumptionEverydayList(Integer page, Integer size) {
        return this.consumptionEverydayRepository.findAll();
    }

    public JsonObj saveConsumptionEveryday(ConsumptionEverydayEditInfo editInfo) {

        return new JsonObj("0", "保存成功");
    }
}
