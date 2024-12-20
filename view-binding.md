# 📌 Viewbinding - Vinculación de vistas.
El Viewbinding o vinculo de vistas permite acceder a una vista mediante una declaración de una variable. Es más seguro y rápido de usar. Para vincular una vista utilizamos seguimos este procedimiento.    

1. Habilitar el databinding en el módulo gradle de la aplicación.
2. Sincroniza el proyecto gradle.
3. Crea el fichero layout que deseas vincular.
4. Declara una variable de tipo 'ActivityMainBinding'.
5. Inicializa la variable declarada en el onCreate.
6. Define el contenido de la vista.
7. Accede a las vistas usando el bindin.

---

## 1. Habilitar el databinding en el módulo gradle de la aplicación.
**Habilitar el viewBinding en el modulo gradle de la aplicación.**    
Dentro del bloque android, añade el siguiente código:
```kts
    viewBinding {
        enabled = true
    }
```
![imagen](https://github.com/user-attachments/assets/fd781491-6853-47b6-ad5c-46a61505f62f)


## 2. Sincroniza el proyecto gradle.
![imagen](https://github.com/user-attachments/assets/a10b7a62-3ad4-4936-9065-483dd19ebe55)


## 3. Crea el fichero layout que deseas vincular.
Crea un fichero XML con el layout que deseas vincular, de no tenerlo ya.

## 4. Declara una variable de tipo 'Activity-NOMBRE-Binding'.
Declaramos una variable cuyo tipo de clase dependerá directamente del nombre del fichero XML.    
Si por ejemplo estamos creando un viewBinding para `activity_main.xml` entonces la declaración será así;    
```kotlin
    // Declara la variable de tipo viewBinding para acceder a la vista.
    private lateinit var viewBinding: ActivityMainBinding
```

**Si todo sale según lo esperado, se importará automaticamente una clase con el nombre correspondiente.   
```kotlin
import com.dasus.jesapadavideoapp.databinding.ActivityMainBinding
```
    
>[!CAUTION]
>la variable que representa el View Binding variará dependiendo del nombre del archivo de diseño XML que estés utilizando. El nombre de la clase de binding generada por el sistema sigue una convención basada en el nombre del archivo de diseño, pero con ciertas transformaciones.
   
![imagen](https://github.com/user-attachments/assets/8ead50db-6951-4178-85a4-22e0a7de3ae2)



## 5. Inicializa la variable declarada en el onCreate.

## 6. Define el contenido de la vista.

## 7. Accede a las vistas usando el bindin.
