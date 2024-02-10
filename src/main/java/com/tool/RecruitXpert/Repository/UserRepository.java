package com.tool.RecruitXpert.Repository;

import com.tool.RecruitXpert.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

//    List<User> findById(int userId);
}
