package com.nuo.netbar.service;

import com.nuo.netbar.pojo.Sysuser;
import com.nuo.netbar.pojo.SysuserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserService {


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
