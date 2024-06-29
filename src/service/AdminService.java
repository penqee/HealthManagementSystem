package service;

import model.Admin;
import repository.AdminRepository;


import java.sql.ResultSet;

public class AdminService {
    private AdminRepository adminRepository;

    public AdminService() {
        adminRepository = new AdminRepository();
    }

    //²é
    public ResultSet select(Admin admin) {
        return adminRepository.select(admin);
    }
}
