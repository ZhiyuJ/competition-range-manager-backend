package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 姜治羽
 * @create 2020-04-19 18:31
 */
@Data
public class RangeQueryDTO {
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
     * 页码
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer pageSize;
}
