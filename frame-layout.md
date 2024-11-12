# 📌 Frame Layout.
La configuración de `FrameLayout` es ideal para mostrar una sola vista o para superponer varias vistas de manera simple. Es eficiente en situaciones en las que no se necesita un diseño complejo y se requieren superposiciones.    
     
**Definir una vista como frame layout.**   
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    
    /* Aqui se definen las vistas hijas */

</FrameLayout>
```
   
## 🔸 Organizar elementos en Linear Layout.
Este tipo de vista organiza los elementos como un sistema de coordenadas, apilando los objetos lo mas cerca posible de la esquina superior izquierda, conociendose este punto como 'el punto 0:0'.

Esta vista organiza sus elementos con el atributo `android:layout_gravity=""`, al que se le asigna **uno o más valores** que juntos trabajan como una coordenada precisa. Para obtener ciertas posiciones deberemos 'concatenar' valores.   
   
**Los valores de posicionamiento son los siguientes:**    
   
 <p align="center">
   <img src="https://github.com/user-attachments/assets/ad632886-45f5-4243-bfa8-43848ccc5963">
 </p>


**Código del ejemplo:**
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="START | TOP"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="start|top"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="CENTER | TOP"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="center|top"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="END | TOP"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="end|top"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="START | CENTER"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="start|center"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="CENTER"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="center"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="END | CENTER"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="end|center"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="START | BOTTOM"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="start|bottom"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="CENTER | BOTTOM"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="center|bottom"
        />

    <TextView
        android:layout_margin="5dp"
        android:padding="5dp"
        android:text="END | BOTTOM"
        android:textColor="@color/white"
        android:layout_height="wrap_content"
        android:layout_width="wrap_content"
        android:background="@color/black"
        android:layout_gravity="end|bottom"
        />
    
</FrameLayout>
```

