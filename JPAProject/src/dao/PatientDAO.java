package dao;


import java.util.ArrayList;
import javax.persistence.EntityManager;
import model.Patient;

public class PatientDAO extends DaoRepository<Patient, Integer>{

	public PatientDAO(EntityManager em) {
		super(em);
	}

	@Override
	public Patient find(Integer id) {
		EntityManager em = this.getEntityManager();
		return em.find(Patient.class, id);
	}

	@Override
	public Patient create(Patient entity) {
		return super.create(entity);
	}

	@Override
	public Patient update(Patient entity) {
		return super.update(entity);
	}

	@Override
	public void delete(Patient entity) {
		super.delete(entity);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Patient> getAllPatients(int ownerID){
		ArrayList<Patient> pets = null;
		EntityManager em = this.getEntityManager();	
		return (ArrayList<Patient>) em.createNamedQuery("Patient.findAll").setParameter("ownerID", ownerID);
		
	}

	@Override
	public Patient findByDocumentID(String documentID) {
		// TODO Auto-generated method stub
		return null;
	}


}
