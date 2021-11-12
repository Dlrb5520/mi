package com.mi.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Goods
 * @Author yangli
 * @Date 2021/10/25 13:48
 * @Description:
 */
@Data
@TableName("mi_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -4352868542697894001L;

    /**
     * id
     */
    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;


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
