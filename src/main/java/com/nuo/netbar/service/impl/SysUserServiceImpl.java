package com.nuo.netbar.service.impl;

import com.nuo.netbar.mapper.SysuserMapper;
import com.nuo.netbar.pojo.Sysuser;
import com.nuo.netbar.pojo.SysuserExample;
import com.nuo.netbar.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysuserMapper sysuserMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysuserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Sysuser record) {
        return sysuserMapper.insert(record);
    }

    @Override
    public int insertSelective(Sysuser record) {
        return sysuserMapper.insertSelective(record);
    }

    @Override
    public List<Sysuser> selectByExample(SysuserExample example) {
        return sysuserMapper.selectByExample(example);
    }

    @Override
    public Sysuser selectByPrimaryKey(Integer id) {
        return sysuserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Sysuser record, SysuserExample example) {
        return sysuserMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Sysuser record, SysuserExample example) {
        return sysuserMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Sysuser record) {
        return sysuserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Sysuser record) {
        return sysuserMapper.updateByPrimaryKey(record);
    }
}
