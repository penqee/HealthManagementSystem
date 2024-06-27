package service;

import model.*;
import repository.*;

import java.sql.*;

public class ApplicationViewService {
    private ApplicationViewRepository applicationViewRepository;
    public ApplicationViewService() {
        this.applicationViewRepository = new ApplicationViewRepository();
    }

    //查询申请信息视图
    public ResultSet select(User user, String ap_type, String application_state) {
        return applicationViewRepository.select(user,ap_type,application_state);
    }
}
