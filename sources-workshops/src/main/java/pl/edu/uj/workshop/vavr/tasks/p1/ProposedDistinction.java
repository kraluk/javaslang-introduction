package pl.edu.uj.workshop.vavr.tasks.p1;

final class ProposedDistinction {

    private final String name;

    private final Integer currentYear;

    private final Double averageGrade;

    private final Integer numberOfReprimands;

    ProposedDistinction(String name, Integer currentYear, double averageGrade, int numberOfReprimands) {
        this.name = name;
        this.currentYear = currentYear;
        this.averageGrade = averageGrade;
        this.numberOfReprimands = numberOfReprimands;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public Integer getNumberOfReprimands() {
        return numberOfReprimands;
    }
}
