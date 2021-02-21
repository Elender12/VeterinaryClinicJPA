package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import dao.FactoryPersistenceManager;
import dao.TreatmentDAO;
import model.AppliedTreatment;
import model.Treatment;


public class TreatmentService {
		public Map<String, ArrayList<Treatment>> getPetsTreatments(int ownerID) {
			EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
			Map<String, ArrayList<Treatment>> treatmentsList = new TreeMap<String, ArrayList<Treatment>>();
			try {
				em.getTransaction().begin();
				TreatmentDAO treatmentDao = new TreatmentDAO(em);
				List<AppliedTreatment> treatments = treatmentDao.getPetsTreatment(ownerID);
				for (AppliedTreatment appliedTreatment : treatments) {
					if(treatmentsList.containsKey(appliedTreatment.getPatient().getName())) {
						treatmentsList.get(appliedTreatment.getPatient().getName()).add(appliedTreatment.getTreatment());
					}else {
						treatmentsList.put(appliedTreatment.getPatient().getName(),new ArrayList<Treatment>());
						treatmentsList.get(appliedTreatment.getPatient().getName()).add(appliedTreatment.getTreatment());
					}
				}
			}catch(Exception ex) {
				ex.printStackTrace(System.out);
			}finally {
				em.close();
			}
			return treatmentsList;
		}
		
		public Map<String, List<AppliedTreatment>> getPetLastTreatment(int ownerID) {
			EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
			Map<String, List<AppliedTreatment>> treatmentsList = new TreeMap<String, List<AppliedTreatment>>();
			try {
				em.getTransaction().begin();
				TreatmentDAO treatmentDao = new TreatmentDAO(em);
				List<AppliedTreatment> treatments = treatmentDao.getPetLastTreatment(ownerID);
				for (AppliedTreatment appliedTreatment : treatments) {
					if(treatmentsList.containsKey(appliedTreatment.getPatient().getName())) {
						treatmentsList.get(appliedTreatment.getPatient().getName()).add(appliedTreatment);
					}else {
						treatmentsList.put(appliedTreatment.getPatient().getName(),new ArrayList<AppliedTreatment>());
						treatmentsList.get(appliedTreatment.getPatient().getName()).add(appliedTreatment);
					}		
				}
			}catch(Exception ex) {
				ex.printStackTrace(System.out);
			}finally {
				em.close();
			}
			return treatmentsList;
		}
}
