package com.bw.gaoyongpei.respstory;

import com.bw.gaoyongpei.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gaoyongpei on 2017/7/28.
 */
public interface UserRops extends JpaRepository<User,Integer>{


    User findByUnameAndPasswd(String uname,String passwd);
    User findByUname(String uname);

}
