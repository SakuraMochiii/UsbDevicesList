package com.cloudpos.usbdeviceslist;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UsbManager mUsbManager;
    private RecyclerView mRecyclerView;
    private List<UsbDevice> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        initView();
        findUsbDevices();
    }

    private void initView() {
        mRecyclerView = this.findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
    }

    private void findUsbDevices() {
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        if (!mList.isEmpty()) {
            mList.clear();
        }
        if (!deviceList.values().isEmpty()) {
            for (UsbDevice usbDevice : deviceList.values()) {
                mList.add(usbDevice);
            }
        }
        RecyclerAdapter adapter = new RecyclerAdapter(mList);
        mRecyclerView.setAdapter(adapter);
    }
}