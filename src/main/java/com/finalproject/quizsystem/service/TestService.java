package com.finalproject.quizsystem.service;

import com.finalproject.quizsystem.entity.Test;

import java.util.List;

public interface TestService {

    List<Test> findAllByTopicId(Long id);
}
