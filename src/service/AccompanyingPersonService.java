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


    public AccompanyingPerson ConvertToAP(ResultSet rs){
        try {
            if (rs.next()){
                String uid = rs.getString("ap_no");
                String password = rs.getString("ap_password");
                String ap_name = rs.getString("ap_name");
                String ap_phone_number = rs.getString("ap_phone_number");
                String ap_type = rs.getString("ap_type");
                String ap_state = rs.getString("ap_state");

                return new AccompanyingPerson(uid, password, ap_name, ap_phone_number, ap_type, ap_state);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
