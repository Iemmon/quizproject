package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.service.ResultService;
import com.finalproject.quizsystem.repository.ResultRepository;
import com.finalproject.quizsystem.entity.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;

    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public void saveResult(Result result){
        resultRepository.save(result);
    }

    @Override
    public Page <Result> getAllResults(Long id, Pageable pageable) {
        long count = resultRepository.countAllByUserId(id);
        int maxPages = (int) count / pageable.getPageSize();
        int currentPageNum = pageable.getPageNumber();
        if (currentPageNum > maxPages) {
            pageable = PageRequest.of(0, 5);
        }
        return resultRepository.findAllByUserId(id, pageable);
    }
}
