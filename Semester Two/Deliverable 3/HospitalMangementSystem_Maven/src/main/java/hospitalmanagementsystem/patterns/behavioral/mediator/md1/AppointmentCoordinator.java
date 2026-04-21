package hospitalmanagementsystem.patterns.behavioral.mediator.md1;

/**
 * AppointmentCoordinator — ConcreteMediator in Mediator pattern MD1
 * Centralizes all coordination logic for appointment events.
 * Colleagues never call each other directly — all communication
 * passes through this class, eliminating inter-component coupling.
 * Author: [ANDREW]
 */
public class AppointmentCoordinator implements AppointmentMediator {

    private NotificationComponent notificationComponent;
    private BillingComponent billingComponent;
    private DoctorScheduleComponent doctorScheduleComponent;

    /**
     * Registers the notification colleague with this mediator.
     *
     * @param nc the NotificationComponent
     */
    public void setNotificationComponent(NotificationComponent nc) {
        this.notificationComponent = nc;
    }

    /**
     * Registers the billing colleague with this mediator.
     *
     * @param bc the BillingComponent
     */
    public void setBillingComponent(BillingComponent bc) {
        this.billingComponent = bc;
    }

    /**
     * Registers the doctor schedule colleague with this mediator.
     *
     * @param ds the DoctorScheduleComponent
     */
    public void setDoctorScheduleComponent(DoctorScheduleComponent ds) {
        this.doctorScheduleComponent = ds;
    }

    /**
     * Receives an event from a colleague and coordinates the reaction
     * of all other registered colleagues.
     *
     * <p>Event handling rules:
     * <ul>
     *   <li>APPOINTMENT_BOOKED: send confirmation, create billing entry, block doctor slot</li>
     *   <li>APPOINTMENT_CANCELLED: send cancellation, reverse billing, release doctor slot</li>
     * </ul>
     *
     * @param sender        the colleague that triggered the event
     * @param event         the event name
     * @param appointmentId the relevant appointment ID
     */
    @Override
    public void notify(AppointmentColleague sender, String event, String appointmentId) {
        System.out.println("[Coordinator] Received event: " + event
                + " from " + sender.getClass().getSimpleName()
                + " for appointment " + appointmentId);

        if ("APPOINTMENT_BOOKED".equals(event)) {
            // Coordinate all post-booking reactions
            notificationComponent.sendConfirmation(appointmentId);
            billingComponent.createBillingEntry(appointmentId);
            doctorScheduleComponent.blockSlot(appointmentId);

        } else if ("APPOINTMENT_CANCELLED".equals(event)) {
            // Coordinate all post-cancellation reactions
            notificationComponent.sendCancellation(appointmentId);
            billingComponent.reverseBillingEntry(appointmentId);
            doctorScheduleComponent.releaseSlot(appointmentId);

        } else {
            System.out.println("[Coordinator] Unknown event: " + event + " — no action taken.");
        }
    }
}
