package com.smart_cities.citizen.scheduler;

import com.smart_cities.citizen.model.Citizen;
import com.smart_cities.citizen.repository.CitizenRepository;
import com.smart_cities.citizen.service.ProviderNotifier;
import com.smart_cities.citizen.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitizenReadingScheduler {
    private final ProviderNotifier providerNotifier;
    private final CitizenRepository citizenRepository;
    private final ReadingService readingService;
    private List<Citizen> citizens = new ArrayList<>();

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void setCitizens(List<Citizen> citizens) {
        this.citizens = citizens;
    }

    @Autowired
    public CitizenReadingScheduler(ProviderNotifier providerNotifier,
                                   CitizenRepository citizenRepository,
                                   ReadingService readingService) {
        this.providerNotifier = providerNotifier;
        this.citizenRepository = citizenRepository;
        this.readingService = readingService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.setCitizens(this.citizenRepository.findAll());
    }

    @Scheduled(fixedRate = 5000)
    public void generateAndNotify() {
        for (Citizen citizen : this.getCitizens()) {
            this.providerNotifier.notify(
                    this.readingService.createReading(citizen).toPostPayload(),
                    citizen.getProviderId()
            );
        }
    }
}
