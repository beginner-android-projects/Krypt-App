package com.pyra.krpytapplication.view.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.messaging.FirebaseMessaging
import com.pyra.krpytapplication.R
import com.pyra.krpytapplication.Utils.Constants
import com.pyra.krpytapplication.Utils.SharedHelper
import com.pyra.krpytapplication.Utils.openNewTaskActivity
import com.pyra.krpytapplication.app.AppRunningService
import com.pyra.krpytapplication.app.MyApp
import com.pyra.krpytapplication.repositories.interfaces.AuthenticationListner
import isAppIsInBackground
import isMyServiceRunning
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.doAsync
import showToast
import java.io.IOException
import java.util.concurrent.TimeoutException


class SplashActivity : AppCompatActivity() {

    private val REQUEST_READ_PHONE_STATE = 0x12;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!isMyServiceRunning(this, AppRunningService::class.java) && !isAppIsInBackground(this))
            startService(Intent(this, AppRunningService::class.java))

        checkForPhoneStatePermission()

        getNewToken()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        enableKryptKeyboard()
    }


    fun enableKryptKeyboard() {
        val imeManager =
            applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val list: String = imeManager!!.getEnabledInputMethodList().toString()
        val defaultKeyboard =
            Settings.Secure.getString(this.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)
        val kryptKeyboard =
            "com.krypt.chat/com.pyra.krpytapplication.incognitokeyboard.KryptIncognitoKeyboard"
        if (list.contains(kryptKeyboard) && !defaultKeyboard.equals(
                kryptKeyboard,
                ignoreCase = true
            )
        ) {
            imeManager.showInputMethodPicker()
        }
    }

    private fun getNewToken() {

        if (SharedHelper(this).firebaseToken == "")
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->


                if (task.isSuccessful) {
                    task.result?.let { token ->
                        saveToken(token)
                        Log.d("token", token);

                    }
                }

            }
    }

    private fun saveToken(token: String) {
        val sharedHelper = SharedHelper(this)
        sharedHelper.firebaseToken = token
    }

    private fun checkForPhoneStatePermission() {

        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                REQUEST_READ_PHONE_STATE
            )

        } else {

            getImeiNumber()
            if (SharedHelper(this).loggedIn) {

                doAsync {
                    MyApp.xmppOperations.connectToXMPPServer(
                        SharedHelper(this@SplashActivity).kryptKey,
                        SharedHelper(this@SplashActivity).password,
                        object : AuthenticationListner {
                            override fun isSuccess(status: Boolean) {
                                if (status) {
                                    if (SharedHelper(this@SplashActivity).vaultPassword == "") {
                                        val intent = Intent(
                                            this@SplashActivity,
                                            DuressPasswordActivity::class.java
                                        )
                                        intent.putExtra(
                                            Constants.IntentKeys.CHANGEPASSWORDFLOW,
                                            false
                                        )
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)
                                    } else {
                                        SharedHelper(this@SplashActivity).showKryptScreen = true
                                        openNewTaskActivity(MainActivity::class.java)
                                    }
                                }
                            }

                        })
                }

            } else {
                openNewTaskActivity(KryptCodeActivity::class.java)
            }
        }
    }

    private fun getImeiNumber() {

        var manager: TelephonyManager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        Log.d("IMEI ",manager.deviceId)
    }

    private fun makeAsSystemApp() {


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_READ_PHONE_STATE) {
            if ((grantResults.isNotEmpty()) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                openNewTaskActivity(KryptCodeActivity::class.java)
            } else {
                showToast(getString(R.string.phone_state_permission_required))
            }
        }
    }


}
