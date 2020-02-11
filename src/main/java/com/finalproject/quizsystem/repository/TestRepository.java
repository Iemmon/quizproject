package com.finalproject.quizsystem.repository;

import com.finalproject.quizsystem.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByTopicId(Long topicId);
}
