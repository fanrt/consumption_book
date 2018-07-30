package cn.fanrt.consumption.service;

import cn.fanrt.consumption.domain.ConsumptionEveryday;
import cn.fanrt.consumption.repository.ConsumptionEverydayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "consumptionDate");
        return this.consumptionEverydayRepository.findAll(pageable);
    }
}
