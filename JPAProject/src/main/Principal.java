package main;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import dao.PatientDAO;
import dao.TreatmentDAO;
import model.Client;
import model.Patient;
import model.Treatment;
import services.ClientService;
import services.PatientService;
import utils.Leer;

public class Principal {
	final static int OPTION_YES = 0;
	final static int OPTION_NO = 1;
	final static int NOT_VALID = -1;
	final static int OPTION_CLIENT = 2;
	final static int OPTION_PET = 3;
	final static int DELETE = 3;
	final static int EDIT = 2;
	final static int REGISTER = 1;
	final static int LIST = 4;
	final static int FIRST_QUERY= 1;
	final static int SECOND_QUERY = 2;
	final static int MANAGE_CLIENT_OPTION = 1;
	final static int MANAGE_PET_OPTION = 2;
	final static int CONSULT_TREATMENT_INFO = 3;
	final static int EXIT = 0;

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String documentID = "";
	//	PatientDAO patientDAO = new PatientDAO();
		TreatmentDAO treatmentDAO = new TreatmentDAO();
		Client client = null;
		ArrayList<Patient> pets = null;
		ClientService clientService = new  ClientService();
		PatientService patientService = new PatientService();
		//TODO meter menu en strings y llamarlos
		int opcion = Leer.pedirEntero(MANAGE_CLIENT_OPTION +". Manage a CLIENT  \n" + MANAGE_PET_OPTION+". Manage a PET \n" + CONSULT_TREATMENT_INFO+". Consult TREATMENT info \n"
				+ EXIT+". Exit");
		while (opcion != 0) {
			switch (opcion) {
			case MANAGE_CLIENT_OPTION:
				int opt = Leer.pedirEntero("Press " + REGISTER + " to register a client, " + EDIT
						+ " to edit a client or " + DELETE + " to delete one.");
				switch (opt) {
				case REGISTER:
					System.out.println("Complete the following information in order to register a new client: ");
					Client newClient = gatherClientData(REGISTER, null);
					clientService.insertClient(newClient);
					break;
				case EDIT:
					documentID = Leer.pedirCadena("Insert client\'s document id: ");
					client = clientService.checkClientByDocumentID(documentID);
					if(client != null) {
						Client editedClient = gatherClientData(EDIT, client);
						clientService.editClient(editedClient);
					}
					client = null;
					break;
				case DELETE:
					documentID = Leer.pedirCadena("Insert client\'s document id: ");
					client = clientService.checkClientByDocumentID(documentID);
					if(client != null) {
						clientService.deleteClient(client);
					}
					client = null;
					break;
				}
				break;
			case MANAGE_PET_OPTION:
				client = null;
				documentID = Leer.pedirCadena("Insert client\'s document id: ");
				client = clientService.checkClientByDocumentID(documentID);
				if (client != null) {
					int petOption = 0;
					petOption = Leer.pedirEntero("Press " + REGISTER + " to register a pet, " + EDIT
							+ " to edit a pet or " + DELETE + " to delete one or "+LIST+ " to list all pets.");
					switch (petOption) {
					case REGISTER:
						System.out.println("ID cliente: "+client.getId());
						Patient newPatient = getPatientData(REGISTER, client, null);
						patientService.insertPatient(newPatient);
						break;
					case EDIT:
						pets = new ArrayList<Patient>();
					//	pets = showAllPets(client, patientDAO);
						if (pets.size() >= 1) {
							int numberPet = Leer.pedirEntero("Insert number of pet you want to edit ");
							while (numberPet > pets.size() + 1 || numberPet < 0) {
								numberPet = Leer.pedirEntero("Insert again the number of pet you want to edit: ");
							}
							if (numberPet <= pets.size() + 1 && numberPet >= 1) {
								Patient patientToUpdate = pets.get(numberPet - 1);
								Patient updatedPatient = getPatientData(EDIT, client, patientToUpdate);
							//	boolean resAction = patientDAO.updatePatient(updatedPatient);
								//showResult(resAction);
							} else {
								System.out.println("Not a valid option ");
							}

						} else {
							System.out.println("There are no pets for this client.");
						}
						break;
					case DELETE:
						pets = new ArrayList<Patient>();
				//		pets = showAllPets(client, patientDAO);
						if (pets.size() >= 1) {
							int number = Leer.pedirEntero("Insert number of pet you want to delete: ");
							while (number > pets.size() + 1 || number < 0) {
								number = Leer.pedirEntero("Insert again the number of pet you want to edit: ");
							}
							if (number <= pets.size() + 1 && number >= 1) {
								
//								boolean deletedPet = patientDAO.deletePatient(pets.get(number - 1).getId());
//								showResult(deletedPet);
							}
						} else {
							System.out.println("There are no pets for this client.");
						}
						break;
					case LIST:
						pets = new ArrayList<Patient>();
					//	pets = showAllPets(client, patientDAO);
						
						break;
					default:
						break;
					}
				} else { //FINAL IF
					System.out.println("Client was not found");
				}
				
				
				break;

			case CONSULT_TREATMENT_INFO:

					Map<String, Treatment> list = new TreeMap<String, Treatment>();
					documentID = Leer.pedirCadena("Insert client\'s document id: ");
					client = new Client();
				//	client = clientDAOImpl.checkClient(documentID);
					if(client != null) {
						int queryNumber = Leer.pedirEntero("Press " + FIRST_QUERY + " to see date and price for the last treatment applied to a client\'s pet and " + SECOND_QUERY
								+ " to see all treatments that all pets had.");
						switch (queryNumber) {
						case FIRST_QUERY:
//							list = treatmentDAO.getLastTreatment(client.getDocumentID());
							if(list.size() > 0) {
								for (String key : list.keySet()) {
									System.out.println( key+ " had "+list.get(key));
								}
							}else {
								System.out.println("This client has no treated pets.");
							}
							break;
						case SECOND_QUERY:
							Map<String, ArrayList<Treatment>> vaccines = new TreeMap<String, ArrayList<Treatment>>();
//							vaccines = treatmentDAO.getPetTreatments(client.getDocumentID());
							if(vaccines.size() > 0) {
								for(String key: vaccines.keySet()) {
									//System.out.println(key+ " had "+vaccines.get(key));
									System.out.println(key+ " had: ");
									for (int i = 0; i < vaccines.get(key).size(); i++) {
										System.out.println(i+1+". "+vaccines.get(key).get(i));
									}
								}
							}else {
								System.out.println("This client has no treated pets.");
							}
						
							break;
						default:
							System.out.println("Please enter a valid option.");
						
						}
					}else {
						System.out.println("Client was not found.");
					}
				break;
	
				
			default:
				Leer.mostrarEnPantalla("Invalid option! \n");
				break;
			}
			opcion = Leer.pedirEntero("Please choose another option: ");
		}

	}

	public static void showResult(boolean result) {
		if (result) {
			Leer.mostrarEnPantalla("The action was completed successfully");
		} else {
			Leer.mostrarEnPantalla("There was en error. Please try again.");
		}
	}

