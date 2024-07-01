package service;

import model.AccompanyingPerson;
import model.User;
import repository.AccompanyingPersonRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccompanyingPersonService {
    private AccompanyingPersonRepository accompanyingPersonRepository;

    public AccompanyingPersonService() {
        this.accompanyingPersonRepository = new AccompanyingPersonRepository();
    }
    //增
    public boolean insert(AccompanyingPerson ap) {
        return accompanyingPersonRepository.insert(ap);
    }
    //删
    public boolean delete(AccompanyingPerson ap) {
        return accompanyingPersonRepository.delete(ap);
    }
    //改
    public boolean updateByNo(AccompanyingPerson ap) {
        return accompanyingPersonRepository.updateByNo(ap);
    }
    //查
    public List<AccompanyingPerson> select(AccompanyingPerson ap) {
        return accompanyingPersonRepository.select(ap);
    }


}
