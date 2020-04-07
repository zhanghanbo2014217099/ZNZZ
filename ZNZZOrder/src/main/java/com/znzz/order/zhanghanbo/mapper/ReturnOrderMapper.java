package com.znzz.order.zhanghanbo.mapper;

import com.znzz.order.zhanghanbo.entities.ReturnOrder;
import com.znzz.order.zhanghanbo.entities.ReturnOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnOrderMapper {
    long countByExample(ReturnOrderExample example);

    int deleteByExample(ReturnOrderExample example);

    int deleteByPrimaryKey(String rorderId);

    int insert(ReturnOrder record);

    int insertSelective(ReturnOrder record);

    List<ReturnOrder> selectByExample(ReturnOrderExample example);

    ReturnOrder selectByPrimaryKey(String rorderId);

    int updateByExampleSelective(@Param("record") ReturnOrder record, @Param("example") ReturnOrderExample example);

    int updateByExample(@Param("record") ReturnOrder record, @Param("example") ReturnOrderExample example);

    int updateByPrimaryKeySelective(ReturnOrder record);

    int updateByPrimaryKey(ReturnOrder record);
}