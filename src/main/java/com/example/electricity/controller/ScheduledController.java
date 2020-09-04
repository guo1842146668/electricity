package com.example.electricity.controller;


import com.example.electricity.common.Result;
import com.example.electricity.common.ResultUtil;
import com.example.electricity.config.DefaultSchedulingConfigurer;
import com.example.electricity.entity.Scheduled;
import com.example.electricity.service.IScheduledService;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private DefaultSchedulingConfigurer defaultSchedulingConfigurer;

    @GetMapping("/getAll")
    public Result getAll(Integer userID) {
        List<Scheduled> byUserID = iScheduledService.getByUserID(userID);
        if (byUserID.size() < 5) {
            Scheduled scheduled = new Scheduled();
            scheduled.setCronStatus(-1);
            for (int i = 0; i < 5 - byUserID.size(); i++) {
                scheduled.setCronName(userID + "task" + (i + 1 + byUserID.size()));
                scheduled.setCronStartTime("0 00 00 * * ?");
                scheduled.setCronEndTime("0 00 00 * * ?");
                scheduled.setUserID(userID);
                iScheduledService.saveScheduled(scheduled);
            }
            byUserID = iScheduledService.getByUserID(userID);
        }
        for (int i = 0; i < byUserID.size(); i++) {
            byUserID.get(i).setCronStartTime(StringConversion(byUserID.get(i).getCronStartTime()));
            byUserID.get(i).setCronEndTime(StringConversion(byUserID.get(i).getCronEndTime()));
        }
        return ResultUtil.seccess(byUserID);
    }


    @GetMapping("openingTiming")
    public Result openingTiming(Integer cronId,String startTime,String endTime) throws InterruptedException {
        while (!defaultSchedulingConfigurer.inited()) {
            Thread.sleep(100);
        }
        Scheduled scheduledByID = iScheduledService.getScheduledByID(cronId);
        scheduledByID.setCronStartTime(timeConversion(startTime));
        scheduledByID.setCronEndTime(timeConversion(endTime));
        iScheduledService.updateScheduled(scheduledByID);
        startTimer(scheduledByID);
        return ResultUtil.seccess();
    }

    private void startTimer(Scheduled scheduledByID){
        if (scheduledByID.getCronStartTime() != null && scheduledByID.getCronStartTime() != null) {
            if (defaultSchedulingConfigurer.hasTask(scheduledByID.getUserID() + "start" + scheduledByID.getCronName())) {
                defaultSchedulingConfigurer.cancelTriggerTask(scheduledByID.getUserID() + "start" + scheduledByID.getCronName());
                defaultSchedulingConfigurer.cancelTriggerTask(scheduledByID.getUserID() + "end" + scheduledByID.getCronName());

            }
            defaultSchedulingConfigurer.addTriggerTask(scheduledByID.getUserID() + "start" + scheduledByID.getCronName(),
                    new TriggerTask(
                            () -> System.out.println("开始执行"),
                            new CronTrigger(scheduledByID.getCronStartTime())));
            defaultSchedulingConfigurer.addTriggerTask(scheduledByID.getUserID() + "end" + scheduledByID.getCronName(),
                    new TriggerTask(
                            () -> System.out.println("结束执行"),
                            new CronTrigger(scheduledByID.getCronEndTime())));
            scheduledByID.setCronStatus(1);
            iScheduledService.updateScheduled(scheduledByID);

        }
    }

    private String timeConversion(String date){
        if(date == null){
            return null;
        }
        String[] split = date.split(":");
        return  "0 "+split[1]+" "+split[0]+" * * ?";
    }

    private String StringConversion(String string){
        if(string == null){
            return null;
        }
        String[] split = string.split(" ");
        return  split[2]+":"+split[1];
    }


    @GetMapping("turnTiming")
    public Result turnTiming(Integer cronId){
        Scheduled scheduledByID = iScheduledService.getScheduledByID(cronId);
        defaultSchedulingConfigurer.cancelTriggerTask(scheduledByID.getUserID() + "start" + scheduledByID.getCronName());
        defaultSchedulingConfigurer.cancelTriggerTask(scheduledByID.getUserID() + "end" + scheduledByID.getCronName());
        scheduledByID.setCronStatus(-1);
        iScheduledService.updateScheduled(scheduledByID);
        return ResultUtil.seccess();
    }

}
