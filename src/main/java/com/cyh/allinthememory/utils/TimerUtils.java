package com.cyh.allinthememory.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cyh.allinthememory.entity.Capsule;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.CapsuleService;
import com.cyh.allinthememory.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author keyh
 * @description 定时器任务 实现定时器方法，
 * @date 2023/5/10 19:13
 */
@Slf4j
@Component
public class TimerUtils implements CommandLineRunner {

    @Autowired
    private CapsuleService capsuleService;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        //先执行一遍胶囊验证
        capsuleVerify();
        //执行判断一年未登录
        notLoginVerify();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        /*
         * @description TODO 定时器任务，检测胶囊验证
         */
        TimerTask timerTaskCapsule = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                //定时器每天执行一次胶囊验证
                capsuleVerify();
                System.out.println("我是定时器任务胶囊");
            }
        };

        /*
         * @description TODO 定时器任务，检测账号是否一年内未登录
         */
        TimerTask timerTaskNotLogin = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                notLoginVerify();
                System.out.println("我是定时器任务一年未登录");
            }
        };
        scheduledExecutorService.scheduleAtFixedRate(timerTaskCapsule, 1, 1, TimeUnit.DAYS);
    }

    /**
     * @param :
     * @return void
     * @author 宇恒
     * @description TODO 验证胶囊是否到达时间的方法
     * 首先执行一遍检索数据库
     * 1.到达时间修改数据库，并发送短信
     * 2.没有到时间，不修改数据库
     * @date 2023/5/13 14:20
     */
    public void capsuleVerify() throws Exception {
        //遍历isLocked为1的全部胶囊数据
        LambdaQueryWrapper<Capsule> queryWrapper = new LambdaQueryWrapper<>();
        List<Capsule> listIsLocked = capsuleService.list(queryWrapper.eq(Capsule::getIsLocked, 1));
        //获取当前的日期情况，nowDate是系统的当前时间
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowDate = date.format(formatter);
        //遍历全部的  当前的日期和dateSend.toString()是否相同
        for (Capsule capsule : listIsLocked) {
            //胶囊的发送时间
            String sendTime = capsule.getDateSend().toString();
            System.out.println("胶囊的发送时间：" + sendTime + "和当前时间" + nowDate);
            System.out.println("一不一样呢" + sendTime.equals(nowDate));

            if (sendTime.equals(nowDate)) {
                //1：相同即到达发送时间;
                LambdaUpdateWrapper<Capsule> updateWrapper = new LambdaUpdateWrapper<>();
                //1.1：修改isLocked为0；
                boolean update = capsuleService.update(updateWrapper.eq(Capsule::getCapsuleId, capsule.getCapsuleId()).set(Capsule::getIsLocked, 0));
                if (update) {
                    //2.修改成功，根据胶囊的userId获取用户对象的手机号码；
                    LambdaQueryWrapper<User> queryWrapperUser = new LambdaQueryWrapper<User>();
                    String phoneNumber = userService.getOne(queryWrapperUser.eq(User::getUserId, capsule.getUserId())).getPhone();
                    if (phoneNumber != null) {
                        //3.用户存了手机号，发送短信息
                        SMSUtils.sendMsg("16634866321", SMSUtils.TemplateCodeCapsule);
                    }
                }
            }
        }
    }

    /**
     * @param :
     * @return void
     * @author 宇恒
     * @description TODO 判断是否超过一年未登录，
     * 1.每次登陆、注册、验证，刷新最后一次登录信息
     * 2.服务器启动时检测一次，之后每天检测一次(暂时)
     * 3.首先遍历status状态未1的用户信息，在判断，
     * @date 2023/5/13 15:33
     */
    public void notLoginVerify() throws Exception {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        List<User> userList = userService.list(queryWrapper.eq(User::getUserStatus, 1));
        LocalDate yearAgo = getYearAgo();
        for (User user : userList) {
            System.out.println("yearAgo" + yearAgo + "user" + user.getLastLogin() + "一样吗" + user.getLastLogin().equals(yearAgo));
            System.out.println(yearAgo.isAfter(user.getLastLogin()));
            if (yearAgo.isAfter(user.getLastLogin())) {
                //最近一次登录的日期在一年之前，发送短信、更改用户状态0
                LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
                boolean update = userService.update(updateWrapper.eq(User::getUserId, user.getUserId()).set(User::getUserStatus, 0));
                if (update) {
                    System.out.println("我要发送短信了");
                    SMSUtils.sendMsg("16634866321", SMSUtils.TemplateCodeUrgent);
                }
            }
        }

    }

    /**
     * @param :
     * @return LocalDate
     * @author 宇恒
     * @description TODO 获取一年以前的日期
     * @date 2023/5/13 15:14
     */
    public LocalDate getYearAgo() {
        LocalDate date = LocalDate.now();
        int year = date.getYear() - 1;
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        return LocalDate.of(year, month, day);
    }

}
