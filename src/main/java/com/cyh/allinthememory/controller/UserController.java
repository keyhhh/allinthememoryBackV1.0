package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;


/**
 * X`
 *
 * @author 宇恒
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("testId")
    public R<User> testId(@RequestBody User user, HttpServletRequest request) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserId, user.getUserId());
        User user1 = userService.getOne(queryWrapper);

        if (user1 == null) {
            return R.error(user.getUserId().toString());
        }

        //设置session，但是获取不到
        HttpSession session = request.getSession();
        session.setAttribute("user", user1.getUserId());

        //更新用户最后一次登陆的时间
        Boolean setUserLastLoginBoolean = setUserLastLogin(user);
        if (!setUserLastLoginBoolean) {
            log.error("设置最后一次登录时间错误");
        }

        user1.setPassword("");
        return R.success(user1);

    }

    /**
     * @param user:
     * @param request:
     * @return R<User>
     * @author 宇恒
     * @description TODO
     * @date 2023/5/1 23:27
     */
    @PostMapping("login")
    public R<User> login(@RequestBody User user, HttpServletRequest request) {

        //1.将页面提交的密码password进行md5加密(32位小)
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2.根据提交的用户名username查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, user.getAccount());
        User user1 = userService.getOne(queryWrapper);

        //3.若未查询到返回登录失败结果
        if (user1 == null) {
            return R.error("用户不存在");
        }

        //4.密码比对，不一致返回登陆失败结果
        if (!user1.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        //5.登陆成功，将id存入session并返回登录成功
        HttpSession session = request.getSession();
        session.setAttribute("user", user1.getUserId());

        //6.返回给前端用户的数据，但是不返回密码
        user1.setPassword("");

        //7.更新用户最后一次登陆的时间
        Boolean setUserLastLoginBoolean = setUserLastLogin(user);
        if (!setUserLastLoginBoolean) {
            log.error("设置最后一次登录时间错误");
        }
        return R.success(user1);
    }


    @PostMapping("register")
    public R<User> register(@RequestBody User user, HttpServletRequest request) {
        //判断账号是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, user.getAccount());
        User user1 = userService.getOne(queryWrapper);
        if (user1 != null) {
            return R.error("该账号已存在");
        }
        //将新用户添加到数据库
        else {
            user.setUserStatus(1);
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userService.save(user);

            //登陆成功，将id存入session并返回登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUserId());

            //更新用户最后一次登陆的时间
            Boolean setUserLastLoginBoolean = setUserLastLogin(user);
            if (!setUserLastLoginBoolean) {
                log.error("设置最后一次登录时间错误");
            }

            return R.success(user);
        }
    }


    /**
     * @param user:
     * @return R<User>
     * @author 宇恒
     * @description TODO 传递过来部分可以修改的信息，进行用户信息修改
     * @date 2023/5/10 21:50
     */
    @PostMapping("updateuser")
    public R<User> updateUser(@RequestBody User user) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUserId, user.getUserId());

        if ( user.getPassword() != null){
            String password = user.getPassword();
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            user.setPassword(password);
        }

        userService.update(user, updateWrapper);
        log.info("@#$%^&*@#$%^&*@#$%^&*" + user.toString());

        return R.success(user);
    }

    /**
     * @param user:
     * @return Boolean
     * @author 宇恒
     * @description TODO 为登陆注册的用户的lastLogin属性更新内容，同时如果是已经发送过短信的需要更新status
     * @date 2023/5/13 15:52
     */
    public Boolean setUserLastLogin(User user) {
        LocalDate localDate = LocalDate.now();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        return userService.update(updateWrapper
                .eq(User::getUserId, user.getUserId())
                .set(User::getLastLogin, localDate)
                .set(User::getUserStatus, 1));
    }


}
