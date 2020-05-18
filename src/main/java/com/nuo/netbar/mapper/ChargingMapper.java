package com.nuo.netbar.mapper;

import com.nuo.netbar.pojo.Charging;
import com.nuo.netbar.pojo.ChargingExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ChargingMapper {
    long countByExample(ChargingExample example);

    int deleteByExample(ChargingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Charging record);

    int insertSelective(Charging record);

    List<Charging> selectByExample(ChargingExample example);

    Charging selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Charging record, @Param("example") ChargingExample example);

    int updateByExample(@Param("record") Charging record, @Param("example") ChargingExample example);

    int updateByPrimaryKeySelective(Charging record);

    int updateByPrimaryKey(Charging record);
}