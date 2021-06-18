package android.joystickandroidapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import android.joystickandroidapp.R;
import android.joystickandroidapp.databinding.ActivityMainBinding;
import android.joystickandroidapp.viewModel.MainViewModel;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    Joystick joystick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setViewModel(new MainViewModel());

        joystick = (Joystick) findViewById(R.id.joystick);

        joystick.setJoystickOnChange((a,e)->{
            MainViewModel vm = mainBinding.getViewModel();
            vm.aileronChange(a);
            vm.elevatorChange(e);
        });

        SeekBar rudder = ((SeekBar)findViewById(R.id.rudderSeekBar));

        SeekBar throttle = ((SeekBar)findViewById(R.id.throttleSeekBar));

        rudder.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    float progressChangedValue = 0;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressChangedValue = progress;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mainBinding.getViewModel().rudderChange( progressChangedValue / 20 );
                    }
                }
        );

        throttle.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    float progressChangedValue = 0;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressChangedValue = progress;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        mainBinding.getViewModel().throttleChange(progressChangedValue / 20);
                    }
                }
        );
    }

    @BindingAdapter({"validationMessage"})
    public static void runMe(View view, String message) {
        if (message != null) {
            Toast.makeText(view.getContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}