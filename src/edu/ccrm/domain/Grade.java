package edu.ccrm.domain;

public enum Grade {
    S(10),
    A(9),
    B(8),
    C(7),
    D(6),
    E(5),
    F(0);

    private final int gradePoint;

    Grade(int gradePoint) {
        this.gradePoint = gradePoint;
    }

    public int getGradePoint() {
        return gradePoint;
    }

    @Override
    public String toString() {
        return name() + " (" + gradePoint + ")";
    }

    public static Grade fromScore(int score) {
        if (score >= 90) return S;
        else if (score >= 80) return A;
        else if (score >= 70) return B;
        else if (score >= 60) return C;
        else if (score >= 50) return D;
        else if (score >= 40) return E;
        else return F;
    }
}
