package com.finalproject.quizsystem.service.impl;

import com.finalproject.quizsystem.repository.TopicRepository;
import com.finalproject.quizsystem.entity.Topic;
import com.finalproject.quizsystem.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }
}
