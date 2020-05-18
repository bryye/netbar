package com.nuo.netbar.Controller;

import com.github.pagehelper.PageHelper;
import com.nuo.netbar.common.AjaxResult;
import com.nuo.netbar.mapper.ComputerMapper;
import com.nuo.netbar.pojo.Computer;
import com.nuo.netbar.pojo.ComputerExample;
import com.nuo.netbar.pojo.Sysuser;
import com.nuo.netbar.service.ComputerService;
import com.nuo.netbar.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Api(value = "电脑信息")
@RequestMapping("/Computer")
public class ComputerController  {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ComputerMapper computerMapper;
    @RequestMapping("/list")
    @ApiOperation("查询")
    public AjaxResult list(){
        ComputerExample computerExample=new ComputerExample();
        computerExample.setDistinct(false);
        HashMap<String,List<Computer>> hashMap=new HashMap();

        List<String> list=computerMapper.selectType();
        System.out.println(list);
        for (String one:list) {
            ComputerExample.Criteria criteria=computerExample.createCriteria();
            criteria.andTypeEqualTo(one);
            List<Computer> list1=computerService.selectByExample(computerExample);
            hashMap.put(one,list1);
        }
        return AjaxResult.success(hashMap);
    }

    @RequestMapping("/insertSelective")
    @ApiOperation("新增")
    public AjaxResult insertSelective(@RequestBody Computer computer){
        ComputerExample computerExample=new ComputerExample();
        computerExample.setDistinct(false);
        ComputerExample.Criteria criteria=computerExample.createCriteria();
        criteria.andNoEqualTo(computer.getNo());
        List list=computerService.selectByExample(computerExample);
        if (list.size()>0){
            return  AjaxResult.failed("该机号已存在");
        }
        computerService.insertSelective(computer);
        return AjaxResult.success("新增成功");
    }

    @RequestMapping("/select")
    @ApiOperation("搜索")
    public  AjaxResult select(@RequestParam(value = "no") Integer no){
        ComputerExample computerExample=new ComputerExample();
        computerExample.setDistinct(false);
        ComputerExample.Criteria criteria=computerExample.createCriteria();
        criteria.andNoEqualTo(no);
        List list=computerService.selectByExample(computerExample);
        return AjaxResult.success(list.get(0));
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    public AjaxResult update(@RequestBody Computer record){
        computerService.updateByPrimaryKey(record);
        return AjaxResult.success("信息修改成功");
    }

    @RequestMapping("/validate")
    @ApiOperation("修改状态")
    public AjaxResult validate(@RequestParam(value = "id") Integer id ,@RequestParam(value = "status") String status){
        Computer computer=computerService.selectByPrimaryKey(id);
        computer.setStatus(status);
        computerService.updateByPrimaryKey(computer);
        return  AjaxResult.success("更改成功！");
    }
}
