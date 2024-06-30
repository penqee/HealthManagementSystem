package service;

import model.ApplicationView;
import model.User;
import repository.ApplicationViewRepository;

import java.util.List;

public class ApplicationViewService {
    private ApplicationViewRepository applicationViewRepository;
    public ApplicationViewService() {
        this.applicationViewRepository = new ApplicationViewRepository();
    }

    //��ѯ������Ϣ��ͼ
    public List<ApplicationView> select(User user, String ap_type, String application_no, String application_state) {
        return applicationViewRepository.select(user,ap_type,application_no,application_state);
    }
}
