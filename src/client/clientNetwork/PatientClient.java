package client.clientNetwork;

import client.model.clientBookAppointment.ClientPatient;
import com.google.gson.Gson;
import server.model.bookAppointment.Patient;
import server.model.patientJournal.Diagnosis;
import server.model.patientJournal.LabResult;
import server.model.patientJournal.Prescription;
import server.model.patientJournal.Referral;
import server.model.patientJournal.Vaccination;
import shared.RequestObject;
import shared.ResponseObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Client for interacting with the patient management system.
 */
public class PatientClient
{
  private DiagnosisListener listener;
  private VaccinationListener vaccinationListener;

  /**
   * Sets the listener for diagnosis-related events.
   *
   * @param listener the listener to set
   */
  public void setDiagnosisListener(DiagnosisListener listener)
  {
    this.listener = listener;
  }

  /**
   * Sets the listener for vaccination-related events.
   *
   * @param vaccinationListener the listener to set
   */
  public void setVaccinationListener(VaccinationListener vaccinationListener)
  {
    this.vaccinationListener = vaccinationListener;
  }

  /**
   * Retrieves a list of all patients from the server.
   *
   * @return a list of patients, or null if an error occurs
   */
  public List<Patient> getPatientList()
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("patientList");
      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);

      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      return response.getPatients();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Sends a request to add a new diagnosis for a patient.
   *
   * @param diagnosis the diagnosis to add
   */
  public void sendAddDiagnosis(Diagnosis diagnosis)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("addDiagnosis");
      request.setDiagnosis(diagnosis);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        Diagnosis addedDiagnosis = response.getDiagnosis();
        System.out.println(
            "Diagnosis added: " + addedDiagnosis.getDiagnosisName());

        if (listener != null)
        {
          listener.onDiagnosisAdded(true,
              addedDiagnosis.getDiagnosisName() + "is added");
        }
      }

    }
    catch (IOException e)

    {
      e.printStackTrace();
    }
  }

  /**
   * Sends a request to add a new prescription for a patient.
   *
   * @param prescription the prescription to add
   */
  public void sendAddPrescription(Prescription prescription)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("addPrescription");
      request.setPrescription(prescription);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server AddPrescription: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println(
          "Received from server AddPrescription: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        Prescription addedPrescription = response.getPrescription();
        System.out.println(
            "Prescription added: " + addedPrescription.getMedicineName());

        if (listener != null)
        {
          listener.onDiagnosisAdded(true,
              addedPrescription.getMedicineName() + "is added");
        }
      }

    }
    catch (IOException e)

    {
      e.printStackTrace();
    }
  }

  /**
   * Sends a request to add a new lab result for a patient.
   *
   * @param labResult the lab result to add
   */
  public void sendAddLabResult(LabResult labResult)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      System.out.println("LabResult: " + labResult.getTestName() + " "
          + labResult.getPatientId());
      RequestObject request = new RequestObject();
      request.setType("addLabResult");
      request.setLabResult(labResult);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server AddLabResult: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server AddLabResult: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      System.out.println("Response class type: " + response.getClass());
      if (response.isSuccess())
      {
        LabResult addedLabResult = response.getLabResult();
        System.out.println("LabResult added: " + addedLabResult.getTestName());

        if (listener != null)
        {
          listener.onDiagnosisAdded(true,
              addedLabResult.getTestName() + "is added");
        }
      }
      else
      {
        System.out.println(
            "Failed to add lab result: " + response.getMessage());
      }

    }
    catch (IOException e)

    {
      e.printStackTrace();
    }
  }

  /**
   * Sends a request to add a new referral for a patient.
   *
   * @param referral the referral to add
   */
  public void sendAddReferral(Referral referral)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("addReferral");
      request.setReferral(referral);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        Referral addedReferral = response.getReferral();
        System.out.println("Referral added: " + addedReferral.getReason());
        //
        //        if (listener != null)
        //        {
        //          listener.onDiagnosisAdded(true,
        //              addedDiagnosis.getDiagnosisName() + "is added");
        //        }
      }

    }
    catch (IOException e)

    {
      e.printStackTrace();
    }
  }

  /**
   * Retrieves a list of diagnoses for a specific patient.
   *
   * @param id the ID of the patient
   * @return a list of diagnoses, or null if an error occurs
   */
  public List<Diagnosis> getPatientDiagnosis(int id)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("getDiagnosisList");
      request.setId(id);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        return response.getDiagnoses();
      }
      else
      {
        System.err.println(
            "Failed to retrieve diagnoses: " + response.getMessage());
        return null;
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves a list of prescriptions for a specific patient.
   *
   * @param id the ID of the patient
   * @return a list of prescriptions, or null if an error occurs
   */
  public List<Prescription> getPatientPrescriptions(int id)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("getPrescriptionList");
      request.setId(id);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server Prescriptions: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server Prescriptions: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        return response.getPrescriptions();
      }
      else
      {
        System.err.println(
            "Failed to retrieve prescriptions: " + response.getMessage());
        return null;
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves a list of lab results for a specific patient.
   *
   * @param id the ID of the patient
   * @return a list of lab results, or null if an error occurs
   */
  public List<LabResult> getPatientLabResults(int id)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("getLabResultList");
      request.setId(id);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server LabResults: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server LabResults: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        return response.getLabResults();
      }
      else
      {
        System.err.println(
            "Failed to retrieve labResults: " + response.getMessage());
        return null;
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves a list of vaccinations for a specific patient.
   *
   * @param id the ID of the patient
   * @return a list of vaccinations, or null if an error occurs
   */
  public List<Vaccination> getPatientVaccination(int id)
  {
    try (Socket socket = new Socket("localhost", 1234))
    {
      PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));

      Gson gson = new Gson();
      RequestObject request = new RequestObject();
      request.setType("getVaccinationList");
      request.setId(id);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server Vaccinations: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server Vaccinations: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        return response.getVaccinations();
      }
      else
      {
        System.err.println(
            "Failed to retrieve vaccinations: " + response.getMessage());
        return null;
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Retrieves a list of referrals for a specific patient.
   *
   * @param id the ID of the patient
   * @return a list of referrals, or null if an error occurs
   */
  public List<Referral> getPatientReferral(int id)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {

      Gson gson = new Gson();

      RequestObject request = new RequestObject();
      request.setType("getReferralList");
      request.setId(id);

      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server: " + jsonRequest);
      output.println(jsonRequest);

      String jsonResponse = input.readLine();
      System.out.println("Received from server: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        return response.getReferrals();
      }
      else
      {
        System.err.println(
            "Failed to retrieve referrals: " + response.getMessage());
        return null;
      }

    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Sends a request to add a new vaccination for a patient.
   * @param vaccination the vaccination to add
   */
  public void sendAddVaccination(Vaccination vaccination)
  {
    try (Socket socket = new Socket("localhost", 1234);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(
            new InputStreamReader(socket.getInputStream())))
    {
      Gson gson = new Gson();

      // Create and set up the request
      RequestObject request = new RequestObject();
      request.setType("addVaccination");
      request.setVaccination(vaccination);

      // Convert request to JSON and send to server
      String jsonRequest = gson.toJson(request);
      System.out.println("Sending to server AddVaccination: " + jsonRequest);
      output.println(jsonRequest);

      // Read and parse the server response
      String jsonResponse = input.readLine();
      System.out.println(
          "Received from server AddVaccination: " + jsonResponse);

      ResponseObject response = gson.fromJson(jsonResponse,
          ResponseObject.class);

      if (response.isSuccess())
      {
        Vaccination addedVaccination = response.getVaccination();
        System.out.println(
            "Vaccination added: " + addedVaccination.getVaccinationName());
      }
      else
      {
        System.err.println(
            "Failed to add vaccination: " + response.getMessage());
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

}

