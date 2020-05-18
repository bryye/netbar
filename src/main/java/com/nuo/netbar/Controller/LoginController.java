package com.nuo.netbar.Controller;

import com.nuo.netbar.common.AjaxResult;
import com.nuo.netbar.pojo.*;
import com.nuo.netbar.service.ChargingService;
import com.nuo.netbar.service.ComputerService;
import com.nuo.netbar.service.MemberService;
import com.nuo.netbar.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Login")
@Api("登陆")
public class LoginController {
    @Resource
    private MemberService memberService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private ChargingService chargingService;
    @Resource
    private ComputerService computerService;

    @RequestMapping("/admin")
    @ApiOperation("管理员登陆")
    public AjaxResult admin(@RequestParam("username") String userName, @RequestParam("password") String password, HttpSession session){
        SysuserExample sysuserExample=new SysuserExample();
        sysuserExample.setDistinct(false);
        SysuserExample.Criteria criteria=sysuserExample.createCriteria();
        criteria.andNameEqualTo(userName);
        List list= sysUserService.selectByExample(sysuserExample);
        if (list.size()<=0) return AjaxResult.failed("该用户不存在");
        Sysuser admin= (Sysuser) list.get(0);
        if (password.trim().equals(admin.getPassword())){
            if (admin.getStatus()>0){
                session.setAttribute("adminId", admin.getId());
                session.setAttribute("adminName", admin.getName());
                session.setAttribute("adminTel", admin.getPhone());
                return AjaxResult.success(admin);
            }
        }
        return AjaxResult.failed("用户名或者密码错误或账号以失效");
    }

    @RequestMapping("/user")
    @ApiOperation("用户登陆")
    public AjaxResult user(@RequestParam("number") String number, @RequestParam("password") String password, HttpSession session){
        MemberExample memberExample=new MemberExample();
        memberExample.setDistinct(false);
        MemberExample.Criteria criteria = memberExample.createCriteria(); //构造自定义查询条件
        criteria.andNumberEqualTo(number);
        List list =memberService.selectByExample(memberExample);
        if (list.size()<=0) return AjaxResult.failed("该用户不存在");
        Member member= (Member) list.get(0);
        if (password.trim().equals(member.getPassword())){
            if (member.getStatus()>0){
                ChargingExample chargingExample=new ChargingExample();
                chargingExample.setDistinct(false);
                ChargingExample.Criteria criteria1=chargingExample.createCriteria();
                criteria1.andUIdEqualTo(member.getId());
                criteria1.andStatusEqualTo(1);
                List<Charging> list1=chargingService.selectByExample(chargingExample);
                Map<String,Object> res = new HashMap<>();
                res.put("name",member.getName());
                res.put("balance",member.getBalance());
                res.put("grade",member.getGarde());
                res.put("id",member.getId());
                if (list1.size()>0){
                    Charging charging=list1.get(0);
                    Date start=charging.getStarttime();
                    Date now=new Date();
                    Long between=now.getTime()-start.getTime();
                    Double time=(double)(between.doubleValue()/(1000*60*60));
                    Computer computer=computerService.selectByPrimaryKey(charging.getcId());
                    BigDecimal money=BigDecimal.valueOf(computer.getPrice().doubleValue()* (time));
                    res.put("computer",charging.getNo());
                    res.put("startTime",start);
                    res.put("money",money.setScale(1,BigDecimal.ROUND_HALF_UP));
                    session.setAttribute("ComputerNo", charging.getNo());
                }
                session.setAttribute("userId", member.getId());
                session.setAttribute("Name", member.getName());

                return AjaxResult.success(res);

            }
        }
        return AjaxResult.failed("用户名或者密码错误或账号以失效");
    }

    @RequestMapping("/refresh")
    @ApiOperation("更新信息")
    public AjaxResult refresh(@RequestParam("number") String number){
        MemberExample memberExample=new MemberExample();
        memberExample.setDistinct(false);
        MemberExample.Criteria criteria = memberExample.createCriteria(); //构造自定义查询条件
        criteria.andNumberEqualTo(number);
        List list =memberService.selectByExample(memberExample);
        if (list.size()<=0) return AjaxResult.failed("该用户不存在");
        Member member= (Member) list.get(0);
            if (member.getStatus()>0){
                ChargingExample chargingExample=new ChargingExample();
                chargingExample.setDistinct(false);
                ChargingExample.Criteria criteria1=chargingExample.createCriteria();
                criteria1.andUIdEqualTo(member.getId());
                criteria1.andStatusEqualTo(1);
                List<Charging> list1=chargingService.selectByExample(chargingExample);
                Charging charging=list1.get(0);
                Map<String,Object> res = new HashMap<>();
                res.put("name",member.getName());
                res.put("balance",member.getBalance());
                res.put("grade",member.getGarde());
                res.put("id",member.getId());
                if (list1.size()>0);{
                    Date start=charging.getStarttime();
                    Date now=new Date();
                    Long between=now.getTime()-start.getTime();
                    Double time=(double)(between.doubleValue()/(1000*60*60));
                    Computer computer=computerService.selectByPrimaryKey(charging.getcId());
                    BigDecimal money=BigDecimal.valueOf(computer.getPrice().doubleValue()* (time));
                    res.put("computer",charging.getNo());
                    res.put("startTime",start);
                    res.put("money",money.setScale(1,BigDecimal.ROUND_HALF_UP));
                }
                return AjaxResult.success(res);
            }

        return AjaxResult.failed("用户名或者密码错误或账号以失效");
    }
}
