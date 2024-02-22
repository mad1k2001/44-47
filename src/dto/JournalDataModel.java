package dto;

import entities.Journal;
import java.util.List;

public class JournalDataModel {
    private List<Journal> journals;

    public JournalDataModel(List<Journal> journals) {
        this.journals = journals;
    }

    public List<Journal> getJournals() {
        return journals;
    }
}
