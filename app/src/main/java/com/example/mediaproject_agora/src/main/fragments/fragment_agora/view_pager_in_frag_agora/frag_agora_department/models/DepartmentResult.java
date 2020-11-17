package com.example.mediaproject_agora.src.main.fragments.fragment_agora.view_pager_in_frag_agora.frag_agora_department.models;

public class DepartmentResult {

    String department_name;
    int status;
    int is_new;

    public DepartmentResult(String department_name, int status, int is_new) {
        this.department_name = department_name;
        this.status = status;
        this.is_new = is_new;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public int getStatus() {
        return status;
    }

    public int getIs_new() {
        return is_new;
    }
}
