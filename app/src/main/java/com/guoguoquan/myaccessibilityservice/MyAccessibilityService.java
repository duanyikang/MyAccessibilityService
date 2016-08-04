package com.guoguoquan.myaccessibilityservice;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * @author 小段果果
 * @time 2016/8/4  11:12
 * @E-mail duanyikang@mumayi.com
 */

public class MyAccessibilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        AccessibilityNodeInfo rootLayout = getRootInActiveWindow();



        if (event.getPackageName().equals("com.android.packageinstaller")) {
            List<AccessibilityNodeInfo> installButtons = rootLayout.findAccessibilityNodeInfosByText("安装");
            if (installButtons != null) {
                for (AccessibilityNodeInfo temp : installButtons) {
                    temp.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }

            List<AccessibilityNodeInfo> nextButtons = rootLayout.findAccessibilityNodeInfosByText("下一步");
            if (installButtons != null) {
                for (AccessibilityNodeInfo temp : installButtons) {
                    temp.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }

    }

    @Override
    public void onInterrupt() {

    }


}

