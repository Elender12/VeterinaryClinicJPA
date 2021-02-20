package services;

import javax.persistence.EntityManager;
import dao.FactoryPersistenceManager;
import dao.PatientDAO;
import model.Patient;

public class PatientService {
	public void insertPatient(Patient patient) {
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			System.out.println(patient.toString());
			em.getTransaction().begin();
			PatientDAO patientDao = new PatientDAO(em);
			patientDao.create(patient);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} finally {
			em.close();
		}
	}
	public void deletePatient(Patient patient) {
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			PatientDAO patientDao = new PatientDAO(em);
			patientDao.delete(patient);
			em.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	
}
