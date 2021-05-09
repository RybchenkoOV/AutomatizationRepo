package L4_Assertions;
import java.math.*;

public class TriangleArea {
    private int sideA;
    private int sideB;
    private int sideC;

    public int getSideA() {
        return sideA;
    }

    public void setSideA(int sideA) {
        this.sideA = sideA;
    }

    public int getSideB() {
        return sideB;
    }

    public void setSideB(int sideB) {
        this.sideB = sideB;
    }

    public int getSideC() {
        return sideC;
    }

    public void setSideC(int sideC) {
        this.sideC = sideC;
    }

    public TriangleArea(int sideA, int sideB, int sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public static double countArea(int sideA, int sideB, int sideC) throws NegaiveSidesException {
        if (sideA <0 || sideB <0 || sideC <0) throw new NegaiveSidesException();
        double halfP = (sideA + sideB + sideC) / 2;
        double S = (halfP*(halfP-sideA)*(halfP-sideB)*(halfP-sideC));
        return Math.sqrt(S);
    }

}

