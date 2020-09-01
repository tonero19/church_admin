package com.tony.church.service;

import com.tony.church.entity.Department;
import com.tony.church.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    public Page<Member> findAll(Pageable pr);

    public Member findById(int id);

    public List<String> findByEmail(String email);

    public void save(Member member);

    public void remove(Member member);
    public void remove(int id);

    public List<String> search(String keyword);

    public List<String> findAllEmails();

    public List<String> findWorkersEmails();

}
