package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 姜治羽
 * @create 2020-04-19 19:04
 */
@Data
public class RangeDTO {
    /**
     * 场地编码
     */
    private String rangeCode;

    /**
     * 场地名称
     */
    private String rangeName;

    /**
     * 场地位置
     */
    private String rangeLocation;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 关闭原因
     */
    private String closeReason;

    /**
     * 备注
     */
    private String description;
}
