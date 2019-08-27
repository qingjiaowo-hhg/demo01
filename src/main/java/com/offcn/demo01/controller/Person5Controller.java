package com.offcn.demo01.controller;


import com.offcn.demo01.bean.Person;
import com.offcn.demo01.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person5")
public class Person5Controller {

    @Autowired
    PersonRepository personRepository;

    //查询指定用户的名称，按照id降序排序第一条记录
    @GetMapping("findFirstByNameOrderByIdDesc/{name}")
    public Person findFirstByNameOrderByIdDesc(@PathVariable String name) {

        return personRepository.findFirstByNameOrderByIdDesc(name);
    }

    //模糊查询指定用户名称，按照id降序排序前10条记录
    @GetMapping("findFirst10ByNameLikeOrderByIdDesc/{name}")
    public List<Person> findFirst10ByNameLikeOrderByIdDesc(@PathVariable String name) {
        return personRepository.findFirst10ByNameLikeOrderByIdDesc("%"+name+"%");
    }


    //查询指定用户名称，按照id升降序排序第一条记录
    @GetMapping("findTopByNameOrderByIdAsc/{name}")
    public Person findTopByNameOrderByIdAsc(@PathVariable String name) {
        return personRepository.findTopByNameOrderByIdAsc(name);
    }

    //模糊查询指定用户名称，按照id升序排序前10条记录
    @GetMapping("findTop10ByNameLikeOrderByIdAsc/{name}")
    public List<Person> findTop10ByNameLikeOrderByIdAsc(@PathVariable String name) {
        return personRepository.findTop10ByNameLikeOrderByIdAsc("%"+name+"%");
    }

    @GetMapping("findPerson/{id}")
    public Person findPerson(@PathVariable Long id) {
        return personRepository.findPerson(id);
    }
}
