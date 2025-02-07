# 📌 Page view.

## 🔹 1. Agregar dependencias en build.gradle
En tu archivo build.gradle (Module: app), asegúrate de tener la dependencia de ViewPager2:
```kotlin
dependencies {
    implementation 'androidx.viewpager2:viewpager2:1.0.0'
}
```

## 🔹 2. Crear el Layout de la Tarjeta (item_card.xml)
Este diseño define cómo se verá cada tarjeta en el slider.
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center"
    android:background="@drawable/card_background"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Título"
        android:textSize="18sp"
        android:textStyle="bold"
        android:gravity="center"/>

    <TextView
        android:id="@+id/tvDescription"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Descripción"
        android:textSize="14sp"
        android:gravity="center"/>
</LinearLayout>
```

## 🔹 3. Crear el CardAdapter.kt
Este adapter gestiona las tarjetas dentro del ViewPager2..
```kotlin
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val cardList: List<CardItem>) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
    }

    override fun getItemCount(): Int = cardList.size
}
```

## 🔹 4. Crear la Clase CardItem.kt
```kotlin
data class CardItem(val title: String, val description: String)
```

## 🔹 5. Agregar ViewPager2 en el activity_main.xml
res/layout/activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="16dp">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

🔹 6. Configurar ViewPager2 en MainActivity.kt
```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        // Lista de tarjetas
        val cards = listOf(
            CardItem("Tarjeta 1", "Descripción de la tarjeta 1"),
            CardItem("Tarjeta 2", "Descripción de la tarjeta 2"),
            CardItem("Tarjeta 3", "Descripción de la tarjeta 3"),
            CardItem("Tarjeta 4", "Descripción de la tarjeta 4")
        )

        // Configurar ViewPager2 con el Adapter
        viewPager.adapter = CardAdapter(cards)

        // Activar deslizamiento infinito (opcional)
        viewPager.offscreenPageLimit = 3
    }
}
```
