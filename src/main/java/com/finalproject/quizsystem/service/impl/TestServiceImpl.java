package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.repository.TestRepository;
import com.finalproject.quizsystem.entity.Test;
import com.finalproject.quizsystem.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<Test> findAllByTopicId(Long id) {
        return testRepository.findAllByTopicId(id);
    }
}
