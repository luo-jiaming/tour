package cn.edu.hlju.tour.dao;

import cn.edu.hlju.tour.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmailAndPassword(@Param("email") String email, @Param("password")String password, @Param("permission")Long permission);

    User selectByEmail(String email);

    void updatePasswordByEmail(@Param("email") String email, @Param("password")String password);

    List<User> selectByUser(User user);

    void delByUserId(Long[] ids);

}