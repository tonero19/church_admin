package com.tony.church.component;

import com.tony.church.entity.Department;
import com.tony.church.entity.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Details {

    private List<Integer> selectedDepartments;
    private Integer memberId;

    public Details() {
        selectedDepartments = new ArrayList<>();
    }

    public List<Integer> getSelectedDepartments() {
        return selectedDepartments;
    }

    public void setSelectedDepartments(List<Integer> selectedDepartments) {
        this.selectedDepartments = selectedDepartments;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
