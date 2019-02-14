package com.aries.api.demo.mapper;


import com.aries.api.demo.entity.UserEntity;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: AriesHoo on 2019/2/11 15:13
 * @E-Mail: AriesHoo@126.com
 * @Function:
 * @Description:
 */
@Repository
public interface UserMapper {

    /**
     * 通过账号/手机号 密码查询用户
     *
     * @param account
     * @param password
     * @return
     */
    UserEntity findUser(String account, String password, String openId);

    /**
     * 通过账号/手机号 查询用户
     *
     * @param account
     * @return
     */
    UserEntity findUserById(String account);

    List<UserEntity> getUserList();

    void register(String id, String account, String password, String headUrl, String weChatId, String qqId);

    void updateUserInfo(String id, String nickName, String headUrl, String password, String phone,
                        String weChatId, String qqId, String signature, Date loginTime);
}
