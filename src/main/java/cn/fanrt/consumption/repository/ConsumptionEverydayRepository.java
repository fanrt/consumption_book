package cn.fanrt.consumption.repository;

import cn.fanrt.consumption.domain.ConsumptionEveryday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-29 18:31
 **/
@Repository
@Transactional
public interface ConsumptionEverydayRepository extends JpaRepository<ConsumptionEveryday, Long> {

}
