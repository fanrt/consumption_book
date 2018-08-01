package cn.fanrt.consumption.bean;

import java.util.Date;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-29 16:29
 **/
public class ConsumptionGoodsEditInfo {

    private Long goodsId;

    /** 商品名称 */
    private String goodsName;

    /** 价钱 */
    private Double price;

    /** 每日消费ID */
    private Long consumptionId;

    /** 创建时间 */
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
