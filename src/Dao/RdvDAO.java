/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.RdvDto;
import Entities.Consultation;
import Entities.Medecin;
import Entities.Patient;
import Entities.Prestation;
import Entities.Rdv;
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
public class RdvDAO implements IDao<Rdv>{
    private final PatientDAO pdao= new PatientDAO();
    private final MedecinDAO mdao=new MedecinDAO();
    
    private final Database database=new Database();
    private final String SQL_INSERT="INSERT INTO rdv(type,date,id_patient) VALUES(?, ?, ?)";
    private final String SQL_INSERT1="INSERT INTO rdv(type, date,id_patient, libelle) VALUES(?, ?, ?, ?)";
    private final String SQL_FILTRE="SELECT * FROM rdv WHERE date LIKE ? AND type LIKE ?";
    private final String SQL_DELETE="DELETE FROM rdv WHERE id=?";
    private final String SQL_RDV_MEDECIN="SELECT * FROM rdv WHERE id_medecin= ?";
    private final String SQL_RDV_MEDECIN_TYPE="SELECT * FROM rdv WHERE id_medecin= ? and type LIKE ?";
    private final String SQL_ADD_CONSULTATION="INSERT INTO rdv(type,date,id_patient,id_medecin) VALUES (?,?,?,?)";
    private final String SQL_ADD_PRESTATION  ="INSERT INTO rdv(type,date,id_patient, libelle, id_consultation) VALUES (?,?,?,?,?)";
    private final String SQL_DOSSIER="SELECT * FROM rdv WHERE id_patient= ?";
    private final String SQL_ALL_TYPE="SELECT * FROM rdv where type LIKE ?";

    @Override
    public int insert(Rdv rdv) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, rdv.getType());
            database.getPs().setString(2, rdv.getDate());
            database.getPs().setInt(3, rdv.getPatient().getId());
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
    public int update(Rdv t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        int nbreLigne=0;
        database.openConnection();
            database.initPreparedStatement(SQL_DELETE);
        try {
            database.getPs().setInt(1, id);
            nbreLigne=database.executeUpdate(SQL_DELETE);   
        } catch (SQLException ex) {
            Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return nbreLigne;
    }

    @Override
    public List<Rdv> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rdv findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<RdvDto> findAllRdvByMedecin(int id){
        List<RdvDto> rdvs= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_RDV_MEDECIN);
        try{
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_RDV_MEDECIN);
            while(rs.next()){
                Patient patient = pdao.findById(rs.getInt("id_patient"));
                RdvDto rdv= new RdvDto(rs.getInt("id"), rs.getString("date"), rs.getString("type"), patient);  
                rdvs.add(rdv);
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return rdvs;
    }
    
    
    public List<RdvDto> findRdvByMedecinAndType(int id, String type){
        List<RdvDto> rdvs= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_RDV_MEDECIN_TYPE);
        try{
            database.getPs().setInt(1, id);
            database.getPs().setString(2, type);
            ResultSet rs= database.executeSelect(SQL_RDV_MEDECIN_TYPE);
            while(rs.next()){
                Patient patient = pdao.findById(rs.getInt("id_patient"));
                RdvDto rdv= new RdvDto(rs.getInt("id"),rs.getString("date"),rs.getString("type"), patient);
                rdvs.add(rdv);
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return rdvs;
    }
    
    public int addConsultation(Consultation consultation){
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_ADD_CONSULTATION);
        try{
            database.getPs().setString(1, consultation.getType());
            database.getPs().setString(2, consultation.getDate());
            database.getPs().setInt(3, consultation.getPatient().getId());
            database.getPs().setInt(4, consultation.getMedecin().getId());
            database.executeUpdate(SQL_ADD_CONSULTATION);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return id;
    }
    
    public int addPrestation(Prestation prestation){
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_ADD_PRESTATION);
        try{
            database.getPs().setString(1, prestation.getType());
            database.getPs().setString(2, prestation.getDate());
            database.getPs().setInt(3, prestation.getPatient().getId());
            database.getPs().setString(4, prestation.getLibelle());
            if(prestation.getConsultation()!=null){
                database.getPs().setInt(5, prestation.getConsultation().getId());
            }else{
                database.getPs().setObject(5, null);
            }
            database.executeUpdate(SQL_ADD_PRESTATION);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return id;
    }
    
    public List<RdvDto> filtrerParDate(String date, String type){
        List<RdvDto> rdvs= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_FILTRE);
        try{
            database.getPs().setString(1, date);
            database.getPs().setString(2, type);
            ResultSet rs= database.executeSelect(SQL_FILTRE);
            while(rs.next()){
                Patient patient = pdao.findById(rs.getInt("id_patient"));
                RdvDto rdv= new RdvDto(rs.getInt("id"),rs.getString("date"),rs.getString("type"), patient);
                rdv.setLibelle(rs.getString("libelle"));
                rdvs.add(rdv);
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return rdvs;
    }
    
    //recuperer tous les rdv d'un patient
    public List<RdvDto> rdvByPatient(int id){
        List<RdvDto> rdvs= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_DOSSIER);
        try{
            database.getPs().setInt(1, id);
            ResultSet rs= database.executeSelect(SQL_DOSSIER);
            while(rs.next()){
                Patient patient = pdao.findById(id);
                Medecin med=mdao.findById(rs.getInt("id_medecin"));
                RdvDto rdv= new RdvDto(rs.getInt("id"),rs.getString("date"),rs.getString("type"), patient);
                rdv.setLibelle(rs.getString("libelle"));
                if(med!=null){
                    rdv.setMedecin(med);
                }
                rdvs.add(rdv);
            }
        }catch(SQLException ex){
           Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        } 
        return rdvs;
    }
    
    public int insert2(RdvDto rdv) {
        int id=0;
        database.openConnection();
        database.initPreparedStatement(SQL_INSERT1);
        try {
            database.getPs().setString(1, rdv.getType());
            database.getPs().setString(2, rdv.getDate());
            database.getPs().setInt(3, rdv.getPatient().getId());
            database.getPs().setString(4, rdv.getLibelle());
            database.executeUpdate(SQL_INSERT1);
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
    
    public List<RdvDto> allRdvByType(String type){
        List<RdvDto> rdvs= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_ALL_TYPE);
        try {
            database.getPs().setString(1,type);
            ResultSet rs= database.executeSelect(SQL_ALL_TYPE);
            while(rs.next()){
                Patient p= pdao.findById(rs.getInt("id_patient"));
                RdvDto rdv=new RdvDto(rs.getInt("id"), rs.getString("date"), rs.getString("type"), p);
                rdv.setLibelle(rs.getString("libelle"));
                rdvs.add(rdv);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RdvDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return rdvs;
    }
}
