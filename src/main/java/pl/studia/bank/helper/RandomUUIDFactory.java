package pl.studia.bank.helper;

import java.util.Random;
import java.util.UUID;

public enum RandomUUIDFactory {
    INSTANCE;

    private final long MASTER_SEED = 0L;

    private long seed;

    private Random random;


    RandomUUIDFactory(){
        restartSeed();
    }

    public void restartSeed() {
        this.random = new Random(MASTER_SEED);
    }

    public UUID getNextUUID(){

        byte[] randomBytesTable = new byte[40];

        random.nextBytes(randomBytesTable);

        return UUID.nameUUIDFromBytes(randomBytesTable);
    }

}
