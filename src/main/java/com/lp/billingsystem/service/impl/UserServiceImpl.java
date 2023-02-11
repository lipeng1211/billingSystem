package com.lp.billingsystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lp.billingsystem.domain.User;
import com.lp.billingsystem.service.UserService;
import com.lp.billingsystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author lpden
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-02-03 23:59:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




