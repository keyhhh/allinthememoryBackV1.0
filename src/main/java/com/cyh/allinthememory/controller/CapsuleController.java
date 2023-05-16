package com.cyh.allinthememory.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cyh.allinthememory.common.R;
import com.cyh.allinthememory.entity.Capsule;
import com.cyh.allinthememory.entity.Help;
import com.cyh.allinthememory.entity.User;
import com.cyh.allinthememory.service.CapsuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author keyh
 * @description 胶囊的控制器
 * @date 2023/5/9 11:50
 */
@Slf4j
@RestController
@RequestMapping("/capsule")
public class CapsuleController {
    @Autowired
    private CapsuleService capsuleService;

    /**
     * @param user:
     * @param request:
     * @return R<List < Capsule>>
     * @author 宇恒
     * @description TODO 胶囊界面获取全部胶囊信息
     * @date 2023/5/9 14:20
     */
    @PostMapping("/getcapsule")
    public R<List<Capsule>> getCapsule(@RequestBody User user, HttpServletRequest request) {
        LambdaQueryWrapper<Capsule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Capsule::getUserId, user.getUserId());
        List<Capsule> capsuleList = capsuleService.list(queryWrapper);

        return R.success(capsuleList);
    }


    /**
     * @param capsule:
    	 * @param request:
      * @return R<Capsule>
     * @author 宇恒
     * @description TODO 点击到胶囊内部，通过id获取胶囊的全部信息
     * @date 2023/5/10 22:01
     */
    @PostMapping("/getcapsulebyid")
    public R<Capsule> getCapsuleById(@RequestBody Capsule capsule, HttpServletRequest request) {
        LambdaQueryWrapper<Capsule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Capsule::getCapsuleId,capsule.getCapsuleId());
        Capsule capsule1 = capsuleService.getOne(queryWrapper);
        if (capsule1 == null){
            return R.error("error");
        } else {
            return R.success(capsule1);
        }
    }

    /**
     * @param capsule:
    	 * @param request:
      * @return R<List<Capsule>>
     * @author 宇恒
     * @description TODO 上传求助信息
     * @date 2023/5/10 22:02
     */
    @PostMapping("/upcapsule")
    public R<List<Capsule>> upCapsule(@RequestBody Capsule capsule, HttpServletRequest request) {
        boolean save = capsuleService.save(capsule);
        if (save) {
            LambdaQueryWrapper<Capsule> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Capsule::getUserId, capsule.getUserId());
            List<Capsule> capsuleList = capsuleService.list(queryWrapper);
            return R.success(capsuleList);
        } else {
            return R.error("提交失败");
        }


    }

    /**
     * @param capsule:
    	 * @param request:
      * @return R<String>
     * @author 宇恒
     * @description TODO 删除胶囊的方法
     * @date 2023/5/17 0:10
     */
    @PostMapping("/decapsule")
    public R<String> deCapsule(@RequestBody Capsule capsule, HttpServletRequest request) {
        LambdaQueryWrapper<Capsule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Capsule::getCapsuleId, capsule.getCapsuleId());
        boolean remove = capsuleService.remove(queryWrapper);
        if (remove){
            return R.success("删除成功");
        } else {
            return R.error("删除失败");
        }
    }
}
