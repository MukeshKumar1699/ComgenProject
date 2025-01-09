package com.example.comgenproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlueActivity extends AppCompatActivity {

//    private BluetoothAdapter bluetoothAdapter;
//    private RecyclerView pairedDevicesRecyclerView;
//    private PairedDevicesAdapter pairedDevicesAdapter;
//    private List<BluetoothDevice> pairedDevicesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        pairedDevicesRecyclerView = findViewById(R.id.pairedDevicesRecyclerView);
//        pairedDevicesList = new ArrayList<>();
//
//        // Initialize BluetoothAdapter
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        if (bluetoothAdapter == null) {
//            // Device doesn't support Bluetooth
//            Toast.makeText(this, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        // Check if Bluetooth is enabled
//        if (!bluetoothAdapter.isEnabled()) {
//            // Bluetooth is not enabled, prompt to enable it
//            bluetoothAdapter.enable();
//        }
//
//        // Get paired devices and add to the list
//        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
//        pairedDevicesList.addAll(pairedDevices);
//
//        // Set up RecyclerView with PairedDevicesAdapter
//        pairedDevicesAdapter = new PairedDevicesAdapter(pairedDevicesList, this::onDeviceClick);
//        pairedDevicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        pairedDevicesRecyclerView.setAdapter(pairedDevicesAdapter);
//
//        setContentView(R.layout.activity_blue);


    }

    private void onDeviceClick(BluetoothDevice device) {
        // Handle device click (e.g., connect to the selected device)
//        Toast.makeText(this, "Clicked on: " + device.getName(), Toast.LENGTH_SHORT).show();
        // You can implement the connection logic here.
    }
}
