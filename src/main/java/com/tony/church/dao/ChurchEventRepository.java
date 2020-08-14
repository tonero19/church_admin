package com.tony.church.dao;

import com.tony.church.entity.ChurchEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChurchEventRepository extends JpaRepository<ChurchEvent, Integer> {

}
