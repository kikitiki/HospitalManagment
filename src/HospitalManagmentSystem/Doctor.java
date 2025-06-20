package HospitalManagmentSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;

    private Scanner scanner;

    public  Doctor(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void viewDoctors(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors: ");
            System.out.println("*------------*------------*------------------------*");
            System.out.println("| Doctor id | Name       |Specializaton------------|");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specilazation = resultSet.getString("sprecilazation");
                System.out.printf("|%-12s|%-12s|%-12s|%-12s\n", id,name,specilazation);
                System.out.println("*------------*------------*------------------------*");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getDoctorById(int id){
        String query = "SELECT * from doctors WHERE id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
