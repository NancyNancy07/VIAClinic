package client.clientNetwork;

import server.model.patientJournal.Diagnosis;

/**
 * Listener interface for handling diagnosis-related events.
 */
public interface DiagnosisListener
{
  /**
   * Called when a diagnosis is added.
   *
   * @param success Indicates whether the operation was successful.
   * @param message A message providing additional details about the operation.
   */
  void onDiagnosisAdded(boolean success, String message);
}