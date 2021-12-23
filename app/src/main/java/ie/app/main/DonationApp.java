package ie.app.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import ie.app.Database.DBManager;
import ie.app.Model.Donation;

public class DonationApp extends Application
{
    public final int target = 10000;
    public int totalDonated = 0;

    public DBManager dbManager;
    public boolean newDonation(Donation donation)
    {
        boolean targetAchieved = totalDonated > target;
        if (!targetAchieved)
        {
            dbManager.add(donation);
            totalDonated += donation.amount;
        }
        else
        {
            Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT).show();
        }
        return targetAchieved;
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Donate", "Donation App Started");
        dbManager = new DBManager(this);
        Log.v("Donate", "Database Created");
    }
}

