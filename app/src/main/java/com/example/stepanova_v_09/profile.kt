package com.example.stepanova_v_09

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val tvGreeting: TextView = findViewById(R.id.tvGreeting)
        val ivSettings: ImageView = findViewById(R.id.ivSettings)
        val btnLogout: Button = findViewById(R.id.btnLogout)
        val btnOnline: Button = findViewById(R.id.btnOnline)
        val btnSnapshot: Button = findViewById(R.id.btnSnapshot)

        // Приветствие
        val login = intent.getStringExtra("USER_LOGIN") ?: "Гость"
        tvGreeting.text = "Привет, $login!"

        // Переход на настройки
        ivSettings.setOnClickListener {
            //val intent = Intent(this, SettingsManager::class.java)
            startActivity(intent)
        }

        // Выйти
        btnLogout.setOnClickListener {
            val intent = Intent(this, IdentityService::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        // Снимок (камера)
        btnSnapshot.setOnClickListener {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Камера недоступна", Toast.LENGTH_SHORT).show()
            }
        }

        // Онлайн - не работает (просто показываем сообщение)
        btnOnline.setOnClickListener {
            Toast.makeText(this, "Функция в разработке", Toast.LENGTH_SHORT).show()
        }
    }
}