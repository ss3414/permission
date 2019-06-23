package com.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("shiro_filter")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Filter extends Model<Filter> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer filterSort;

    private String filterUrl;

    private String filterPermission;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}