package client.clientNetwork;

import server.model.patientJournal.Prescription;

/**
 * Interface for listening to prescription events.
 * This interface defines methods to handle the addition of prescriptions.
 */
public interface PrescriptionListener
{
  /**
   * Called when a prescription is added.
   *
   * @param success Indicates whether the prescription was added successfully.
   * @param message A message providing additional information about the operation.
   */
  void onPrescriptionAdded(boolean success, String message);
  /**
   * Called when a prescription is added to the system.
   *
   * @param prescription The prescription that was added.
   */
  void addedPrescription(Prescription prescription);
}
