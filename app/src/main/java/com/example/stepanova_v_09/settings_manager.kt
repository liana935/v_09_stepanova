package com.example.stepanova_v_09

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsManager : AppCompatActivity() {

    private lateinit var cbShowExpiry: CheckBox
    private lateinit var cbShowActive: CheckBox
    private lateinit var cbNotifyNew: CheckBox
    private lateinit var btnResetSettings: Button
    private lateinit var ivBack: ImageView
    private lateinit var ivLogout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_manager)

        ivBack = findViewById(R.id.ivBack)
        ivLogout = findViewById(R.id.ivLogout)
        cbShowExpiry = findViewById(R.id.cbShowExpiry)
        cbShowActive = findViewById(R.id.cbShowActive)
        cbNotifyNew = findViewById(R.id.cbNotifyNew)
        btnResetSettings = findViewById(R.id.btnResetSettings)

        // Стрелка назад - на экран Profile
        ivBack.setOnClickListener {
            finish()
        }

        // Иконка выхода - на экран IdentityService
        ivLogout.setOnClickListener {
            val intent = Intent(this, IdentityService::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Чекбокс 3 - показывает уведомление при отключении
        cbNotifyNew.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                showCustomToast("Отключено отображение новых промокодов")
            }
        }
        // Сброс настроек
        btnResetSettings.setOnClickListener {
            cbShowExpiry.isChecked = false
            cbShowActive.isChecked = false
            cbNotifyNew.isChecked = false
        }
    }

    private fun showCustomToast(message: String) {
        val inflater = LayoutInflater.from(this)
        val layout = inflater.inflate(R.layout.toast, null)

        val text = layout.findViewById<TextView>(R.id.tvToastText)
        val icon = layout.findViewById<ImageView>(R.id.ivToastIcon)

        text.text = message
        icon.setImageResource(R.drawable.qr)

        val toast = Toast(this)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}