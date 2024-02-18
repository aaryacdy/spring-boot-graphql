package com.arya.graphql.controller;

import com.arya.graphql.dto.request.AdminRequest;
import com.arya.graphql.dto.request.ProductRequest;
import com.arya.graphql.dto.response.AdminResponse;
import com.arya.graphql.dto.response.ProductResponse;
import com.arya.graphql.service.AdminService;
import com.arya.graphql.util.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Response> saveAdmin(@RequestBody AdminRequest adminRequest) {
        var response = adminService.saveAdmin(adminRequest);
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AdminResponse> getAllAdmin() {
        return adminService.getAllAdmins();
    }

}
