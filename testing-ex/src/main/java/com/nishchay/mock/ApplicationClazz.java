package com.nishchay.mock;

public class ApplicationClazz {

    private DatabaseDAO database;
    private NetworkDAO network;

    public ApplicationClazz(DatabaseDAO database, NetworkDAO network) {
        this.database = database;
        this.network = network;
    }

    public DatabaseDAO getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseDAO database) {
        this.database = database;
    }

    public NetworkDAO getNetwork() {
        return network;
    }

    public void setNetwork(NetworkDAO network) {
        this.network = network;
    }

    public boolean save(String fileName) {
        database.save(fileName);
        System.out.println("Application saved file in database");

        network.save(fileName);
        System.out.println("Application saved file in network");

        return true;
    }
}