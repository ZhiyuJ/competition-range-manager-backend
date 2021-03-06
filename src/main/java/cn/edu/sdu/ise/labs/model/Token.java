package cn.edu.sdu.ise.labs.model;

import lombok.Data;

import java.util.Date;

/**
 * 令牌实体类
 *
 * @author 李洪文
 * @date 2019/12/3 10:38
 */
@Data
public class Token {
    /**
     * 令牌token
     */
    private String accessToken;

    /**
     * 租户代码
     */
    private String tenantCode;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 教师编码
     */
    private String teacherCode;

    /**
     * 上次动作
     */
    private Date lastAction;

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
     * 场地状态
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
