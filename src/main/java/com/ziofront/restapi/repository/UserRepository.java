package com.ziofront.restapi.repository;

import com.ziofront.restapi.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    public User findUserByName(String name);


    public User findByName(String name);

}
