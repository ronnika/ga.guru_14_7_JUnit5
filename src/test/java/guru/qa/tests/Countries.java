package guru.qa.tests;

public enum Countries {
    US("U.S."), INDIA("India"), MEXICO("Mexico"), BRAZIL("Brazil");
    String notation;

    Countries(String notation) {
        this.notation = notation;
    }

    public String getNotation() { return notation; }
}