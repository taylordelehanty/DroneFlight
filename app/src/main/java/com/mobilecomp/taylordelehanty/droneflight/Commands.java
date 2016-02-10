package com.mobilecomp.taylordelehanty.droneflight;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Commands extends AppCompatActivity {

    Context context = this;
    private Socket socket;
    private static final int SERVERPORT = 4444;
    private static final String SERVER_IP = "172.23.10.79";
    public static String jsString = "var arDrone = require('ar-drone');\n" +
            "var client = arDrone.createClient();\n" +
            "client.createRepl();\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commands);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context, DroneMap.class);
                startActivity(intent);
            }
        });

        new Thread(new ClientThread()).start();


//        Intent client = new Intent (context, Client.class);
//        startActivity(client);

        Button takeoffButton = (Button) findViewById(R.id.takeoff);
        //
        Button turningButton = (Button) findViewById(R.id.turn);
        Button landingButton = (Button) findViewById(R.id.land);
        Button launch = (Button) findViewById(R.id.launch);
        //
    }

    public void takeoff(View view) {
        //Toast.makeText(this, "Takeoff!", Toast.LENGTH_LONG).show();
        jsString = jsString + "client.takeoff();\n" +
                "client";
    }

    public void turn(View view) {
        jsString = jsString + ".after(5000, function() {\n" +
                "    this.clockwise(0.5);\n" +
                "  })";

    }
    public void land(View view) {
        //Toast.makeText(this, "Landing!", Toast.LENGTH_LONG).show();
        jsString = jsString + ".after(1000, function() {\n" +
                "    this.stop();\n" +
                "    this.land();\n" +
                "  });";

    }

    public void launch(View view) {
        Intent launcher = new Intent (context, JsDisplayActivity.class);
        startActivity(launcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commands, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ClientThread implements Runnable {
        @Override
        public void run() {

            try {
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                socket = new Socket(serverAddr, SERVERPORT);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
