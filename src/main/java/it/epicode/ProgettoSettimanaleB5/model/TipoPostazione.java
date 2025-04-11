package it.epicode.ProgettoSettimanaleB5.model;

public enum TipoPostazione {
    PRIVATO("Postazione privata individuale"),
    OPENSPACE("Area open space condivisa"),
    SALA_RIUNIONI("Sala riunioni attrezzata");

    private final String descrizione;

    TipoPostazione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
