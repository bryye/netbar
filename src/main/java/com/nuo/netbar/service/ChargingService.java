package com.nuo.netbar.service;


import com.nuo.netbar.pojo.Charge;
import com.nuo.netbar.pojo.Charging;
import com.nuo.netbar.pojo.ChargingExample;

import java.util.List;

public interface ChargingService {
    List<Charge> getChargingList ();
    Charging selectByPrimaryKey(Integer id);
    Charge getCharging(Integer id);
    int updateByPrimaryKeySelective(Charging record);
    List<Charging> selectByExample(ChargingExample example);

    List<Charge> getChargingByNo(Integer no);

}
