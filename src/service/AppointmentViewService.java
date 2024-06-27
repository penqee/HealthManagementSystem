package service;

import model.*;
import repository.*;

import java.sql.*;

public class AppointmentViewService {
    private AppointmentViewRepository appointmentViewRepository;
    public AppointmentViewService() {
        this.appointmentViewRepository = new AppointmentViewRepository();
    }
    public ResultSet select(User user, AccompanyingPerson ap, String appointment_state) {
        return appointmentViewRepository.select(user, ap, appointment_state);
    }
}
