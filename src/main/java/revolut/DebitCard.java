package revolut;

public class DebitCard {

    private int cvvPin;

    public DebitCard(int cvvPin) {
        this.cvvPin = cvvPin;
    }

    public int getCvvPin() {
        return cvvPin;
    }

    public void setCvvPin(int cvvPin) {
        this.cvvPin = cvvPin;
    }
}
