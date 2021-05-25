package revolut;

public class PaymentService {
    private String serviceName;
    private boolean servicePin;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public boolean isServicePin() {
        return servicePin;
    }

    public void setServicePin(boolean servicePin) {
        this.servicePin = servicePin;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean pinApproval(DebitCard card, int cvvPin){

        if(card.getCvvPin() == cvvPin){
            setServicePin(true);
            return true;
        }else{
            setServicePin(false);
            return false;
        }
    }
}
