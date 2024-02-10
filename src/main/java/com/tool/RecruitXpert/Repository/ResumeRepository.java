package com.tool.RecruitXpert.Repository;

import com.tool.RecruitXpert.Entities.ResumeEntity;
import com.tool.RecruitXpert.Entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Integer> {

    Optional<ResumeEntity> findByName(String fileName);

    List<ResumeEntity> findByUser(User user);

    @Transactional
    @Modifying
    @Query(value = "delete from image_data where userid=:userid", nativeQuery = true)
    void deleteByUserId(@Param("userid") Integer userid);
}
