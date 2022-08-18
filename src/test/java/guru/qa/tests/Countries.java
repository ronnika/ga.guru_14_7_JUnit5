package guru.qa.tests;

public enum Countries {
    US("U.S."), India("India"), Mexico("Mexico"), Brazil("Brazil");
    String notation;

    Countries(String notation) {
        this.notation = notation;
    }

    public String getNotation() { return notation; }
}