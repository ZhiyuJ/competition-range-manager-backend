package cn.edu.sdu.ise.labs.vo;

import lombok.Data;

/**
 * @author 姜治羽
 * @create 2020-04-19 17:39
 */
@Data
public class RangeVO {
    /**
     * 场地编码
     */
    private String rangeCode;

    /**
     * 场地名称
     */
    private String rangeName;

    /**
     * 位置
     */
    private String rangeLocation;

    /**
     * 状态编码
     */
    private Integer status;

    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 关闭原因
     */
    private String closeReason;

    /**
     * 备注
     */
    private String description;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;
}
