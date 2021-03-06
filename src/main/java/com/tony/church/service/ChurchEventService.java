package com.tony.church.service;

import com.tony.church.entity.ChurchEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ChurchEventService {

    public Page<ChurchEvent> findAll(Pageable pr);

    public ChurchEvent findById(int id);

    public void save(ChurchEvent churchEvent);

    public void remove(ChurchEvent churchEvent);

    public void remove(int id);
}
