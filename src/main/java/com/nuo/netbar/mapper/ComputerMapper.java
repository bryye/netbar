package com.nuo.netbar.mapper;

import com.nuo.netbar.pojo.Computer;
import com.nuo.netbar.pojo.ComputerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ComputerMapper {
    long countByExample(ComputerExample example);

    int deleteByExample(ComputerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Computer record);

    int insertSelective(Computer record);

    List<Computer> selectByExample(ComputerExample example);

    Computer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Computer record, @Param("example") ComputerExample example);

    int updateByExample(@Param("record") Computer record, @Param("example") ComputerExample example);

    int updateByPrimaryKeySelective(Computer record);

    int updateByPrimaryKey(Computer record);

    List selectType();

    int downByPrimaryKey(Computer computer);
}