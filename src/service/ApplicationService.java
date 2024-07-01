package service;

import model.Application;
import model.Appointment;
import repository.ApplicationRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationService() {
        this.applicationRepository = new ApplicationRepository();
    }
    //增
    public boolean insert(Application application) {
        return applicationRepository.insert(application);
    }
    //删
    public boolean delete(Application application) {
        return applicationRepository.delete(application);
    }
    //改
    public boolean updateByNo(Application application) {
        return applicationRepository.updateByNo(application);
    }
    //查
    public List<Application> select(Application application) {
        return applicationRepository.select(application);
    }


}
