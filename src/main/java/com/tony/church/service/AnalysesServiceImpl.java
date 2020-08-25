package com.tony.church.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class AnalysesServiceImpl implements AnalysesService {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Object[]> nativeQuery(String query) {
        Query q = entityManager.createNativeQuery(query);

        List<Object[]> result = q.getResultList();

        return  result;
    }
}
