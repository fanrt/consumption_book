package cn.fanrt.consumption.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Author: fanrt
 * @Description:
 * @create: 2018-07-29 16:22
 **/
public class ConsumptionEverydayEditInfo {

    private Long consumptionId;

    /** 消费时间 */
    @DateTimeFormat(pattern="yyyy年MM月dd日")
    private Date consumptionDate;

    /** 价钱 */
    private Double price;

    /** 创建时间 */
    private Date createTime;

    /** 备注 */
    private String remark;

    private List<ConsumptionGoodsEditInfo> consumptionGoodsEditInfoList;

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

    public List<ConsumptionGoodsEditInfo> getConsumptionGoodsEditInfoList() {
        return consumptionGoodsEditInfoList;
    }

    public void setConsumptionGoodsEditInfoList(List<ConsumptionGoodsEditInfo> consumptionGoodsEditInfoList) {
        this.consumptionGoodsEditInfoList = consumptionGoodsEditInfoList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
