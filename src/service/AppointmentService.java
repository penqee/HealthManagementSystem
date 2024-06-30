package service;

import model.Appointment;
import model.User;
import repository.AppointmentRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public Appointment ConvertToAppointment(ResultSet rs){
        try {
            if (rs.next()){
                String appointment_no = rs.getString("appointment_no");
                String user_no = rs.getString("user_no");
                String ap_no = rs.getString("ap_no");
                String appointment_state = rs.getString("appointment_state");

                return new Appointment(appointment_no, user_no, ap_no, appointment_state);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
