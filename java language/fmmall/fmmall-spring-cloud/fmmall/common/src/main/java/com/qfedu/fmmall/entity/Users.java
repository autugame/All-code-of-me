package com.qfedu.fmmall.entity;

import lombok.*;

import java.util.Date;

/**
 * 表名：users
 * 表注释：用户
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    /**
     * 主键id 用户id
     * -- GETTER --
     *  获取主键id 用户id
     *
     * @return userId - 主键id 用户id

     */
    private Integer userId;

    /**
     * 用户名 用户名
     * -- GETTER --
     *  获取用户名 用户名
     *
     * @return username - 用户名 用户名

     */
    private String username;

    /**
     * 密码 密码
     * -- GETTER --
     *  获取密码 密码
     *
     * @return password - 密码 密码

     */
    private String password;

    /**
     * 昵称 昵称
     * -- GETTER --
     *  获取昵称 昵称
     *
     * @return nickname - 昵称 昵称

     */
    private String nickname;

    /**
     * 真实姓名 真实姓名
     * -- GETTER --
     *  获取真实姓名 真实姓名
     *
     * @return realname - 真实姓名 真实姓名

     */
    private String realname;

    /**
     * 头像 头像
     * -- GETTER --
     *  获取头像 头像
     *
     * @return userImg - 头像 头像

     */
    private String userImg;

    /**
     * 手机号 手机号
     * -- GETTER --
     *  获取手机号 手机号
     *
     * @return userMobile - 手机号 手机号

     */
    private String userMobile;

    /**
     * 邮箱地址 邮箱地址
     * -- GETTER --
     *  获取邮箱地址 邮箱地址
     *
     * @return userEmail - 邮箱地址 邮箱地址

     */
    private String userEmail;

    /**
     * 性别 M(男) or F(女)
     * -- GETTER --
     *  获取性别 M(男) or F(女)
     *
     * @return userSex - 性别 M(男) or F(女)

     */
    private String userSex;

    /**
     * 生日 生日
     * -- GETTER --
     *  获取生日 生日
     *
     * @return userBirth - 生日 生日

     */
    private Date userBirth;

    /**
     * 注册时间 创建时间
     * -- GETTER --
     *  获取注册时间 创建时间
     *
     * @return userRegtime - 注册时间 创建时间

     */
    private Date userRegtime;

    /**
     * 更新时间 更新时间
     * -- GETTER --
     *  获取更新时间 更新时间
     *
     * @return userModtime - 更新时间 更新时间

     */
    private Date userModtime;
}