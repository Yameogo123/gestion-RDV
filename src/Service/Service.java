/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dao.*;
import Dto.RdvDto;
import Entities.Antecedant;
import Entities.Constante;
import Entities.Consultation;
import Entities.Diagnostic;
import Entities.Medecin;
import Entities.Patient;
import Entities.Prestation;
import Entities.Rdv;
import Entities.Sante;
import Entities.User;
import java.util.List;

/**
 *
 * @author user
 */
public class Service implements IService{

    private final RdvDAO rdvDao=new RdvDAO();
    private final RpDAO rpDao= new RpDAO();
    private final UserDAO userDao=new UserDAO();
    private final AntecedantDAO anteDao= new AntecedantDAO();
    private final PatientDAO pdao=new PatientDAO();
    private final MedecinDAO mdao= new MedecinDAO();
    private final SanteDAO sdao= new SanteDAO();
    private final ConsultationDAO consDao=new ConsultationDAO();
    private final PrestationDAO presDao= new PrestationDAO();
    private final ConstanteDAO constDao= new ConstanteDAO();
    private final DiagnosticDAO diagDao=new DiagnosticDAO();
    
    //annuler consultation
    @Override
    public boolean annulerRDV(int id) {
        return rdvDao.delete(id)!=0;
    }

    @Override
    public List<RdvDto> listerRDV(String type) {
        return rdvDao.allRdvByType(type);
    }
    
    //lister tous les rdv d'un medecin
    public List<RdvDto> listerAllRDVOfMedecin(int id) {
        return rdvDao.findAllRdvByMedecin(id);
    }
    
    //lister tous les rdv d'un medecin par type
    public List<RdvDto> listerRDVOfMedecinByType(int id, String type) {
        return rdvDao.findRdvByMedecinAndType(id, type);
    }
    
    //filtrer consultation
    @Override
    public List<RdvDto> filtrer(String date, String type) {
        return rdvDao.filtrerParDate(date, type);
    }

    @Override
    public int plannifierRDV(Rdv rdv) {
        return rdvDao.insert(rdv);
    }

    @Override
    public List<RdvDto> getDossierMedical(Patient patient) {
        return rdvDao.rdvByPatient(patient.getId());
    }

    @Override
    public User seConnecter(String login, String password) {
        return userDao.findByLoginPassword(login, password);
    }

    //creer un compte pour le patient
    @Override
    public int creerCompte(Patient patient) {
        return pdao.insert(patient);
    }

    @Override
    public void demanderRDV(Rdv rdv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Antecedant> listeAntecedant() {
        return anteDao.findAll();
    }

    //permet d'affecter les antecedants aux patients
    @Override
    public int setSante(Sante sante) {
        return sdao.insert(sante);
    }

    @Override
    public int plannifierConsultation(Consultation consultation) {
        return rdvDao.addConsultation(consultation);
    }

    @Override
    public int plannifierPrestation(Prestation prestation) {
        return rdvDao.addPrestation(prestation);
    }

    @Override
    public List<Consultation> consultationsParPatient(int id) {
        return consDao.findByIdPatient(id);
    }

    @Override
    public List<Patient> listerPatient() {
        return pdao.findAll();
    }

    @Override
    public Medecin getMedecin(int id) {
        return mdao.findById(id);
    }

    @Override
    public int addConstante(Constante constante) {
        return constDao.insert(constante);
    }

    @Override
    public int setDiagnostic(Diagnostic diagnostic) {
        return diagDao.insert(diagnostic);
    }

    @Override
    public Consultation findConsultation(int id) {
        return consDao.findById(id);
    }

    @Override
    public Patient getPatient(int id) {
        return pdao.findById(id);
    }

    @Override
    public int setRdv(RdvDto rdv) {
        return rdvDao.insert2(rdv);
    }

    

    
    
    
}
