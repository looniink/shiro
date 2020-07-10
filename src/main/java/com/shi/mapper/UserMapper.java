package com.shi.mapper;


import com.shi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapper {
	public User queryUserByName(String name);
}
