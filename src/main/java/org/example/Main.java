package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Entering main");
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        LockProvider inMemoryLockProvider = new InMemoryLockProvider();
        LockProvider faultyLockProvider = new InMemoryLockProvider();
        SeatBookingService seatBookingService = new SeatBookingService(faultyLockProvider, 10);
        seatBookingService.run();
        System.out.println("Exiting main");
    }
}