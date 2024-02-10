package com.tool.RecruitXpert.Service;


import com.tool.RecruitXpert.DTO.AdminDTO.AdminRequest;
import com.tool.RecruitXpert.DTO.AdminDTO.AdminResponse;
import com.tool.RecruitXpert.Entities.Admin;
import com.tool.RecruitXpert.Exceptions.AdminNotFoundException;
import com.tool.RecruitXpert.Repository.AdminRepository;
import com.tool.RecruitXpert.Transformer.AdminTransformer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    ModelMapper modelMapper;
    public AdminResponse addAdmin(AdminRequest adminRequest) {

        Admin admin = AdminTransformer.AdminRequestToAdmin(adminRequest);
        Admin savedAdmin = adminRepository.save(admin);
//        return AdminTransformer.AdminToAdminResponse(savedAdmin);
        return modelMapper.map(adminRequest,AdminResponse.class);
    }

    public String updateAdmin(Long id,AdminRequest adminRequest) {

//        //Optional<Admin> optionalAdmin = adminRepository.findById(id);
//
//       // if(!optionalAdmin.isPresent()) throw new AdminNotFoundException("Admin not Found");
//
//        Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
//        //Admin admin = adminRepository.findById(id).get();
//        //Admin updatedAdmin = modelMapper.map(adminRequest,Admin.class);
//         //adminRepository.updateByAdmin(updatedAdmin)
//         ;
           if(!adminRepository.existsById(id))  throw new AdminNotFoundException("Admin not found with AdminId "+id);
           Admin admin=adminRepository.findById(id).get();

        return "Successfully Updated !!";
    }

    public String deleteAdmin(Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if(!optionalAdmin.isPresent()){
            throw new AdminNotFoundException("Admin not Found");
        }
        adminRepository.deleteById(id);
        return "Successfully Deleted";
    }
}
