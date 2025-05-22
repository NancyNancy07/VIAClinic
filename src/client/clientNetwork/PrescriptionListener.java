package client.clientNetwork;

import server.model.patientJournal.Prescription;

public interface PrescriptionListener
{
  void onPrescriptionAdded(boolean success, String message);
  void addedPrescription(Prescription prescription);
}
