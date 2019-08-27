package com.offcn.demo01.controller;


import com.offcn.demo01.bean.Person;
import com.offcn.demo01.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person2")
public class Person2Controller {

    @Autowired
    PersonRepository personRepository;

    //查询指定用户的姓名
    @GetMapping("findByNameIs/{name}")
    public Person findByNameIs(@PathVariable String name){
        return personRepository.findByNameIs(name);
    }

    //查询指定用户姓名和密码都相同的用户
    @GetMapping("findByNameIsAndPassword/{name}/{password}")
    public Person findByNameIsAndPassword(@PathVariable String name,@PathVariable String password){
        return personRepository.findByNameIsAndPassword(name,password);
    }

    //查询包含指定名字的用户
    @GetMapping("findByNameContaining/{name}")
    public List<Person> findByNameContatining(@PathVariable String  name){
        return personRepository.findByNameContaining(name);
    }
}
