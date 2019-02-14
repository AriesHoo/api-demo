package com.aries.api.demo.controller;


import com.aries.api.demo.entity.BaseEntity;
import com.aries.api.demo.entity.UserEntity;
import com.aries.api.demo.service.UserService;
import com.aries.api.demo.util.AppUtil;
import com.aries.api.demo.util.EncoderUtil;
import com.aries.api.demo.util.ValidatorUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Author: AriesHoo on 2019/2/11 16:13
 * @E-Mail: AriesHoo@126.com
 * @Function: 用户模块
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "/user", description = "用户模块", tags = {"/user"})
public class UserController {
    @Autowired
    private UserService mUserService;
    @Autowired
    private HttpServletRequest mRequest;

    /**
     * 登录接口
     *
     * @param type     登录类型;0-账号/手机号;1-微信;2-qq
     * @param account  账号或手机号
     * @param password 账号密码模式
     * @param openId   三方登录openId
     * @return
     */
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseEntity login(@RequestParam(value = "type", required = false, defaultValue = "0") @ApiParam("登录类型;0-账号/手机号;1-微信;2-qq") Integer type,
                            @RequestParam(value = "account", required = false) @ApiParam("账号或手机号") String account,
                            @RequestParam(value = "password", required = false) @ApiParam("密码") String password,
                            @RequestParam(value = "openId", required = false) @ApiParam("微信/qq授权成功的openId") String openId) {
        UserEntity userEntity = null;
        switch (type) {
            case 1:
            case 2:
                if (StringUtils.isEmpty(openId)) {
                    return new BaseEntity()
                            .setMsg("请先使用微信/qq授权登录")
                            .setCode(201);
                }
                userEntity = mUserService.findUser(null, null, openId);
                if (userEntity == null) {
                    return BaseEntity
                            .success(new UserEntity());
                }
                mUserService.updateUserInfo(userEntity.id, null, null,
                        null, null, null, null, null, new Date());
                break;
            default:
                if (StringUtils.isEmpty(account)) {
                    return new BaseEntity()
                            .setMsg("请输入账号/手机号")
                            .setCode(201);
                }
                userEntity = mUserService.findUserById(account);
                if (userEntity == null) {
                    return new BaseEntity()
                            .setMsg("账号不存在请检查后重试")
                            .setCode(201);
                }
                if (StringUtils.isEmpty(password)) {
                    return new BaseEntity()
                            .setMsg("请输入您的密码")
                            .setCode(201);
                }
                userEntity = mUserService.findUser(account, EncoderUtil.encoderByMd5(password), null);
                if (userEntity == null) {
                    return new BaseEntity()
                            .setMsg("用户名或账号不正确")
                            .setCode(201);
                }
                mUserService.updateUserInfo(userEntity.id, null, null,
                        null, null, null, null, null, new Date());
                break;
        }
        if (userEntity == null) {
            return new BaseEntity()
                    .setMsg("用户名或账号不正确")
                    .setCode(201);
        }
        return BaseEntity.
                success(userEntity);
    }

