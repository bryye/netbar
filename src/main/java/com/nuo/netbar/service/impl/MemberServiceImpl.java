package com.nuo.netbar.service.impl;

import com.nuo.netbar.mapper.MemberMapper;
import com.nuo.netbar.pojo.Member;
import com.nuo.netbar.pojo.MemberExample;
import com.nuo.netbar.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Member record) {
        return memberMapper.insertSelective(record);
    }

    @Override
    public List<Member> selectByExample(MemberExample example) {
        return memberMapper.selectByExample(example);
    }

    @Override
    public Member selectByPrimaryKey(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Member record) {

        return memberMapper.updateByPrimaryKey(record);
    }
}
