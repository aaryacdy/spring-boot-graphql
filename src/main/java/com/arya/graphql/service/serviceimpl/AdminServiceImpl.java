package com.arya.graphql.service.serviceimpl;

import com.arya.graphql.dto.request.AdminRequest;
import com.arya.graphql.dto.response.AdminResponse;
import com.arya.graphql.model.Admin;
import com.arya.graphql.repository.AdminRepository;
import com.arya.graphql.service.AdminService;
import com.arya.graphql.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Response saveAdmin(AdminRequest adminRequest) {
        if (isUserNameUnique(adminRequest.getUsername())) {
            return Response.builder()
                    .success(false)
                    .message("Username is already taken!")
                    .build();
        }

        Admin admin = Admin.builder()
                .name(adminRequest.getName())
                .username(adminRequest.getUsername())
                .email(adminRequest.getEmail())
                .build();

        adminRepository.save(admin);
        return Response.builder()
                .success(true)
                .message("Admin saved successfully!")
                .build();
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream().map(this::toAdminResponse).toList();
    }

    public AdminResponse toAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .name(admin.getName())
                .username(admin.getUsername())
                .email(admin.getEmail())
                .build();
    }

    @Override
    public boolean isUserNameUnique(String name) {
        return adminRepository.existsByUsername(name);
    }
}
