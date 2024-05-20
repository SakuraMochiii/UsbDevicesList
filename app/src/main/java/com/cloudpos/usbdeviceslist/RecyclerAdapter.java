package com.cloudpos.usbdeviceslist;

import android.hardware.usb.UsbDevice;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<UsbDevice> mDeviceList;

    public RecyclerAdapter(List<UsbDevice> deviceList) {
        mDeviceList = deviceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsbDevice device = mDeviceList.get(position);
        Log.d("deviceInfo", device.toString());
        String info = "UsbName=" + device.getDeviceName() + "\ngetVersion=" + device.getVersion() + "\nmProductName=" +
                device.getProductName() + "\ngetDeviceId=" + device.getDeviceId() + "\ngetVendorId=" + device.getVendorId()
                + "\ngetProductId=" + device.getProductId() + "\ngetDeviceProtocol=" + device.getDeviceProtocol();
        holder.info.setText(info);
    }

    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView info;

        public ViewHolder(View view) {
            super(view);
            info = view.findViewById(R.id.device_info);
        }

    }
}
