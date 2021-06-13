package android.joystickandroidapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.joystickandroidapp.R;
import android.joystickandroidapp.databinding.ActivityMainBinding;
import android.joystickandroidapp.viewModel.MainViewModel;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mainBinding.setViewModel(new MainViewModel());

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
}