package tim.com.libnetwork.atest.whatsapp;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

public class RemoveActionShortcuts implements IXposedHookInitPackageResources {
    /**
     * Called when the resources are loaded. Allows us to hook the conversation layout, and
     * modify it on-the-fly.
     */
    @Override
    public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
        if (!resparam.packageName.equals(Utils.WHATSAPP_PACKAGE_NAME)) {
            return;
        }
        
        // Modifies the conversation layout to make the buttons we don't want to be zero pixels
        // by zero pixels.
        resparam.res.hookLayout(Utils.WHATSAPP_PACKAGE_NAME, "layout", "conversation", new XC_LayoutInflated() {
            @Override
            public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
                Utils.debug("Start of Conversation layout inflated callback");
                
                if (Preferences.hasRemoveVoicePreference()) {
                    Utils.debug("User has preference to remove the voice button");
                    try {
                        ImageButton voiceButton = (ImageButton) liparam.view.findViewById(
                                liparam.res.getIdentifier("voice_note_btn", "id", Utils.WHATSAPP_PACKAGE_NAME)
                        );
                        if (null == voiceButton) {
                            Utils.debug("Could not locate the voice button, skipping removal");
                        } else {
                            FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(0, 0);
                            voiceButton.setLayoutParams(frameLayoutParams);
                            Utils.debug("Removed voice button");
                        }
                    } catch (Exception e) {
                        Utils.debug("Failed to hook voice button: " + Log.getStackTraceString(e));
                    }
                } else {
                    Utils.debug("Not removing voice button because that's what the user preference indicated");
                }

                if (Preferences.hasRemoveCameraPreference()) {
                    Utils.debug("User has preference to remove the camera button");
                    try {
                        ImageButton cameraButton = (ImageButton) liparam.view.findViewById(
                                liparam.res.getIdentifier("camera_btn", "id", Utils.WHATSAPP_PACKAGE_NAME)
                        );
                        if (null == cameraButton) {
                            Utils.debug("Could not locate the camera button, skipping removal");
                        } else {
                            LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(0, 0);
                            cameraButton.setLayoutParams(linearLayoutParams);
                            Utils.debug("Removed camera button");
                        }
                    } catch (Exception e) {
                        Utils.debug("Failed to hook camera button: " + Log.getStackTraceString(e));
                    }
                } else {
                    Utils.debug("Not removing camera button because that's what the user preference indicated");
                }
                
                Utils.debug("End of Conversation layout inflated callback");
            }
        }); 
    }
}
