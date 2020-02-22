package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Result;
import com.finalproject.quizsystem.repository.ResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceImplTest {

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private Pageable pageable;

    @InjectMocks
    public ResultServiceImpl resultService;

    @Test
    public void getAllResults() {
        Long userId = 1L;
        Page<Result> results = mock(Page.class);
        when(resultRepository.countAllByUserId(userId)).thenReturn(10L);
        when(pageable.getPageSize()).thenReturn(10);
        when(resultRepository.findAllByUserId(userId, pageable)).thenReturn(results);

        Page<Result> userResults = resultService.getAllResults(userId, pageable);
        assertEquals(results, userResults);
    }

    @Test
    public void saveShouldSaveToDB(){
        Result result = Result.builder().build();

        resultService.saveResult(result);
        verify(resultRepository).save(eq(result));
    }
}