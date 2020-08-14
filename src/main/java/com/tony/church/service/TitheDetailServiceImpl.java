package com.tony.church.service;

import com.tony.church.dao.TitheDetailRepository;
import com.tony.church.entity.Department;
import com.tony.church.entity.TitheDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitheDetailServiceImpl implements  TitheDetailService {

    @Autowired
    TitheDetailRepository titheDetailRepository;


    @Override
    public List<TitheDetail> findAll() {

        return titheDetailRepository.findAll();
    }

    @Override
    public TitheDetail findById(int id) {
        Optional<TitheDetail> result = titheDetailRepository.findById(id);
        if(result.isPresent())
            return result.get();

        return null;
    }

    @Override
    public void save(TitheDetail titheDetail) {
        titheDetailRepository.save(titheDetail);
    }

    @Override
    public void remove(TitheDetail titheDetail) {
        titheDetailRepository.delete(titheDetail);
    }
}
