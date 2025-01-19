# 📌 0 - Desactivar Actionbar nativa.
Antes de generar nuestra propria actionbar vamos a tener que controlar que esté desactivada en los ficheros de estilos o temas.
Para ello vamos al fichero `res/values/themes` y controlamos que en ambos ficheros (tema claro y tema oscuro), esté puesto lo siguiente:

**Claro**.
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.ToolbarMenu" parent="Theme.Material3.DayNight.NoActionBar">
        <!-- Customize your light theme here. -->
        <!-- <item name="colorPrimary">@color/my_light_primary</item> -->
    </style>

    <style name="Theme.ToolbarMenu" parent="Base.Theme.ToolbarMenu" />
</resources>
```

**Oscuro**
```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- Base application theme. -->
    <style name="Base.Theme.ToolbarMenu" parent="Theme.Material3.DayNight.NoActionBar">
        <!-- Customize your dark theme here. -->
        <!-- <item name="colorPrimary">@color/my_dark_primary</item> -->
    </style>
</resources>
```

# 📌 1 - Importamos los iconos necesarios para la toolbar.
Antes de definir el layout de la toolbar importaremos los SVG necesarios para los iconos.
Para ello ceramos un nuevo Drawable.


# 📌 2 - Creamos el layout de la toolbar.
Creamos un nuevo layout en el directorio de recursos de tipo `<androidx.appcompat.widget.Toolbar>`.

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.appcompat.widget.Toolbar
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/toolbarBox"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/blue"
    android:padding="10dp">

    <!-- Imagen de la flecha hacia atrás -->
    <ImageView
        android:id="@+id/iconoFlecha"
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:layout_gravity="start|center_vertical"
        android:src="@drawable/baseline_arrow_back_24" />

    <!-- Texto del título -->
    <TextView
        android:id="@+id/textoToolbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:text="Nombre aplicacion"
        android:textSize="15sp"
        android:textColor="@color/white" />

    <!-- Botón de menú -->
    <Button
        android:id="@+id/btnMenuToolbar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|center_vertical"
        android:text="Menu" />

</androidx.appcompat.widget.Toolbar>
```


# 📌 3 - Creamos las entradas que inflarán el menu.
- Creamos directorio `/res/menu`
- En directorio `menu` click derecho y creamos `nuevo recurso de menú`.

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <!-- Entrada de menú para "Ajustes" -->
    <item
        android:id="@+id/item1"
        android:title="Menu Item 1"
        android:icon="@drawable/baseline_arrow_back_24"
        app:showAsAction="never" />

    <!-- Entrada de menú para "Ayuda" -->
    <item
        android:id="@+id/item2"
        android:title="Menu Item 2"
        android:icon="@drawable/baseline_arrow_back_24"
        app:showAsAction="never" />

</menu>
```