/*	
	
	public static ArrayList<Patient> showAllPets(Client client, PatientService servicePatient) {
		//ArrayList<Patient> pets = servicePatient.
		
		if (pets != null) {
			for (int i = 0; i < pets.size(); i++) {
				Leer.mostrarEnPantalla((i + 1) + " " + pets.get(i).getName()+ " age "+pets.get(i).getAge()+ " weight "+pets.get(i).getWeight());
			}
			return pets;
		}
		return null;
	}
*/
	public static Patient getPatientData(int option, Client client, Patient patient) {
		if (option == REGISTER) {
			Patient newPatient = new Patient();
			newPatient.setClient(client);
			newPatient.setName(Leer.pedirCadena("Pet's name: "));
			newPatient.setWeight(Leer.pedirFloat("Pet's weight: "));
			newPatient.setAge(Leer.pedirEntero("Pet's age: "));
			newPatient.setType(Leer.pedirCadena("Pet type: "));
			newPatient.setBreed(Leer.pedirCadena("Pet's breed: "));
			return newPatient;
		}
		if (option == EDIT) {
			patient.setWeight(Leer.pedirFloat("Pet's weight: "));
			patient.setAge(Leer.pedirEntero("Pet's age: "));
			return patient;
		}
		return null;
	}

	public static Client gatherClientData(int option, Client client) {
		if (option == REGISTER) {
			Client newClient = new Client();
			newClient.setIdDocument(Leer.pedirCadena("Client\'s document id: "));
			newClient.setName(Leer.pedirCadena("Client\'s name: "));
			newClient.setSurname(Leer.pedirCadena("Client\'s surname: "));
			newClient.setAddress(Leer.pedirCadena("Client\'s address: "));
			newClient.setPhone(Leer.pedirCadena("Client\'s phone: "));
			newClient.setZipcode(Leer.pedirEntero("Client\'s zipcode: "));
			newClient.setCity(Leer.pedirCadena("Client\'s city: "));
			return newClient;
		}
		if (option == EDIT) {
			client.setAddress(Leer.pedirCadena("Client\'s address: "));
			client.setPhone(Leer.pedirCadena("Client\'s phone: "));
			client.setZipcode(Leer.pedirEntero("Client\'s zipcode: "));
			client.setCity(Leer.pedirCadena("Client\'s city: "));
			return client;
		}
		return null;
	}
	
}
