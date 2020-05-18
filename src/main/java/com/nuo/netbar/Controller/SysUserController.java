package com.nuo.netbar.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuo.netbar.common.AjaxResult;
import com.nuo.netbar.pojo.Member;
import com.nuo.netbar.pojo.MemberExample;
import com.nuo.netbar.pojo.Sysuser;
import com.nuo.netbar.pojo.SysuserExample;
import com.nuo.netbar.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
@Api("管理员管理")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/insertSelective")
    @ApiOperation("新增")
    public AjaxResult insertSelective(@RequestBody Sysuser record) {
        SysuserExample sysuserExample=new SysuserExample();
        sysuserExample.setDistinct(false);
        SysuserExample.Criteria criteria=sysuserExample.createCriteria();
        criteria.andNameEqualTo(record.getName());
       List list= sysUserService.selectByExample(sysuserExample);
        if (list.size()>0){
            return  AjaxResult.failed("该管理员已存在");
        }
        sysUserService.insertSelective(record);
        return AjaxResult.success("新增成功");
    }

    @RequestMapping("/list")
    @ApiOperation("查询")
    public AjaxResult list(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        SysuserExample sysuserExample=new SysuserExample();
        sysuserExample.setDistinct(false);
        List list= sysUserService.selectByExample(sysuserExample);
        PageInfo<Sysuser> pageInfo=new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }
    @RequestMapping("/select")
    @ApiOperation("姓名搜索")
    public  AjaxResult select(@RequestParam(value = "userName") String userName) {
        PageHelper.startPage(1, 3);
        SysuserExample sysuserExample=new SysuserExample();
        sysuserExample.setDistinct(false);
        SysuserExample.Criteria criteria=sysuserExample.createCriteria();
        criteria.andNameEqualTo(userName);
        List list= sysUserService.selectByExample(sysuserExample);
        PageInfo<Member> pageInfo=new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    public AjaxResult update(@RequestBody Sysuser record){
        sysUserService.updateByPrimaryKey(record);
        return AjaxResult.success("信息修改成功");
    }

    @RequestMapping("/validate")
    @ApiOperation("禁用、启用")
    public AjaxResult validate(@RequestParam(value = "id") Integer id ,@RequestParam(value = "status") Integer status){
        Sysuser sysuser=sysUserService.selectByPrimaryKey(id);
        sysuser.setStatus(status);
        sysUserService.updateByPrimaryKey(sysuser);
        String message;
        if (status==1)
            message="生效";
        else message="失效";
        return  AjaxResult.success(message+"成功！");
    }
}
