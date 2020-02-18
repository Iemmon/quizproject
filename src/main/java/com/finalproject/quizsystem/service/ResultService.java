package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResultService {

//    List<Result> getAllResults(Long id);

    Page<Result> getAllResults(Long id, Pageable pageable);

    void saveResult(Result result);


}
