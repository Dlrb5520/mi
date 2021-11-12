package com.mi.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ORDER
 * @Author yangli
 * @Date 2021/10/25 11:57
 * @Description:
 */
@Data
@TableName("mi_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -4352868070797894001L;

    /**
     * id
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;

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
    private Integer goodsName;

}
