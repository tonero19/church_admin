package com.tony.church.service;

import com.tony.church.dao.ChurchEventRepository;
import com.tony.church.entity.ChurchEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChurchEventServiceImpl implements ChurchEventService {

    private ChurchEventRepository churchEventRepository;

    @Autowired
    public ChurchEventServiceImpl(ChurchEventRepository churchEventRepository) {
        this.churchEventRepository = churchEventRepository;
    }

    @Override
    public Page<ChurchEvent> findAll(Pageable pr) {
        return churchEventRepository.findAll(pr);
    }

    @Override
    public ChurchEvent findById(int id) {
        Optional<ChurchEvent> result = churchEventRepository.findById(id);
        if(result.isPresent())
            return result.get();

        return null;
    }

    @Override
    public void save(ChurchEvent churchEvent) {
        churchEventRepository.save(churchEvent);
    }

    @Override
    public void remove(ChurchEvent churchEvent) {
        churchEventRepository.delete(churchEvent);
    }

    @Override
    public void remove(int id) {
        churchEventRepository.deleteById(id);
    }
}
