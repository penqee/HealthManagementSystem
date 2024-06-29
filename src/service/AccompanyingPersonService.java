package service;

import model.AccompanyingPerson;
import repository.AccompanyingPersonRepository;

import java.sql.ResultSet;
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
    public ResultSet select(AccompanyingPerson ap) {
        return accompanyingPersonRepository.select(ap);
    }

    // 检查陪诊师是否空闲
    public boolean isStateFree(String ap_no) {
        AccompanyingPerson ap = new AccompanyingPerson(null,null,null,null,null,null);
        ap.setAp_no(ap_no);
        ResultSet rs = accompanyingPersonRepository.select(ap);
        try {
            if (rs != null && rs.next()) {
                String state = rs.getString("ap_state");
                return "空闲".equals(state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
