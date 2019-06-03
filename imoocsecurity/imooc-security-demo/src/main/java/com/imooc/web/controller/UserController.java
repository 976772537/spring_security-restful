package com.imooc.web.controller;

import com.imooc.web.dto.User;
import com.imooc.web.dto.UserQueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault(size = 10,page = 1,sort = "username,asc") Pageable pageable){
        log.info ("username : {}",userQueryCondition);
        log.info ("pageSize: {}, pageNum: {}, pageSort: {}"
                ,pageable.getPageSize (),pageable.getPageNumber (),pageable.getSort ());
        List<User> users = new ArrayList<> ();
        users.add (new User ("xiaoming","qwertyu"));
        users.add (new User ("zhaosi","adadada"));
        users.add (new User ("liuneng","sfsfsfsf"));
        return users;
    }
//    @RequestMapping(value = "/user",method = RequestMethod.GET)
//    public List<User> query(@RequestParam(required = false,defaultValue = "youke") String username){
//        log.info ("username : {}",username);
//        List<User> users = new ArrayList<> ();
//        users.add (new User ("xiaoming","qwertyu"));
//        users.add (new User ("zhaosi","adadada"));
//        users.add (new User ("liuneng","sfsfsfsf"));
//        return users;
//    }
}
