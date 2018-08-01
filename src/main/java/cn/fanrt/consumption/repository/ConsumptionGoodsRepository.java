package cn.fanrt.consumption.repository;

import cn.fanrt.consumption.domain.ConsumptionGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-31 21:12
 **/
@Repository
@Transactional
public interface ConsumptionGoodsRepository extends JpaRepository<ConsumptionGoods, Long> {

    List<ConsumptionGoods> findByConsumptionId(Long consumptionId);
}
