
package com.kepzer.displayappjava;

import com.sun.jna.Library;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;


public interface WinUserNative extends Library{
    BOOL SystemParametersInfoA(
        int  uiAction,
        int  uiParam,
        String pvParam,
        int  fWinIni
      );
    int GetPixel(
        HDC hdc,
        int x,
        int y
      );
    HDC GetDC(
        HWND hWnd
      );
    BOOL GetCursorPos(
        POINT lpPoint
    );
    BOOL EnumDisplaySettingsA(
        String   lpszDeviceName,
        int   iModeNum,
        DEVMODEstruct lpDevMode
    );
}


