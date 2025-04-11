package it.epicode.ProgettoSettimanaleB5.utility;

import org.springframework.stereotype.Component;

@Component
public class CodiceUnivocoGenerator {

    private static final String PREFIX = "POST-";
    private static final int CODE_LENGTH = 6;

    public String generaCodice() {
        // Implementazione semplice - in produzione usare un algoritmo più robusto
        long timestamp = System.currentTimeMillis() % 1000000;
        return PREFIX + String.format("%0" + CODE_LENGTH + "d", timestamp);
    }
}