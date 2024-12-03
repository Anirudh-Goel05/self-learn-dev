package org.example;

import org.example.provider.LockProvider;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatBookingService {
    private final int TOTAL_SEATS;
    private final String LOCK_KEY = "lockKey";
    private int currentSeats;
    AtomicInteger totalBooked;
    private final LockProvider lockProvider;

    public SeatBookingService(LockProvider lockProvider, int totalSeats) {
        this.lockProvider = lockProvider;
        this.currentSeats = 0;
        this.TOTAL_SEATS = totalSeats;
        totalBooked = new AtomicInteger(0);
    }

    public void run() throws InterruptedException {
        this.run(5);
    }
    public void run(int numThread) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        for (int i = 1; i <= numThread; i++) {
            int finalI = i;
            executorService.submit(() -> bookSeats(finalI));
        }
        executorService.shutdown();
        executorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    private void bookSeats(int clientId)
    {
        while(true) {
            boolean stopBooking = bookSeat(clientId);
            if(stopBooking) {
                break;
            }
        }
    }

    private boolean bookSeat(int clientId)
    {
        boolean hasLock = false;
        UUID lockId = UUID.randomUUID();
        try {
            hasLock = lockProvider.lock(LOCK_KEY, lockId);
            if(hasLock) {
                if(this.currentSeats < TOTAL_SEATS) {
                    this.currentSeats += 1;
                    totalBooked.incrementAndGet();
                    System.out.println(clientId + " Booking seat number :" + this.currentSeats + " : " + totalBooked.get());
                } else if (this.currentSeats == TOTAL_SEATS){
                    System.out.println(clientId + " All seats booked : " + totalBooked.get());
                    return true;
                } else {
                    System.out.println(clientId + " Danger !!!!!!!!" + totalBooked.get());
                    return true;
                }
            } else {
                // wait
            }
        } catch (Exception e) {

        } finally {
             if(hasLock) {
                 lockProvider.unlock(LOCK_KEY, lockId);
                 System.out.println(clientId + " : Unlocking lock" + this.currentSeats + ": " + lockId.toString());
             }
        }
        return false;
    }
}
