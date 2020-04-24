package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "场地名称不能为空")
    private String rangeName;

    /**
     * 场地位置
     */
    @NotEmpty(message = "场地位置不能为空")
    private String rangeLocation;

    /**
     * 状态
     */
    @NotEmpty(message = "状态不能为空")
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
