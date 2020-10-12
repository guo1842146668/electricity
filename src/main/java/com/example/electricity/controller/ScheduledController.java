package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.config.DefaultSchedulingConfigurer;
import com.example.electricity.entity.Scheduled;
import com.example.electricity.service.IScheduledService;
import com.example.electricity.tool.ReadFile;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-02
 */
@CrossOrigin
@RestController
@RequestMapping("/scheduled")
public class ScheduledController {
    @Resource
    private IScheduledService iScheduledService;


    @GetMapping("/getAll")
    public Result getAll(String equipmentNO) {
        List<Scheduled> byUserID = iScheduledService.getByUserID(equipmentNO);
        if (byUserID.size() < 5) {
            Scheduled scheduled = new Scheduled();
            scheduled.setCronStatus(-1);
            for (int i = 0; i < 5 - byUserID.size(); i++) {
                scheduled.setCronName(equipmentNO + (i + 1 + byUserID.size()));
                scheduled.setCronStartTime("00:00");
                scheduled.setCronEndTime("00:00");
                scheduled.setEquipmentNO(equipmentNO);
                iScheduledService.saveScheduled(scheduled);
            }
            byUserID = iScheduledService.getByUserID(equipmentNO);
        }
        return ResultUtil.seccess(byUserID);
    }


    @GetMapping("/openingTiming")
    public Result openingTiming(Integer cronId,String startTime,String endTime){
        Scheduled scheduledByID = iScheduledService.getScheduledByID(cronId);
        List<Scheduled> byUserID = iScheduledService.getByCronId(scheduledByID.getEquipmentNO());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < byUserID.size(); i++) {
            if(byUserID.get(i).getCronStartTime().equals("00:00") && byUserID.get(i).getCronEndTime().equals("00:00")){

            }else{
                list.add(byUserID.get(i).getCronStartTime()+"-"+byUserID.get(i).getCronEndTime());
            }
        }
        list.add(startTime+"-"+endTime);
        RestTemplate restTemplate = new RestTemplate();
        String[] split = startTime.split(":");
        String[] split1 = endTime.split(":");
        if(ReadFile.checkOverlap(list)){
            return ResultUtil.error(500,"时间设置存在交叉");
        }

        String forObject = restTemplate.getForObject("http://127.0.0.1:80/scheduled/openingTiming?iccid=" + scheduledByID.getEquipmentNO() + "&hour1=" + split[0]
                + "&minute1=" + split[1] + "&hour2=" + split1[0] + "&minute2=" + split1[1] + "&ID=" + cronId, String.class);
        if(!forObject.equals("success")){
            return ResultUtil.error(500,"设置定时失败");
        }
        scheduledByID.setCronStartTime(startTime);
        scheduledByID.setCronEndTime(endTime);
        if(startTime.equals("00:00")&&endTime.equals("00:00")){
            scheduledByID.setCronStatus(-1);
        }else{
            scheduledByID.setCronStatus(1);
        }
        iScheduledService.updateScheduled(scheduledByID);
        return ResultUtil.seccess();
    }


    @PutMapping("/down")
    public Result down(Integer cronId){
        Scheduled scheduledByID = iScheduledService.getScheduledByID(cronId);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://127.0.0.1:80/equipment/down?equipmentNO=" + scheduledByID.getEquipmentNO(), String.class);
        if(forObject.equals("success")){
            //return ResultUtil.seccess();
        }else{
            return ResultUtil.error(500,"关闭失败");
        }
        scheduledByID.setCronStatus(-1);
        iScheduledService.updateScheduled(scheduledByID);
        return ResultUtil.seccess();
    }

}
