package com.tony.church.service;

import com.tony.church.entity.Department;
import com.tony.church.entity.Member;

import java.util.List;

public interface MemberService {
    public List<Member> findAll();

    public Member findById(int id);

    public void save(Member member);

    public void remove(Member member);
    public void remove(int id);

    public List<String> search(String keyword);

    public List<String> findAllEmails();

    public List<String> findWorkersEmails();

}
