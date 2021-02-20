package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the treatments database table.
 * 
 */
@Entity
@Table(name="treatments")
@NamedQuery(name="Treatment.findAll", query="SELECT t FROM Treatment t")
public class Treatment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	private byte isVaccine;

	private float price;

	//bi-directional many-to-one association to AppliedTreatment
	@OneToMany(mappedBy="treatment")
	private List<AppliedTreatment> appliedTreatments;

	public Treatment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsVaccine() {
		return this.isVaccine;
	}

	public void setIsVaccine(byte isVaccine) {
		this.isVaccine = isVaccine;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<AppliedTreatment> getAppliedTreatments() {
		return this.appliedTreatments;
	}

	public void setAppliedTreatments(List<AppliedTreatment> appliedTreatments) {
		this.appliedTreatments = appliedTreatments;
	}

	public AppliedTreatment addAppliedTreatment(AppliedTreatment appliedTreatment) {
		getAppliedTreatments().add(appliedTreatment);
		appliedTreatment.setTreatment(this);

		return appliedTreatment;
	}

	public AppliedTreatment removeAppliedTreatment(AppliedTreatment appliedTreatment) {
		getAppliedTreatments().remove(appliedTreatment);
		appliedTreatment.setTreatment(null);

		return appliedTreatment;
	}

}