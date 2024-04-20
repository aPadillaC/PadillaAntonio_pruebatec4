package com.hackaboss.agenciaTurismo.service;

import com.hackaboss.agenciaTurismo.model.Flight;
import com.hackaboss.agenciaTurismo.model.Client;
import com.hackaboss.agenciaTurismo.repository.FlightRepository;
import com.hackaboss.agenciaTurismo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ClientRepository clientRepository;

    public void createFlightAndAddClients() {
        // Create a new flight
        Flight flight = new Flight();
        flight.setFlightCode("FL1");
        flight.setOrigin("City1");
        flight.setDestination("City2");
        flight.setSeatType("Economy");
        flight.setFlightPrice(100.0);
        flight.setDate(LocalDate.now());
        flight.setAvailableSeats(5);
        flight.setClientList(new ArrayList<>());


        // Save the flight
        flight = flightRepository.save(flight);

        //Create and add 5 clients to the flight
        for (int i = 1; i <= 5; i++) {
            Client client = new Client();

            client.setName("Client" + i);
            client.setLastName("LastName" + i);
            client.setNif("NIF" + i);
            client.setEmail("client" + i + "@example.com");


             //Save the client
            client = clientRepository.save(client);

             //Add the client to the flight
            flight.addClient(client);
        }

        // Update the flight
        flightRepository.save(flight);
    }

    public void deleteFlight() {

        Flight flight = flightRepository.findById(102).get();

        List<Client> flightClients = flight.getClientList();
        List<Client> clientsToRemove = new ArrayList<>();

        for (Client client : flightClients) {
            if (client.getId() % 2 == 0) {
                clientsToRemove.add(client);
            }
        }

        for (Client client : clientsToRemove) {
            flight.removeClient(client);
        }

        flightRepository.save(flight);
    }
}
