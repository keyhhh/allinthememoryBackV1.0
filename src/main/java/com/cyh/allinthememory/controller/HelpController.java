package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.Help;
import com.cyh.allinthememory.entity.HelpDiscuss;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.HelpDiscussService;
import com.cyh.allinthememory.service.HelpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author keyh
 * @description 求助信息
 * @date 2023/5/8 21:08
 */
@Slf4j
@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    private HelpService helpService;
    @Autowired
    private HelpDiscussService helpDiscussService;

    /**
     * @param help:
     * @param request:
     * @return R<List < Help>>
     * @author 宇恒
     * @description TODO 展示全部的求助信息
     * @date 2023/5/9 11:18
     */
    @PostMapping("/gethelp")
    public R<List<Help>> likeRecordPublic(@RequestBody Help help, HttpServletRequest request) {
        List<Help> helpList = helpService.list();
        return R.success(helpList);
    }

    /**
     * @param help:
     * @param request:
     * @return R<Help>
     * @author 宇恒
     * @description TODO 进去详细界面，通过id获取求助信息的全部内容
     * @date 2023/5/9 11:18
     */
    @PostMapping("/gethelpbyid")
    public R<Help> getHelpByID(@RequestBody Help help, HttpServletRequest request) {
        LambdaQueryWrapper<Help> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Help::getHelpId, help.getHelpId());
        Help help1 = helpService.getOne(queryWrapper);
        return R.success(help1);
    }

    /**
     * @param user:
     * @param request:
     * @return R<Help>
     * @author 宇恒
     * @description TODO 返回我的发布的求助信息
     * @date 2023/5/13 21:20
     */
    @PostMapping("/gethelpmypu")
    public R<List<Help>> getHelpMyPu(@RequestBody User user, HttpServletRequest request) {
        LambdaQueryWrapper<Help> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Help::getUserId, user.getUserId());
        List<Help> helpList = helpService.list(queryWrapper);
        return R.success(helpList);
    }

    /**
     * @param user:
     * @param request:
     * @return R<Help>
     * @author 宇恒
     * @description TODO 返回我的同求信息
     * @date 2023/5/13 21:22
     */
    @PostMapping("/gethelpmylike")
    public R<List<Help>> getHelpMyLike(@RequestBody User user, HttpServletRequest request) {
        //存储用户同求的求助信息
        List<Help> likedHelpList = new ArrayList<>();

        List<Help> helpList = helpService.list();
        for (Help helpItem : helpList){
            if (helpItem.getLikedUserId() == null || "".equals(helpItem.getLikedUserId())){
                continue;
            } else {
                String[] likedUserId = helpItem.getLikedUserId().split(",");
                List<String> list = new ArrayList<>(Arrays.asList(likedUserId));
                if (list.contains(Long.toString(user.getUserId()))) {
                    //判断列表中是否存在传过来的id
                    likedHelpList.add(helpItem);
                }
            }
        }
        return R.success(likedHelpList);
    }



    /**
     * @param help:
     * @param request:
     * @return R<List < HelpDiscuss>>
     * @author 宇恒
     * @description TODO 获取全部的评论信息
     * @date 2023/5/9 11:17
     */
    @PostMapping("/getdiscuss")
    public R<List<HelpDiscuss>> getDiscuss(@RequestBody Help help, HttpServletRequest request) {

        LambdaQueryWrapper<HelpDiscuss> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HelpDiscuss::getHelpId, help.getHelpId());
        List<HelpDiscuss> list = helpDiscussService.list(queryWrapper);

        return R.success(list);
    }


    /**
     * @param helpDiscuss:
     * @param request:
     * @return R<List < HelpDiscuss>>
     * @author 宇恒
     * @description TODO 上传评论信息
     * @date 2023/5/9 11:17
     */
    @PostMapping("/updiscuss")
    public R<List<HelpDiscuss>> upDiscuss(@RequestBody HelpDiscuss helpDiscuss, HttpServletRequest request) {
        boolean save = helpDiscussService.save(helpDiscuss);
        if (save) {
            LambdaQueryWrapper<HelpDiscuss> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(HelpDiscuss::getHelpId, helpDiscuss.getHelpId());
            List<HelpDiscuss> list = helpDiscussService.list(queryWrapper);
            return R.success(list);
        } else {
            return R.error("请稍后再试");
        }
    }

    /**
     * @param help:
    	 * @param request:
      * @return R<Help>
     * @author 宇恒
     * @description TODO 上传求助信息
     * @date 2023/5/10 22:01
     */
    @PostMapping("/uphelp")
    public R<Help> upHelp(@RequestBody Help help, HttpServletRequest request) {
        log.info("###########################"+help);
        boolean save = helpService.save(help);
        if (save){
            return R.success(help);
        }else {
            return R.error("提交失败");
        }
    }


    @PostMapping("/changeuserlike")
    public R<String> changeUserLike(@RequestBody Help help, HttpServletRequest request) {
        //根据id找求助信息
        Help byId = helpService.getById(help.getHelpId());
        //获取这个help的原本的同求id,如果为空就直接添加(help表的liked_user_id)
        if ("".equals(byId.getLikedUserId()) || byId.getLikedUserId() == null){
            List<String> likedUserIdList = new ArrayList<>();
            likedUserIdList.add(help.getLikedUserId());
            String newLikedUserId = String.join(",", likedUserIdList);
            LambdaUpdateWrapper<Help> updateWrapper = new LambdaUpdateWrapper<>();
            boolean update = helpService.update(updateWrapper
                    .eq(Help::getHelpId, help.getHelpId())
                    .set(Help::getLikedUserId, newLikedUserId));
            if (update){
                return R.success("切换同求成功");
            }else{
                return R.error("切换同求失败");
            }
        }else{
            //求助信息喜欢的数据不为空的情况
            String[] likedUserId = byId.getLikedUserId().split(",");
            List<String> likedUserIdList = new ArrayList<>(Arrays.asList(likedUserId));
            //判断传递过来的likedUserId是否在查找出来的likedUserId中
            if (likedUserIdList.contains(help.getLikedUserId())){
                //存在删除掉
                likedUserIdList.remove(help.getLikedUserId());
            }else{
                //不存在添加上
                likedUserIdList.add(help.getLikedUserId());
            }
            String newLikedUserId = String.join(",", likedUserIdList);
            LambdaUpdateWrapper<Help> updateWrapper = new LambdaUpdateWrapper<>();
            boolean update = helpService.update(updateWrapper
                    .eq(Help::getHelpId, help.getHelpId())
                    .set(Help::getLikedUserId, newLikedUserId));
            if (update){
                return R.success("切换同求成功");
            }else{
                return R.error("切换同求失败");
            }
        }
    }
}
