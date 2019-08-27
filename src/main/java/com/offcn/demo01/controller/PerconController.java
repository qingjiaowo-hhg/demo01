package com.offcn.demo01.controller;

import com.offcn.demo01.bean.Person;
import com.offcn.demo01.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PerconController {

    @Autowired
    PersonRepository personRepository;

    //添加
    @PostMapping(path = "addPerson")
    public void addPerson(@RequestBody Person person){
        personRepository.save(person);
    }

    //查询全部
    @GetMapping(path="getAllPerson")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    //根据Id删除
    @DeleteMapping(path = "deletePerson")
    public void deletePerson(@RequestParam Long id){
        personRepository.deleteById(id);
    }

    //修改（有id修改，没有id就是增加）
    @PutMapping(path = "updatePerson")
    public  void updatePerson(@RequestBody Person person){
        personRepository.saveAndFlush(person);
    }
}
