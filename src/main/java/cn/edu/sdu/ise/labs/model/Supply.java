package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jzhy36
 * @Description: 供应实体类
 */
@Data
public class Supply {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 供应标题
     */
    private String title;

    /**
     * 对应品类
     */
    private Integer categoryId;

    /**
     * 省级编号
     */
    private String province;

    /**
     * 市级编号
     */
    private String city;

    /**
     * 镇/区编号
     */
    private String district;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 供应属性
     */
    private String property;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 供货日期类型
     */
    private Integer sellDateType;

    /**
     * 供货日期
     */
    private Date sellDate;

    /**
     * 最低价规格名称
     */
    private String specificationName;

    /**
     * 最低价单位
     */
    private Integer unit;

    /**
     * 最低价价格
     */
    private BigDecimal price;

    /**
     * 最低价起批量
     */
    private Integer startingValue;

    /**
     * 最低价运费设置
     */
    private Integer transportType;

    /**
     * 点击次数
     */
    private Integer clicks;

    /**
     * 成交额
     */
    private BigDecimal amount;

    /**
     * 创建人
     */
    private Integer createdBy;

    /**
     * 更新人
     */
    private Integer updatedBy;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 删除标记
     */
    private Boolean deleted;

}