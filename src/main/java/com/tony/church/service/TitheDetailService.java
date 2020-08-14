package com.tony.church.service;

import com.tony.church.entity.Department;
import com.tony.church.entity.TitheDetail;

import java.util.List;

public interface TitheDetailService {

    public List<TitheDetail> findAll();

    public TitheDetail findById(int id);

    public void save(TitheDetail titheDetail);

    public void remove(TitheDetail titheDetail);
}
