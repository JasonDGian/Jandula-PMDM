# 📌 Linear Layout.
La principal característica del `LinearLayout` en Android es que organiza sus vistas secundarias en una disposición lineal, ya sea vertical u 
horizontal. Todos los elementos hijos se colocan en una sola línea, uno tras otro, en el orden en que se definen.

**Definir una vista como linear layout.**
```kotlin
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

/* Vistas hijas definidas en su interior */

</LinearLayout>
```

## 🔸 Organizar elementos en Linear Layout.
En un linear layout podemos definir la `orientación` y el `peso` de las vistas hijas.    
- Orientación: Define si las vistas se aplian de manera horizontal o vertical.    
- Peso: Define el porcentaje que ocupa en la pantalla cada vista.    

**Configuración de la orientación:**    
Para configurar la orientación, definimos el atributo `android:orientation=""` a `horizontal` o `vertical`.   
   
![imagen](https://github.com/user-attachments/assets/7bfd80ec-d49a-48fe-b346-e45bd3cd9c18)
   
Copiapega:
```kotlin
android:orientation="vertical"
android:orientation="horizontal"
```

**Configuración del peso de las vistas hijas:**    
Para definir el peso de una vista secundaria es importante tener en cuenta estas dos reglas:
- La vista debe de explicitar el peso con el atributo `android:layout_weight="N"`.
  - Donde `N` representa el peso expresado en numero entero.
- Configurar el ancho o alto de la vista a `0dp` para ceder el control de su tamaño al peso.   
  - La configuración dependerá de la orientación establecida.

**Ejemplo de Layout Linear con vistas organizadas por peso en distribución vertical.**
   
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:orientation="horizontal"
    >
    
    <View
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="match_parent"
        android:background="#003FFF"
        />

    <View
        android:layout_weight="1"
        android:layout_width="0dp"
        android:layout_height="match_parent"
        />

    <View
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="match_parent"
        android:background="#003FFF"
        />
    
</LinearLayout>
```

![imagen](https://github.com/user-attachments/assets/dce9b44b-790f-4e3d-9ffa-0089060c9f3a)

