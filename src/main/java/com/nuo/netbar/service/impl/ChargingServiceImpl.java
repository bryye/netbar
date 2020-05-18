package com.nuo.netbar.service.impl;


import com.github.pagehelper.PageHelper;
import com.nuo.netbar.mapper.ChargingMapper;
import com.nuo.netbar.mapper.MemberMapper;
import com.nuo.netbar.mapper.SysuserMapper;
import com.nuo.netbar.pojo.*;
import com.nuo.netbar.service.ChargingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargingServiceImpl implements ChargingService {
    @Autowired
    private  ChargingMapper chargingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public List<Charge> getChargingList() {
        List<Charge> dataList=new ArrayList<>();
        ChargingExample chargingExample=new ChargingExample();
        chargingExample.setDistinct(false);
         List<Charging>  list= chargingMapper.selectByExample(chargingExample);

        for (Charging charge:list
             ) {
            Member user=memberMapper.selectByPrimaryKey(charge.getuId());
            Charge data=new Charge();
            data.setChargeId(charge.getId());
            data.setDowntime(charge.getDowntime());
            data.setMoney(charge.getMoney());
            data.setName(user.getName());
            data.setNo(charge.getNo());
            data.setStatus(charge.getStatus());
            data.setStarttime(charge.getStarttime());
            data.setcId(charge.getcId());
            dataList.add(data);

        }
        return  dataList;

    }

    @Override
    public Charging selectByPrimaryKey(Integer id) {
        return chargingMapper.selectByPrimaryKey(id);
    }

    @Override
    public Charge getCharging(Integer id) {
        Charging charge=chargingMapper.selectByPrimaryKey(id);
        Member user=memberMapper.selectByPrimaryKey(charge.getuId());
        Charge data=new Charge();
        data.setChargeId(charge.getId());
        data.setDowntime(charge.getDowntime());
        data.setMoney(charge.getMoney());
        data.setName(user.getName());
        data.setNo(charge.getNo());
        data.setStatus(charge.getStatus());
        data.setStarttime(charge.getStarttime());
        data.setcId(charge.getcId());
        return data;
    }

    @Override
    public int updateByPrimaryKeySelective(Charging record) {
        return chargingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Charging> selectByExample(ChargingExample example) {
        return chargingMapper.selectByExample(example);
    }

    @Override
    public List<Charge> getChargingByNo(Integer no) {
        List<Charge> dataList=new ArrayList<>();
        ChargingExample chargingExample=new ChargingExample();
        chargingExample.setDistinct(false);
        ChargingExample.Criteria criteria=chargingExample.createCriteria();
        criteria.andNoEqualTo(no);
        List<Charging>  list= chargingMapper.selectByExample(chargingExample);

        for (Charging charge:list
        ) {
            Member user=memberMapper.selectByPrimaryKey(charge.getuId());
            Charge data=new Charge();
            data.setChargeId(charge.getId());
            data.setDowntime(charge.getDowntime());
            data.setMoney(charge.getMoney());
            data.setName(user.getName());
            data.setNo(charge.getNo());
            data.setStatus(charge.getStatus());
            data.setStarttime(charge.getStarttime());
            data.setcId(charge.getcId());
            dataList.add(data);

        }
        return  dataList;
    }
}
