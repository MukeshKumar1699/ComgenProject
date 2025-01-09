//package com.example.comgenproject;
//
//import android.bluetooth.BluetoothDevice;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class PairedDevicesAdapter extends RecyclerView.Adapter<PairedDevicesAdapter.ViewHolder> {
//
//    private List<BluetoothDeviceItem> pairedDevicesList;
//    private OnDeviceClickListener listener;
//
//    // Constructor
//    public PairedDevicesAdapter(List<BluetoothDeviceItem> pairedDevicesList, OnDeviceClickListener listener) {
//        this.pairedDevicesList = pairedDevicesList;
//        this.listener = listener;
//    }
//
//    // ViewHolder to hold the individual device view
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        public TextView deviceName;
//        public TextView deviceAddress;
//
//        public ViewHolder(View view) {
//            super(view);
//            deviceName = view.findViewById(R.id.deviceName);
//            deviceAddress = view.findViewById(R.id.deviceAddress);
//        }
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        BluetoothDeviceItem device = pairedDevicesList.get(position);
//        holder.deviceName.setText(device.getName());
//        holder.deviceAddress.setText(device.getAddress());
//
//        // Set onClickListener to handle device click
//        holder.itemView.setOnClickListener(v -> listener.onDeviceClick(device));
//    }
//
//    @Override
//    public int getItemCount() {
//        return pairedDevicesList.size();
//    }
//
//    // Interface to handle device clicks
//    public interface OnDeviceClickListener {
//        void onDeviceClick(BluetoothDeviceItem device);
//    }
//}
