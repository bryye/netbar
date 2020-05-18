package com.nuo.netbar.mapper;

import com.nuo.netbar.pojo.Sysuser;
import com.nuo.netbar.pojo.SysuserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysuserMapper {
    long countByExample(SysuserExample example);

    int deleteByExample(SysuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sysuser record);

    int insertSelective(Sysuser record);

    List<Sysuser> selectByExample(SysuserExample example);

    Sysuser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sysuser record, @Param("example") SysuserExample example);

    int updateByExample(@Param("record") Sysuser record, @Param("example") SysuserExample example);

    int updateByPrimaryKeySelective(Sysuser record);

    int updateByPrimaryKey(Sysuser record);
}