package android.joystickandroidapp.viewModel;

import android.joystickandroidapp.BR;
import android.joystickandroidapp.model.FlightSimulatorLogic;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class MainViewModel extends BaseObservable {
    private FlightSimulatorLogic fgLogic;

    public MainViewModel(){
        this.fgLogic = new FlightSimulatorLogic();
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

    public void onClick() {

        this.fgLogic.connect();
    }

    public void rudderChange(float value){
        this.fgLogic.setRudder(value);
    }

    public void throttleChange(float value){
        System.out.println("in vm - throttle " + value);
        this.fgLogic.setThrottle(value);
    }
}
