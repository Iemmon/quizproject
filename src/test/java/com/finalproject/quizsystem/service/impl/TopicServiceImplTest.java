package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.entity.Topic;
import com.finalproject.quizsystem.repository.TopicRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceImplTest {

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    public void findAllShouldReturnListOfTopics(){

        List<Topic> topics = new ArrayList<>();
        assertEquals(topics, topicService.findAll());

        verify(topicRepository, atLeastOnce()).findAll();
    }
}
