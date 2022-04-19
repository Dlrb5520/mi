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
@TableName("mi_orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    @TableField("ORDER_NUM")
    private String orderNum;

    /**
     * 商品id
     */
    @TableField("GOODS_ID")
    private Integer goodsId;

    /**
     * 商品名称
     */
    @TableField("GOODS_NAME")
    private String goodsName;


}
