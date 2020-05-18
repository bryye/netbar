package com.nuo.netbar.service;

import com.nuo.netbar.pojo.Computer;
import com.nuo.netbar.pojo.ComputerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComputerService {

    int deleteByPrimaryKey(Integer id);

    int insert(Computer record);

    int insertSelective(Computer record);

    List<Computer> selectByExample(ComputerExample example);

    Computer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Computer record, @Param("example") ComputerExample example);

    int updateByExample(@Param("record") Computer record, @Param("example") ComputerExample example);

    int updateByPrimaryKeySelective(Computer record);

    int updateByPrimaryKey(Computer record);
}
