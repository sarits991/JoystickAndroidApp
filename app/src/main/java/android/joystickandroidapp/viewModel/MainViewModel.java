package android.joystickandroidapp.viewModel;

import android.joystickandroidapp.BR;
import android.joystickandroidapp.model.FlightSimulatorLogic;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.concurrent.Future;


public class MainViewModel extends BaseObservable {
    private FlightSimulatorLogic fgLogic;

    private String validationMessage;

    public MainViewModel(){
        this.fgLogic = new FlightSimulatorLogic();
        this.validationMessage = "";
    }

    @Bindable
    public String getValidationMessage() {
        return validationMessage;
    }

    @Bindable
    public String getIp(){
        return this.fgLogic.getFgProperties().getIp();
    }

    @Bindable
    public String getPort(){
        return this.fgLogic.getFgProperties().getPort();
   }

    public void setIp(String ip){
        this.fgLogic.getFgProperties().setIp(ip);
        notifyPropertyChanged(BR.ip);
    }

    public void setPort(String port){
        this.fgLogic.getFgProperties().setPort(port);
        notifyPropertyChanged(BR.port);
    }

    public void setValidationMessage(String toastMessage) {
        this.validationMessage = toastMessage;
        notifyPropertyChanged(BR.validationMessage);
    }

    public void isConnectClick() {

        if(!isDataValid()) {
            setValidationMessage("Invalid data");
            return;
        }
        setValidationMessage("try to connect");
        Future<Boolean> f = this.fgLogic.connect();
        try{
            Boolean isConnect = f.get();
            if(isConnect) {
                setValidationMessage("connect successfully");
            }
        }
        catch (Exception e) {
            setValidationMessage("failed to connect");
        }
    }

    public boolean isDataValid(){
        if(this.fgLogic.getFgProperties().getPort().isEmpty() || this.fgLogic.getFgProperties().getIp().isEmpty())
        {
            return false;
        }
        return true;
    }

    public void rudderChange(float value){
        this.fgLogic.setRudder(value);
    }

    public void throttleChange(float value){
        this.fgLogic.setThrottle(value);
    }

    public void aileronChange(float value){
        this.fgLogic.setAileron(value);
    }

    public void elevatorChange(float value){
        this.fgLogic.setElevator(value);
    }
}
