package com.mi.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangli
 * @since 2021-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mi_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 库存
     */
    @TableField("STOCK")
    private String stock;


}
