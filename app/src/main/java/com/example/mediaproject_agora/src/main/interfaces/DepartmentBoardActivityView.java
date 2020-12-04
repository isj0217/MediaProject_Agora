package com.example.mediaproject_agora.src.main.interfaces;

import com.example.mediaproject_agora.src.main.models.DepartmentBoardResponse;

public interface DepartmentBoardActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    void getSpecificDepartmentBoardSuccess(DepartmentBoardResponse departmentBoardResponse);

    void getFilteredDepartmentPostSuccess(DepartmentBoardResponse departmentBoardResponse);
}