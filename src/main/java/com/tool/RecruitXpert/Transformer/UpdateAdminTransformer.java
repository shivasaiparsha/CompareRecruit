package com.tool.RecruitXpert.Transformer;

import com.tool.RecruitXpert.Entities.Admin;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAdminTransformer {

    public Admin updateUser(Admin admin)
    {
        Admin admin1= Admin.builder()
                .AdminId(admin.getAdminId())
                .password(admin.getPassword())
                .adminRole(admin.getAdminRole())
                .address(admin.getAddress())
                .adminImg(admin.getAdminImg())
                .email(admin.getEmail())
                .firstname(admin.getFirstname())
                .companyName(admin.getCompanyName())
                .website(admin.getWebsite())
                .lastActive(admin.getLastActive())
                .lastname(admin.getLastname())

                .build();
        return admin1;
    }
}
