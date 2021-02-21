package dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

	public List<Patient> getAllPatients(int ownerID){
		EntityManager em = this.getEntityManager();	
		TypedQuery<Patient> query = em.createQuery("SELECT p FROM Patient p WHERE p.client.id = :ownerID" , Patient.class);
		List<Patient> petsList = query.setParameter("ownerID", ownerID).getResultList();
		return petsList;
		
	}

	@Override
	public Patient findByDocumentID(String documentID) {
		// TODO Auto-generated method stub
		return null;
	}


}
