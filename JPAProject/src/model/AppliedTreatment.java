package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the applied_treatments database table.
 * 
 */
@Entity
@Table(name="applied_treatments")
@NamedQuery(name="AppliedTreatment.findAll", query="SELECT a FROM AppliedTreatment a")
public class AppliedTreatment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AppliedTreatmentPK id;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="id_patient")
	private Patient patient;

	//bi-directional many-to-one association to Treatment
	@ManyToOne
	@JoinColumn(name="id_treatment")
	private Treatment treatment;

	public AppliedTreatment() {
	}

	public AppliedTreatmentPK getId() {
		return this.id;
	}

	public void setId(AppliedTreatmentPK id) {
		this.id = id;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Treatment getTreatment() {
		return this.treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	@Override
	public String toString() {
		return "AppliedTreatment [id=" + id.getIdPatient()+" "+id.getIdTreatment() + ", patient=" + patient.getName() + ", treatment=" + treatment.getDescription() + " owner: "+patient.getClient().getName();
	}

}