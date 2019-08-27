package com.offcn.demo01.controller;

import com.offcn.demo01.bean.Person;
import com.offcn.demo01.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person3")
public class Person3Controller {

    @Autowired
    PersonRepository personRepository;

    //查询指定用户姓名的用户
    @GetMapping("getPerson/{name}")
    public Person getPerson(@PathVariable String name){
        return personRepository.getPerson(name);
    }

    //用户的登录验证
    @GetMapping("login/{name}/{password}")
    public Person login(@PathVariable String name,@PathVariable String password){
        return personRepository.login(name, password);
    }

    //根据名字模糊查询
    @GetMapping("getNamesLike/{name}")
    public List<Person> getNamesLike(@PathVariable String name){
        return personRepository.getNameLike(name);
    }

    //查询密码是5位数的全部用户
    @GetMapping("getPasswordisFive")
    public List<Person> getPasswordisFive(){
        return personRepository.getPasswordisFive();
    }
    //-----------------------------组合注解-------------------------------
    //修改用户名称
    @PutMapping(path = "updateName/{id}/{name}")
    public int updateName(@PathVariable Long id,@PathVariable String name){
        return personRepository.UpdateName(id,name);
    }

    //删除指定用户
    @DeleteMapping(path="deleteName/{name}")
    public  int DeleteName(@PathVariable String name){
        return  personRepository.DeleteName(name);
    }

}
