package service;

import model.AccompanyingPerson;
import model.User;
import repository.AppointmentViewRepository;

import java.sql.ResultSet;

public class AppointmentViewService {
    private AppointmentViewRepository appointmentViewRepository;
    public AppointmentViewService() {
        this.appointmentViewRepository = new AppointmentViewRepository();
    }
    public ResultSet select(User user, AccompanyingPerson ap, String appointment_no,String appointment_state) {
        return appointmentViewRepository.select(user, ap, appointment_no, appointment_state);
    }
}
