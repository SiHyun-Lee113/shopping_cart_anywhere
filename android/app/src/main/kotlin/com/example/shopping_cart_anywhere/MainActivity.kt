package com.example.shopping_cart_anywhere


import android.app.PictureInPictureParams
import android.os.Build
import android.util.Rational
import androidx.annotation.RequiresApi
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
class MainActivity: FlutterActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUserLeaveHint() {
        // PIP 모드로 전환
        enterPictureInPictureMode(PictureInPictureParams.Builder()
            .setAspectRatio(Rational(9, 16))  // PIP 모드의 화면 비율 설정
            .build())
        super.onUserLeaveHint()
    }

    private val CHANNEL = "com.example.pip/pip"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)  // 반드시 호출해야 함!

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "enterPipMode") {
                println("method channel run")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPipMode()
                    result.success(null)
                } else {
                    result.error("UNAVAILABLE", "PIP mode is not supported.", null)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun enterPipMode() {
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(Rational(9, 16))  // 비율
            .build()
        enterPictureInPictureMode(params)
    }
}
