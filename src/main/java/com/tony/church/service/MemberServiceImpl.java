package com.tony.church.service;

import com.tony.church.dao.MemberRepository;
import com.tony.church.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Page<Member> findAll(Pageable pr) {
        return memberRepository.findAll(pr);
    }

    @Override
    public Member findById(int id) {
        Optional<Member> result = memberRepository.findById(id);
        if(result.isPresent())
            return result.get();

        return null;
    }

    @Override
    public List<String> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void remove(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public void remove(int id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<String> search(String keyword) {
        return memberRepository.search(keyword);
    }

    @Override
    public List<String> findAllEmails() {
        return memberRepository.findAllEmails();
    }

    @Override
    public List<String> findWorkersEmails() {
        return memberRepository.findWorkersEmails() ;
    }
}
