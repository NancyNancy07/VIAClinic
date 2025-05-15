package client.clientNetwork;

import server.model.patientJournal.Diagnosis;

public interface DiagnosisListener
{
  void onDiagnosisAdded(boolean success, String message);
}