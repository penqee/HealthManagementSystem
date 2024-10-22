package service;

import model.Appointment;
import repository.AppointmentRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService() {
        this.appointmentRepository = new AppointmentRepository();
    }
    //增
    public boolean insert(Appointment appointment) {
        return appointmentRepository.insert(appointment);
    }
    //删
    public boolean delete(Appointment appointment) {
        return appointmentRepository.delete(appointment);
    }
    //改
    public boolean updateByNo(Appointment appointment) {
        return appointmentRepository.updateByNo(appointment);
    }
    //查
    public List<Appointment> select(Appointment appointment) {
        return appointmentRepository.select(appointment);
    }


}
