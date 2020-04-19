package cn.edu.sdu.ise.labs.dto;

import lombok.Data;

/**
 * @author 姜治羽
 * @create 2020-04-19 18:31
 */
@Data
public class RangeQueryDTO {
    /**
     * 部门名称，模糊匹配
     */
    private String departmentName;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页记录数
     */
    private Integer pageSize;
}
