package cn.fanrt.consumption.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-29 16:29
 **/
@Entity
@Table(name="CONSUMPTION_GOODS" , schema="CONSUMPTION_BOOK" )
@SequenceGenerator(name="CONSUMPTION_GOODS_ID" , schema="CONSUMPTION_BOOK" , sequenceName="CONSUMPTION_GOODS_ID", allocationSize=1)
public class ConsumptionGoods {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONSUMPTION_EVERYDAY_ID")
    @Column(name="GOODS_ID", unique=true, nullable=false, precision=18)
    private Long goodsId;

    /** 商品名称 */
    @Column(name = "GOODS_NAME", length = 40)
    private String goodsName;

    /** 价钱 */
    @Column(name = "PRICE", precision=18)
    private Double price;

    /** 每日消费ID */
    @Column(name = "CONSUMPTION_ID", precision=18)
    private Long consumptionId;

    /** 创建时间 */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Long consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
