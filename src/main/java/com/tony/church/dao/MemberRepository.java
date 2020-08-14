package com.tony.church.dao;

import com.tony.church.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT email FROM Member where email like %:keyword%")
    public List<String> search(@Param("keyword") String keyword);

    @Query("SELECT email FROM Member")
    public List<String> findAllEmails();

    @Query(
            value="select distinct M.email as email from member M inner join members_departments MD on M.id = MD.member_id",
            nativeQuery = true)
    public List<String> findWorkersEmails();

}