    /**
     * 注册
     *
     * @param type
     * @param account
     * @param password
     * @param openId
     * @param url      头像url
     * @return
     */
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseEntity register(@RequestParam(value = "type", required = false, defaultValue = "0") @ApiParam("注册类型;0-账号/手机号;1-微信;2-qq") Integer type,
                               @RequestParam(value = "account", required = false) @ApiParam("账号-") String account,
                               @RequestParam(value = "password", required = false) @ApiParam("密码") String password,
                               @RequestParam(value = "openId", required = false) @ApiParam("微信/qq授权成功的openId") String openId,
                               @RequestParam(value = "headUrl", required = false, defaultValue = "") @ApiParam("用户头像") String url) {
        UserEntity userEntity = null;
        String id = AppUtil.getId();
        switch (type) {
            case 1:
                break;
            case 2:
                break;
            default:
                if (StringUtils.isEmpty(account)) {
                    return new BaseEntity()
                            .setMsg("请输入账号")
                            .setCode(201);
                }
                if (!ValidatorUtil.isUsername(account)) {
                    return new BaseEntity()
                            .setMsg("账号由5-20位字母+数字组合而成，且必须包含大小写字母")
                            .setCode(201);
                }
                if (StringUtils.isEmpty(password)) {
                    return new BaseEntity()
                            .setMsg("请输入密码")
                            .setCode(201);
                }
                if (!ValidatorUtil.isPassword(password)) {
                    return new BaseEntity()
                            .setMsg("密码由6-18大小写字母+数字组合而成")
                            .setCode(201);
                }
                userEntity = mUserService.findUserById(account);
                if (userEntity != null) {
                    return new BaseEntity()
                            .setMsg("账号已存在,请更换账号")
                            .setCode(201);
                }
                //注册
                mUserService.register(id, account, EncoderUtil.encoderByMd5(password), url, "", "");
                BaseEntity baseEntity = login(type, account, password, null);
                if (baseEntity != null && baseEntity.getData() != null) {
                    userEntity = (UserEntity) baseEntity.getData();
                }
                break;
        }
        if (userEntity != null) {
            return BaseEntity.
                    success("注册成功")
                    .setData(userEntity);
        }
        return new BaseEntity()
                .setMsg("注册失败,错误码(20000)")
                .setCode(201);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户信息-根据用户id")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public BaseEntity getUserInfo(@RequestParam(value = "id") @ApiParam("用户id-登录注册返回用户实体中id字段") String id) {
        UserEntity userEntity = mUserService.findUserById(id);
        if (StringUtils.isEmpty(id)) {
            return new BaseEntity()
                    .setMsg("请输入用户id")
                    .setCode(201);
        }
        if (userEntity != null) {
            return BaseEntity.
                    success("获取用户信息成功")
                    .setData(userEntity);
        }
        return new BaseEntity()
                .setMsg("暂无该账号信息")
                .setCode(201);
    }

    @ApiOperation(value = "更新用户信息-根据用户id")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public BaseEntity updateUserInfo(@RequestParam(value = "id") @ApiParam("用户id-登录注册返回用户实体中id字段") String id,
                                     @RequestParam(value = "nickName", required = false) @ApiParam("用户昵称") String nickName,
                                     @RequestParam(value = "headUrl", required = false) @ApiParam("用户头像") String headUrl,
                                     @RequestParam(value = "password", required = false) @ApiParam("密码-6至18位字母和数字组合") String password,
                                     @RequestParam(value = "phone", required = false) @ApiParam("绑定手机号") String phone,
                                     @RequestParam(value = "weChatId", required = false) @ApiParam("绑定微信号id") String weChatId,
                                     @RequestParam(value = "qqId", required = false, defaultValue = "") @ApiParam("绑定QQ号id") String qqId,
                                     @RequestParam(value = "signature", required = false, defaultValue = "") @ApiParam("个性签名") String signature) {
        UserEntity userEntity = mUserService.findUserById(id);
        if (StringUtils.isEmpty(id)) {
            return new BaseEntity()
                    .setMsg("请输入用户id")
                    .setCode(201);
        }
        if (userEntity != null) {
            if (!StringUtils.isEmpty(password) && !ValidatorUtil.isPassword(password)) {
                return new BaseEntity()
                        .setMsg("密码由6-18字母或数字组成")
                        .setCode(201);
            }
            mUserService.updateUserInfo(id, nickName, headUrl, StringUtils.isEmpty(password) ? null : EncoderUtil.encoderByMd5(password), phone, weChatId, qqId, signature, null);
            return BaseEntity.
                    success("获取用户信息成功")
                    .setData("");
        }
        return new BaseEntity()
                .setMsg("暂无该账号信息")
                .setCode(201);
    }

    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户列表")
    public BaseEntity getUserList(@RequestParam(value = "page") @ApiParam("起始页码从0开始") int page,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10")
                                  @ApiParam("每页10个数量") int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<UserEntity> list = mUserService.getUserList();
        PageInfo<UserEntity> pageInfo = new PageInfo<>(list);
        return BaseEntity.
                success(pageInfo.getList());
    }

}
