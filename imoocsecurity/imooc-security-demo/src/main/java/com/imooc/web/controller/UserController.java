package com.imooc.web.controller;

import com.imooc.web.dto.User;
import com.imooc.web.dto.UserQueryCondition;
import com.imooc.web.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
//    @JsonView(User.UserSimpleView.class)
    @GetMapping
    public List<User> query(UserQueryCondition userQueryCondition, @PageableDefault(size = 10,page = 1,sort = "username,asc") Pageable pageable){
        log.info ("username : {}",userQueryCondition);
        log.info ("pageSize: {}, pageNum: {}, pageSort: {}"
                ,pageable.getPageSize (),pageable.getPageNumber (),pageable.getSort ());
        List<User> users = new ArrayList<> ();
        users.add (User.builder ().Username ("xiaoming").password ("adada").build ());
        users.add (User.builder ().Username ("zhaosi").password ("dadada").build ());
        users.add (User.builder ().Username ("zhangsan").password ("asdafsaa").build ());
        return users;
    }
/*
    @JsonView(User.UserSimpleView.class)
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
*/
//    @RequestMapping(value = "/user",method = RequestMethod.GET)
//    public List<User> query(@RequestParam(required = false,defaultValue = "youke") String username){
//        log.info ("username : {}",username);
//        List<User> users = new ArrayList<> ();
//        users.add (new User ("xiaoming","qwertyu"));
//        users.add (new User ("zhaosi","adadada"));
//        users.add (new User ("liuneng","sfsfsfsf"));
//        return users;
//    }

//    @JsonView(User.UserDetailView.class)
    @GetMapping(value = "/{id:\\d+}")
    public User getInfo(@PathVariable @ApiParam(value = "用戶id") String id){
        log.info ("进入getInfo服务 ,id : {}",id);
        return User.builder ().Username ("tom").build ();
    }

    @PostMapping
    @ApiOperation (value = "用戶創建服務")
    public User create(@Valid @RequestBody User user , BindingResult errors){
        return getUser (user, errors);
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user , BindingResult errors){
        return getUser (user, errors);
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id){
                log.info ("id : {}",id);
    }
    private User getUser(@RequestBody @Valid User user, BindingResult errors) {
        if(errors.hasErrors ()){
            errors.getAllErrors ().forEach (e -> log.info (".error : {} ",e.getDefaultMessage ()));
        }
        log.info ("User : {}",user);
        return user;
    }

    @GetMapping(value = "/error")
    public void getError(){
        throw new UserNotExistException ("1");
    }
}
