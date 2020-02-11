package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.repository.ResultRepository;
import com.finalproject.quizsystem.entity.Result;
import com.finalproject.quizsystem.service.ResultService;
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
    public List<Result> getAllResults(Long user) {
        return resultRepository.findAllByUserId(user);
    }
}
