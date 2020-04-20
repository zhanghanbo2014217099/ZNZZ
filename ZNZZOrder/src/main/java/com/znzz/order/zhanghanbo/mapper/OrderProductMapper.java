package com.znzz.order.zhanghanbo.mapper;


import java.util.List;

import com.znzz.order.zhanghanbo.entities.OrderProduct;
import com.znzz.order.zhanghanbo.entities.OrderProductExample;
import org.apache.ibatis.annotations.Param;

public interface OrderProductMapper {
    long countByExample(OrderProductExample example);

    int deleteByExample(OrderProductExample example);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    List<OrderProduct> selectByExample(OrderProductExample example);

    int updateByExampleSelective(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);

    int updateByExample(@Param("record") OrderProduct record, @Param("example") OrderProductExample example);
}