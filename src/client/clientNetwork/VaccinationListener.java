package client.clientNetwork;

/**
 * Interface for listening to vaccination-related events.
 */
public interface VaccinationListener
{
    /**
     * Called when a vaccination is added successfully or fails.
     * @param success indicates if the vaccination was added successfully
     * @param message provides additional information about the operation
     */
    void onVaccinationAdded(boolean success, String message);
}
