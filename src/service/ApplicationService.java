package service;

import model.*;
import repository.*;

import java.sql.ResultSet;
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
    public ResultSet select(Application application) {
        return applicationRepository.select(application);
    }
}
