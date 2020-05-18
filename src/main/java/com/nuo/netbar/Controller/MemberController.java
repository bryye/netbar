package com.nuo.netbar.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuo.netbar.common.AjaxResult;
import com.nuo.netbar.mapper.MemberMapper;
import com.nuo.netbar.pojo.Charging;
import com.nuo.netbar.pojo.Member;
import com.nuo.netbar.pojo.MemberExample;
import com.nuo.netbar.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Member")
@Api("会员管理")
public class MemberController {
    @Autowired
    private MemberService memberService;


    @RequestMapping("/insertSelective")
    @ApiOperation("新增")
    public AjaxResult insertSelective(@RequestBody Member record){
        MemberExample memberExample=new MemberExample();
        memberExample.setDistinct(false);
        MemberExample.Criteria criteria = memberExample.createCriteria(); //构造自定义查询条件
        criteria.andNumberEqualTo(record.getNumber());
        List list =memberService.selectByExample(memberExample);
        if (list.size()>0){
            return  AjaxResult.failed("该身份证已注册会员");
        }
        int i=0;
        try {
            i=memberService.insertSelective(record);
        } catch (Exception e) {
            e.printStackTrace();
            AjaxResult.failed(e.getMessage());
        }
        return AjaxResult.success("成功插入："+i);
    }


    @RequestMapping("/list")
    @ApiOperation("查询")
    public AjaxResult list(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        MemberExample memberExample=new MemberExample();
        memberExample.setDistinct(false);
        List<Member> list=memberService.selectByExample(memberExample);
        PageInfo<Member> pageInfo=new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @RequestMapping("/select")
    @ApiOperation("姓名搜索")
    public  AjaxResult select(@RequestParam(value = "userName") String userName){
        PageHelper.startPage(1,10);
        MemberExample memberExample=new MemberExample();
        memberExample.setDistinct(false);
        MemberExample.Criteria criteria=memberExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<Member> list=memberService.selectByExample(memberExample);
        PageInfo<Member> pageInfo=new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @RequestMapping("/money")
    @ApiOperation("充值")
    public AjaxResult money(@RequestParam(value = "id") Integer id ,@RequestParam(value = "balance") String balance){
        Member data=memberService.selectByPrimaryKey(id);
        balance=String.valueOf(Double.parseDouble(balance)+Double.parseDouble(data.getBalance()));
        data.setBalance(balance);
        //memberMapper.updateBalanceByPrimaryKeySelective(member);
        memberService.updateByPrimaryKeySelective(data);
        return AjaxResult.success("充值成功,当前余额"+balance+"元");
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    public AjaxResult update(@RequestBody Member record){
        memberService.updateByPrimaryKeySelective(record);
        return AjaxResult.success("信息修改成功");
    }

    @RequestMapping("/validate")
    @ApiOperation("修改状态")
    public AjaxResult validate(@RequestParam(value = "id") Integer id ,@RequestParam(value = "status") Integer status){
        Member data=memberService.selectByPrimaryKey(id);
        data.setStatus(status);
        memberService.updateByPrimaryKeySelective(data);

        String message;
        if (status==1){
            message="生效";
        }else message="失效";
        return  AjaxResult.success(message+"成功！");
    }
}
