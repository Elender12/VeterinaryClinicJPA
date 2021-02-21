package services;

import java.util.List;
import javax.persistence.EntityManager;
import dao.FactoryPersistenceManager;
import dao.PatientDAO;
import model.Patient;

public class PatientService {
	public boolean insertPatient(Patient patient) {
		boolean res = false;
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			System.out.println(patient.toString());
			em.getTransaction().begin();
			PatientDAO patientDao = new PatientDAO(em);
			patientDao.create(patient);
			em.getTransaction().commit();
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			res= false;
		} finally {
			em.close();
		}
		return res;
	
	}
	public boolean deletePatient(Patient patient) {
		boolean res = false;
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			PatientDAO patientDao = new PatientDAO(em);
			patientDao.delete(patient);
			em.getTransaction().commit();
			res = true;
		}catch(Exception ex) {
			ex.printStackTrace(System.out);
			res= false;
		}finally {
			em.close();
		}
		return res;
	}
	public boolean editPatient(Patient patient) {
		boolean res = false;
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			PatientDAO patientDao = new PatientDAO(em);
			Patient updatedPatient = patientDao.update(patient);
			System.out.println(updatedPatient.toString());
			em.getTransaction().commit();
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			res= false;
		}finally {
			em.close();
		}
		return res;
	}
	public List<Patient> getAllPatientsByOwnerID(int ownerID){
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		List<Patient> patients = null;
		try {
			em.getTransaction().begin();
			PatientDAO patientDao = new PatientDAO(em);
			patients = patientDao.getAllPatients(ownerID);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}finally {
			em.close();
		}
		return patients;
	}
}
