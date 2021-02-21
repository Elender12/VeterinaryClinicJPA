package dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.AppliedTreatment;
import model.AppliedTreatmentPK;


public class TreatmentDAO extends DaoRepository<AppliedTreatment, AppliedTreatmentPK >{
	final static double IVA = 0.21;
	public TreatmentDAO(EntityManager em) {
		super(em);
	}

	public List<AppliedTreatment> getPetsTreatment(int ownerID) {
		EntityManager em = this.getEntityManager();
		String query ="SELECT at from AppliedTreatment as at JOIN at.patient p WHERE at.patient.client.id = :ownerID";
		TypedQuery<AppliedTreatment> qr = em.createQuery(query, AppliedTreatment.class);
		qr.setParameter("ownerID", ownerID);
		return (List<AppliedTreatment>) qr.getResultList();
	}
	
	
	public List<AppliedTreatment> getPetLastTreatment(int ownerID){
		EntityManager em = this.getEntityManager();
		String query ="SELECT at from AppliedTreatment as at JOIN at.patient p JOIN at.treatment t WHERE at.patient.client.id = :ownerID";
		TypedQuery<AppliedTreatment> qr = em.createQuery(query, AppliedTreatment.class);
		qr.setParameter("ownerID", ownerID);
		return (List<AppliedTreatment>) qr.getResultList();
	}
	
	@Override
	public AppliedTreatment find(AppliedTreatmentPK id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppliedTreatment findByDocumentID(String documentID) {
		// TODO Auto-generated method stub
		return null;
	}

}
