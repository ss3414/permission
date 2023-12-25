package com.module.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("shiro_permission")
public class Permission extends Model<Permission> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private String permissionName;

    private String permissionUrl;

    private String permissionPerm;

    @TableField(exist = false)
    private List<Object> permissionList; /* 子级权限 */

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
