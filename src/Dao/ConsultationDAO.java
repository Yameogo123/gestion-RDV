/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Consultation;
import Entities.Medecin;
import Entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConsultationDAO implements IDao<Consultation>{

    private final Database database=new Database();
    private final MedecinDAO mdao=new MedecinDAO();
    private final PatientDAO pdao=new PatientDAO();
    private final String SQL_SELECT_ONE="SELECT * FROM rdv WHERE type LIKE 'CONSULTATION' AND id= ?";
    private final String SQL_SELECT_ONE_BY_PATIENT="SELECT * FROM rdv WHERE type LIKE 'CONSULTATION' AND id_patient= ?";
    private final String SQL_INSERT="INSERT INTO rdv(type,date,id_patient, id_medecin) VALUES('CONSULTATION', ?, ?, ?, ?)";
    
    @Override
    public int insert(Consultation cons) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, cons.getDate());
            database.getPs().setInt(2, cons.getPatient().getId());
            database.getPs().setInt(3, cons.getMedecin().getId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return id;
    }

    @Override
    public int update(Consultation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation findById(int id) {
        Consultation consultation=null;
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_ONE);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_SELECT_ONE);
            if(rs.next()){
                Medecin medecin= mdao.findById(rs.getInt("id_medecin"));
                Patient patient= pdao.findById(rs.getInt("id_patient"));
                consultation=new Consultation(medecin, rs.getInt("id"),rs.getString("date"), patient);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return consultation;
    }
    
    public List<Consultation> findByIdPatient(int id) {
        List<Consultation> consultations=new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_ONE_BY_PATIENT);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_SELECT_ONE_BY_PATIENT);
            while(rs.next()){
                Medecin medecin= mdao.findById(rs.getInt("id_medecin"));
                Patient patient= pdao.findById(rs.getInt("id_patient"));
                Consultation consultation=new Consultation(medecin, rs.getInt("id"),rs.getString("date"), patient);
                consultations.add(consultation);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return consultations;
    }
    
}
