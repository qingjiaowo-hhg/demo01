package com.offcn.demo01.dao;

import com.offcn.demo01.bean.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Long> {

    //查询指定用户姓名的用户
    public Person findByNameIs(String name);

    //查询指定用户姓名和密码都相同的用户
    public Person findByNameIsAndPassword(String name,String password);

    //查询包含指定名字的用户
    public List<Person> findByNameContaining(String name);

    //-----------------------------自定义查询------------------------------
    //查询指定用户姓名的用户
    @Query("select p from Person p where p.name=:name")
    public Person getPerson(@Param("name") String name);

    //用户登录验证
    @Query("select p from Person p where p.name=?1 and p.password=?2")
    public Person login(@Param("name") String name,@Param("password")String password);

    //模糊查询
    @Query("select p from Person p where p.name like %:name%")
    public List<Person> getNameLike(@Param("name") String name);

    //查询密码是5位数的全部用户，使用mysql原始sql语句进行查询
    @Query(value="select * from person where length(password)=5",nativeQuery = true)
    public List<Person> getPasswordisFive();

    //------------------------------JPA@Modifying和Query、@Transactional组合注解进行数据的更新和删除-------------------------
    //修改用户名称，使用自定义语句来更新，需要显示声明开启事务
    @Modifying
    @Transactional(readOnly = false)
    @Query("update  Person p set p.name=?2 where p.id=?1")
    int UpdateName(Long id,String name);

    //删除指定用户，需要显示声明开启写事务
    @Modifying
    @Transactional(readOnly = false)
    @Query("delete  from Person p where p.name=?1")
    int DeleteName(String name);

    //--------------------使用JPA处理分页和排序------------------------------
    //排序查询，返回list集合
    List<Person> findByNameContaining(String name, Sort sort);

    //分页查询，查询计算元素的总个数，总页数，数据多的情况下，代价是昂贵的
    Page<Person> findByNameContaining(String name, Pageable pageable);

    //分页查询，返回的是一个片段，他只知道下一片段或上一片段是否可用
    Slice<Person> getByNameContaining(String name,Pageable pageable);

    //------------------使用JPA限制查询-----------------------------
    //查询指定用户名称，按照id降序排序第一条记录
    Person findFirstByNameOrderByIdDesc(String name);

    //模糊查询指定用户名称，按照id降序排序前10条记录
    List<Person> findFirst10ByNameLikeOrderByIdDesc(String name);

    //查询指定用户名称，按照id升降序排序第一条记录
    Person findTopByNameOrderByIdAsc(String name);

    //模糊查询指定用户名称，按照id升序排序前10条记录
    List<Person> findTop10ByNameLikeOrderByIdAsc(String name);

    //多表查询
    @Query("select p from Person p join p.dogs d where p.id=?1")
    Person findPerson(@Param("id") Long id);
}
