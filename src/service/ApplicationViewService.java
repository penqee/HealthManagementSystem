package service;

import model.User;
import repository.ApplicationViewRepository;

import java.sql.ResultSet;

public class ApplicationViewService {
    private ApplicationViewRepository applicationViewRepository;
    public ApplicationViewService() {
        this.applicationViewRepository = new ApplicationViewRepository();
    }

    //��ѯ������Ϣ��ͼ
    public ResultSet select(User user, String ap_type,String application_no, String application_state) {
        return applicationViewRepository.select(user,ap_type,application_no,application_state);
    }
}
