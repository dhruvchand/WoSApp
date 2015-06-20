package com.red_folder.phonegap.plugin.backgroundservice.sample;

public interface AccelerometerListener {
      
    public void onAccelerationChanged(float x, float y, float z);
  
    public void onShake(float force);
  
}