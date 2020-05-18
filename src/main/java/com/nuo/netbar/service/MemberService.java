package com.nuo.netbar.service;

import com.nuo.netbar.pojo.Member;
import com.nuo.netbar.pojo.MemberExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {


    int deleteByPrimaryKey(Integer id);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Member record);

}
