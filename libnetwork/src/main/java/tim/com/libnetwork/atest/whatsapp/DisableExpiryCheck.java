package tim.com.libnetwork.atest.whatsapp;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findClass;
import static de.robv.android.xposed.XposedHelpers.findMethodsByExactParameters;

/**
 * This hook allows us to disable the ticking time bomb that WhatsApp places
 * within their application.
 *
 * When debugging mode is enabled, the code will log the original expiry time
 * so you can also use this to see what your expiry time is going to be.
 */
public class DisableExpiryCheck implements IXposedHookLoadPackage {
    // Useful package constants.
    private static final String WHATSAPP_APP_CLASS = Utils.WHATSAPP_PACKAGE_NAME + ".App";

    /**
     * This is initially called by the Xposed Framework when we register this
     * android application as an Xposed Module.
     */
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        // This method is called once per package, so we only want to apply hooks to WhatsApp.
        if (Utils.WHATSAPP_PACKAGE_NAME.equals(lpparam.packageName)) {
            Class<?> appClass = findClass(WHATSAPP_APP_CLASS, lpparam.classLoader);
            Method[] getExpiryDateMethods = findMethodsByExactParameters(appClass, Date.class);
            Utils.debug("We found " + getExpiryDateMethods.length + " that look like they return an expiry date.");

            if (getExpiryDateMethods.length == 1) {
                Method getExpiryDateMethod = getExpiryDateMethods[0];
                Utils.debug("Method found: " + getExpiryDateMethod.getName());

                XposedBridge.hookMethod(getExpiryDateMethod, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Utils.debug("Start of expiry date fetch method");

                        if (!Preferences.hasDisableExpiryCheck()) {
                            Utils.debug("Not disabling the time bomb due to the users preference");
                            return;
                        }

                        Utils.debug("Original expiry date: " + param.getResult());
                        
                        // Let's tell WhatsApp that this version expires this time next year.
                        Calendar expiry = Calendar.getInstance();
                        expiry.add(Calendar.YEAR, 1);
                        param.setResult(expiry.getTime());

                        Utils.debug("New expiry date: " + param.getResult());

                        Utils.debug("End of expiry date fetch method");
                    }
                });
            }
        }
    }
}
