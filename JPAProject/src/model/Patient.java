package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the patients database table.
 * 
 */
@Entity
@Table(name="patients")
@NamedQuery(name="Patient.findAll", query="SELECT p FROM Patient p where p.client.id = :ownerID")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int age;

	private String breed;

	private String name;

	private String type;

	private float weight;

	//bi-directional many-to-one association to AppliedTreatment
	@OneToMany(mappedBy="patient")
	private List<AppliedTreatment> appliedTreatments;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Client client;

	public Patient() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBreed() {
		return this.breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getWeight() {
		return this.weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public List<AppliedTreatment> getAppliedTreatments() {
		return this.appliedTreatments;
	}

	public void setAppliedTreatments(List<AppliedTreatment> appliedTreatments) {
		this.appliedTreatments = appliedTreatments;
	}

	public AppliedTreatment addAppliedTreatment(AppliedTreatment appliedTreatment) {
		getAppliedTreatments().add(appliedTreatment);
		appliedTreatment.setPatient(this);

		return appliedTreatment;
	}

	public AppliedTreatment removeAppliedTreatment(AppliedTreatment appliedTreatment) {
		getAppliedTreatments().remove(appliedTreatment);
		appliedTreatment.setPatient(null);

		return appliedTreatment;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", age=" + age + ", breed=" + breed + ", name=" + name + ", type=" + type
				+ ", weight=" + weight + ", client id: " + client.getId() + "]";
	}
	

}