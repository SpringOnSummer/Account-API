package org.project.personal.accountapi.utils;

public enum Behavior {
    CREATE("Created"),
    DELETE("Deleted"),
    UPDATE("Updated"),
    READ("Read");

    private final String pastParticiple;

    Behavior(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    @Override
    public String toString() {
        return name();
    }

    public String toPastParticiple(){
        return this.pastParticiple;
    }
}
