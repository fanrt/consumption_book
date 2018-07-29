package cn.fanrt.consumption.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-29 16:22
 **/
@Entity
@Table(name="CONSUMPTION_EVERYDAY" , schema="CONSUMPTION_BOOK" )
@SequenceGenerator(name="CONSUMPTION_EVERYDAY_ID" , schema="CONSUMPTION_BOOK" , sequenceName="CONSUMPTION_EVERYDAY_ID", allocationSize=1)
public class ConsumptionEveryday {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONSUMPTION_EVERYDAY_ID")
    @Column(name="CONSUMPTION_ID", unique=true, nullable=false, precision=18)
    private Long consumptionId;

    /** 消费时间 */
    @Column(name = "CONSUMPTION_DATE")
    private Date consumptionDate;

    /** 价钱 */
    @Column(name = "PRICE", precision=18)
    private Double price;

    /** 创建时间 */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    public Long getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Long consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Date getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Date consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
