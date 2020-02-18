package com.finalproject.quizsystem.repository;

import com.finalproject.quizsystem.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findAllByUserId(Long id);

    Page<Result> findAllByUserId(Long id, Pageable pageable);

    Long countAllByUserId(Long id);

}
