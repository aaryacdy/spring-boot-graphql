package com.arya.graphql.service;

import com.arya.graphql.dto.request.AdminRequest;
import com.arya.graphql.dto.request.ProductRequest;
import com.arya.graphql.dto.response.AdminResponse;
import com.arya.graphql.dto.response.ProductResponse;
import com.arya.graphql.model.Admin;
import com.arya.graphql.util.Response;

import java.util.List;

public interface AdminService {

    Response saveAdmin(AdminRequest adminRequest);

    List<AdminResponse> getAllAdmins();

    boolean isUserNameUnique(String name);

}
