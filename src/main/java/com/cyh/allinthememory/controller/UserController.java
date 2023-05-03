package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;


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
            return R.error( user.getUserId().toString());
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user1.getUserId());

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

        //1.将页面提交的密码password进行md5加密
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

            //5.登陆成功，将id存入session并返回登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUserId());

            return R.success(user);
        }
    }
}
