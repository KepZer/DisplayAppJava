/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kepzer.displayappjava;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HDC;
import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author KepLo
 */
public class PixelAsyncDetector implements Runnable{
    private JLabel label;
    private WinUserNative winUser;
    private HDC hdc;
    private WinDef.POINT point;
    public PixelAsyncDetector(JLabel label, WinUserNative winUser) {
        this.label = label;
        hdc = winUser.GetDC(null);
        this.winUser = winUser;
        point = new WinDef.POINT(); 
    }
    

    @Override
    public void run(){
        while(true){
            if(winUser.GetCursorPos(point).booleanValue()){
                int colorref = winUser.GetPixel(hdc,point.x, point.y);
                Color color = new Color(colorref);
                label.setForeground(new Color(color.getBlue(), color.getGreen(), color.getRed()));
                //костыльный фикс того что красный и синий спутаны местами...
            }
        }
    }
}
