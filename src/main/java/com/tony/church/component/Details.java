package com.tony.church.component;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Details {

    private List<Integer> selectedDepartments;
    private List<Integer> oldSelectedDepartments;
    private String idsAsSingleString = "";
    private Integer memberId;



    public Details() {
        selectedDepartments = new ArrayList<>();
        oldSelectedDepartments = new ArrayList<>();
    }

    public String getIdsAsSingleString() {
        return idsAsSingleString;
    }

    public void setIdsAsSingleString(String idsAsSingleString) {
        this.idsAsSingleString = idsAsSingleString;
    }

    public List<Integer> getOldSelectedDepartments() {
        return oldSelectedDepartments;
    }

    public void setOldSelectedDepartments(List<Integer> oldSelectedDepartments) {
        this.oldSelectedDepartments = oldSelectedDepartments;
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
