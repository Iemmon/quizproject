package com.finalproject.quizsystem.service.impl;


import com.finalproject.quizsystem.entity.Result;
import com.finalproject.quizsystem.repository.ResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

    @Mock
    public ResultRepository resultRepository;

    @InjectMocks
    public ResultServiceImpl resultService;

    @Test
    public void getAllResults() {
        Long userId = 1L;
        List<Result> results = new ArrayList<>();
        when(resultRepository.findAllByUserId(userId)).thenReturn(results);
        List<Result> userResults = resultService.getAllResults(userId);

        assertEquals(results, userResults);
    }
}