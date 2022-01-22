package com.example.demo.dao;

import com.example.demo.entity.AuthUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户信息(AuthUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-10-15 11:21:03
 */
@Component(value = "AuthUserDao")
public interface AuthUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AuthUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param authUser 实例对象
     * @return 对象列表
     */
    List<AuthUser> queryAll(AuthUser authUser);

    /**
     * 新增数据
     *
     * @param authUser 实例对象
     * @return 影响行数
     */
    int insert(AuthUser authUser);

    /**
     * 修改数据
     *
     * @param authUser 实例对象
     * @return 影响行数
     */
    int update(AuthUser authUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}