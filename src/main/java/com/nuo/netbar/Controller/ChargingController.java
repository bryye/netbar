package com.nuo.netbar.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nuo.netbar.common.AjaxResult;
import com.nuo.netbar.mapper.ComputerMapper;
import com.nuo.netbar.pojo.Charge;
import com.nuo.netbar.pojo.Charging;
import com.nuo.netbar.pojo.Computer;
import com.nuo.netbar.service.ChargingService;
import com.nuo.netbar.service.ComputerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "消费信息")
@RequestMapping("/Charging")
public class ChargingController {
    @Autowired
    private ChargingService chargingService;
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ComputerMapper computerMapper;

    @RequestMapping("/list")
    public AjaxResult list(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Charge> list=chargingService.getChargingList();
        PageInfo<Charge> pageInfo=new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }

    @RequestMapping("/downMessage")
    public AjaxResult downMessage(@RequestParam(value = "id") Integer id){
        Charge charge=chargingService.getCharging(id);
        if (charge.getStatus()==0) return AjaxResult.failed("该机器已经下机！！！");
        Date start=charge.getStarttime();
        Date now=new Date();
        Long between=now.getTime()-start.getTime();
        Double time=(double)(between.doubleValue()/(1000*60*60));
        System.out.println(time);
        Computer computer=computerService.selectByPrimaryKey(charge.getcId());
        BigDecimal money=BigDecimal.valueOf(computer.getPrice().doubleValue()* (time));
        System.out.println(money);
        charge.setMoney(money.setScale(1,BigDecimal.ROUND_HALF_UP));
        return  AjaxResult.success(charge);
    }

    @RequestMapping("/down")
    public AjaxResult down(@RequestParam(value = "id") Integer id){
        Charging charge=chargingService.selectByPrimaryKey(id);
        Date start=charge.getStarttime();
        Date now=new Date();
        Long between=now.getTime()-start.getTime();
        Double time=(double)(between.doubleValue()/(1000*60*60));
        Computer computer=computerService.selectByPrimaryKey(charge.getcId());
        BigDecimal money=BigDecimal.valueOf(computer.getPrice().doubleValue()* (time));
        charge.setMoney(money.setScale(1,BigDecimal.ROUND_HALF_UP));
        charge.setStatus(0);
        charge.setDowntime(now);
        chargingService.updateByPrimaryKeySelective(charge);
        computer.setStatus("结束");
        computerMapper.downByPrimaryKey(computer);
        return  AjaxResult.success(charge);
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    public AjaxResult update(@RequestBody Charge record){
        Charging charging=chargingService.selectByPrimaryKey(record.getChargeId());
        charging.setStatus(record.getStatus());
        charging.setMoney(record.getMoney());
        charging.setDowntime(record.getDowntime());
        charging.setStarttime(record.getStarttime());
        chargingService.updateByPrimaryKeySelective(charging);
        return AjaxResult.success("信息修改成功");
    }


    @RequestMapping("/select")
    @ApiOperation("查询")
    public AjaxResult select(@RequestParam("no")Integer no ,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List list=chargingService.getChargingByNo(no);
        PageInfo<Charge> pageInfo=new PageInfo<>(list);
        return AjaxResult.success(pageInfo);
    }
}
