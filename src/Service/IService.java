/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Dto.*;
import Entities.*;
import java.util.List;

/**
 *
 * @author user
 */
public interface IService {
    
    public boolean annulerRDV(int id);
    public List<RdvDto> listerRDV(String type);
    public List<Patient> listerPatient();
    public List<RdvDto> filtrer(String date, String type);
    public int plannifierRDV(Rdv rdv);
    public int plannifierConsultation(Consultation consultation);
    public int plannifierPrestation(Prestation prestation);
    public List<RdvDto> getDossierMedical(Patient patient);
    public User seConnecter(String login, String password);
    public int creerCompte(Patient patient);
    public void demanderRDV(Rdv rdv);
    public List<Antecedant> listeAntecedant();
    public int setSante(Sante sante);
    public List<Consultation> consultationsParPatient(int id);
    public Medecin getMedecin(int id);
    public int addConstante(Constante constante);
    public int setDiagnostic(Diagnostic diagnostic);
    public Consultation findConsultation(int id);
    public Patient getPatient(int id);
    public int setRdv(RdvDto rdv);
    
}
