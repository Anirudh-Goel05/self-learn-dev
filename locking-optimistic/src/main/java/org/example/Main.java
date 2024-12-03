package org.example;

import org.example.factory.LockProviderFactory;
import org.example.provider.LockProvider;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Entering main");
        LockProvider redisLockProvider = LockProviderFactory.getLockProvider(LockProviderFactory.REDIS);
        SeatBookingService seatBookingService = new SeatBookingService(redisLockProvider, 10000);
        seatBookingService.run();
        System.out.println("Exiting main");
    }
}