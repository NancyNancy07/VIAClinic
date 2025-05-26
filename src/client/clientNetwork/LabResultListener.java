package client.clientNetwork;

/**
 * Interface for listening to diagnosis-related events.
 */
public interface LabResultListener
{
  /**
   * Called when a diagnosis is added.
   *
   * @param success Indicates whether the operation was successful.
   * @param message A message providing additional details about the operation.
   */
  void onLabResultAdded(boolean success, String message);
}
