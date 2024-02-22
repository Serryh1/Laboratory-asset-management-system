package com.acmlab.file.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ctq
 * @since 2021-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Device extends Model<Device> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;
    /**
     * 设备id
     */
    private String classify;
    /**
     * 分类
     */
    private String name;
    /**
     * 设备名称
     */
    private Integer quantity;
    /**
     * 设备数量
     */


    @Override
    protected Serializable pkVal() {
        return this.sid;
    }

}
