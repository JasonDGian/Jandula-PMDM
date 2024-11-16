# 📌 Radio-group.
Los botones radio se agrupan en un "Radio group" que los contiene.

```kotlin
    <RadioGroup
        android:id="@+id/rg_grupoRadio"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        >
        
    // Estructura basica radio button.
   <RadioButton
        android:id="@+id/identificador"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Etiqueta mostrada"
        app:layout_constraintBottom_toBottomOf="parent"
        [...]
        app:layout_constraintTop_toTopOf="parent"/>
        
    <RadioButton
    [atributos radio button]
    />
        
    <RadioButton
    [atributos radio button]
    />

    </RadioGroup>



```
