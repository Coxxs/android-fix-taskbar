package xp.taskbarmod

import android.content.res.XResources.DimensionReplacement
import android.util.TypedValue
import de.robv.android.xposed.*
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam


class Module : /* IXposedHookLoadPackage,*/ IXposedHookInitPackageResources {

    @Throws(Throwable::class)
    override fun handleInitPackageResources(resparam: InitPackageResourcesParam) {
        val packageName = resparam.packageName

        if (packageName == "com.google.android.apps.nexuslauncher") {
            if (BuildConfig.TASKBAR_HIDE) {
                resparam.res.setReplacement(
                    packageName,
                    "dimen",
                    "taskbar_stashed_size",
                    DimensionReplacement(0F, TypedValue.COMPLEX_UNIT_DIP)
                )

                resparam.res.setReplacement(
                    packageName,
                    "dimen",
                    "taskbar_stashed_handle_height",
                    DimensionReplacement(0F, TypedValue.COMPLEX_UNIT_DIP)
                )
            }

            resparam.res.setReplacement(
                packageName,
                "dimen",
                "taskbar_size",
                DimensionReplacement(60F, TypedValue.COMPLEX_UNIT_DIP)
            )
        } else if (packageName == "com.android.launcher3") {
            resparam.res.setReplacement(
                packageName,
                "dimen",
                "taskbar_stashed_size",
                DimensionReplacement(0F, TypedValue.COMPLEX_UNIT_DIP)
            )

            resparam.res.setReplacement(
                packageName,
                "dimen",
                "taskbar_stashed_handle_height",
                DimensionReplacement(0F, TypedValue.COMPLEX_UNIT_DIP)
            )
        }

        XposedBridge.log("$packageName replaced res")
    }
}