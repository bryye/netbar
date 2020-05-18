package com.nuo.netbar.service.impl;

import com.nuo.netbar.mapper.ComputerMapper;
import com.nuo.netbar.pojo.Computer;
import com.nuo.netbar.pojo.ComputerExample;
import com.nuo.netbar.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {
   @Autowired
   private ComputerMapper computerMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return computerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Computer record) {
        return computerMapper.insert(record);
    }

    @Override
    public int insertSelective(Computer record) {
        return computerMapper.insertSelective(record);
    }

    @Override
    public List<Computer> selectByExample(ComputerExample example) {
        return computerMapper.selectByExample(example);
    }

    @Override
    public Computer selectByPrimaryKey(Integer id) {
        return computerMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Computer record, ComputerExample example) {
        return computerMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Computer record, ComputerExample example) {
        return computerMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Computer record) {
        return computerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Computer record) {
        return computerMapper.updateByPrimaryKey(record);
    }
}
