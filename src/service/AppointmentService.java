package service;

import model.Appointment;
import repository.AppointmentRepository;

import java.sql.ResultSet;

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
    public ResultSet select(Appointment appointment) {
        return appointmentRepository.select(appointment);
    }
}
