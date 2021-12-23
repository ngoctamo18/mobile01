    package ie.app.donate;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;



import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ie.app.Model.Donation;
import ie.app.donate.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

    public class MainActivity extends Base {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private EditText amountText;
    private TextView amountTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        /*--------------------------------------------*/
        anhxa();

        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
        amountTotal.setText("$0");


    }


    public void donateButtonPressed (View view)
        {

            String method = "";
            switch (paymentMethod.getCheckedRadioButtonId())
            {
                case R.id.PayPal:
                {
                    method = "PayPal";
                    break;
                }
                case R.id.Direct:
                {
                    method = "Direct";
                }
            }
            int donatedAmount = amountPicker.getValue();
            if (donatedAmount == 0)
            {
                String text = amountText.getText().toString();
                if (!text.equals(""))
                    donatedAmount = Integer.parseInt(text);
            }
            if (donatedAmount > 0)
            {
                app.newDonation(new Donation(donatedAmount, method));
                progressBar.setProgress(app.totalDonated);
                String totalDonatedStr = "$" + app.totalDonated;
                amountTotal.setText(totalDonatedStr);
            }
        }

    private void anhxa()
    {
        donateButton = (Button) findViewById(R.id.donateButton);
        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        amountText = (EditText) findViewById(R.id.paymentAmount);
        amountTotal = (TextView) findViewById(R.id.totalSoFar);
    }

}