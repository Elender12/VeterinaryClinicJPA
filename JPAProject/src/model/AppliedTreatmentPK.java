package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the applied_treatments database table.
 * 
 */
@Embeddable
public class AppliedTreatmentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_treatment", insertable=false, updatable=false)
	private int idTreatment;

	@Column(name="id_patient", insertable=false, updatable=false)
	private int idPatient;

	@Temporal(TemporalType.DATE)
	@Column(name="treatment_date")
	private java.util.Date treatmentDate;

	public AppliedTreatmentPK() {
	}
	public int getIdTreatment() {
		return this.idTreatment;
	}
	public void setIdTreatment(int idTreatment) {
		this.idTreatment = idTreatment;
	}
	public int getIdPatient() {
		return this.idPatient;
	}
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}
	public java.util.Date getTreatmentDate() {
		return this.treatmentDate;
	}
	public void setTreatmentDate(java.util.Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AppliedTreatmentPK)) {
			return false;
		}
		AppliedTreatmentPK castOther = (AppliedTreatmentPK)other;
		return 
			(this.idTreatment == castOther.idTreatment)
			&& (this.idPatient == castOther.idPatient)
			&& this.treatmentDate.equals(castOther.treatmentDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTreatment;
		hash = hash * prime + this.idPatient;
		hash = hash * prime + this.treatmentDate.hashCode();
		
		return hash;
	}
}