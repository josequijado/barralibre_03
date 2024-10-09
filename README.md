
# Documentación de del proyecto `barralibre_03`

Este es un proyecto evolutivo de ***Barralibre***. En este proyecto ya se usan varias Activities, intents, servicios, y otras técnicas que nos ayudarán a comprender los fundamentos de la programación en Kotlin y el desarrollo de aplicaciones para Android con AndroidStudio.

<div style="border: 1px solid #ddd; padding: 10px; background-color: #10c5f3; box-shadow: 2px 2px 5px rgba(0,0,0,0.1); ">
<h1>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MainActivity</h1>
</div>

En este documento se pretende llevar un registro de los detalles de la Main Activity.

## 1. Campo de texto y botón en la `MainActivity`

### 1.1. Etiqueta de texto (`TextView`)
En la `MainActivity`, añadimos una etiqueta de texto que muestra el rótulo **"Nombre"**. Esta etiqueta tiene las siguientes propiedades definidas en `activity_main.xml`:

```xml
<TextView
    android:id="@+id/textView"
    android:layout_width="284dp"
    android:layout_height="82dp"
    android:layout_marginStart="10dp"
    android:layout_marginTop="50dp"
    android:fontFamily="sans-serif"
    android:text="@string/text_name"
    android:textColor="#000000"
    android:textSize="60sp"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

El valor del texto mostrado está almacenado en `strings.xml` como:
```xml
<string name="text_name">Nombre</string>
```

### 1.2. Campo de texto (`EditText`)
Debajo de la etiqueta, se ha añadido un campo de texto para que el usuario ingrese su nombre. Se le han dado las siguientes propiedades:

- Margen interior (padding).
- Bordes definidos a través de un archivo `drawable`.
- Tamaño de texto configurado.
- Hint personalizado para guiar al usuario en la entrada de datos.

El código en `activity_main.xml` es el siguiente:

```xml
<EditText
    android:id="@+id/txtNombre"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_marginStart="10dp"
    android:layout_marginTop="120dp"
    android:background="@drawable/main_edit_text"
    android:ems="10"
    android:gravity="start"
    android:inputType="text"
    android:textColor="#000000"
    android:textSize="30sp"
    android:hint="@string/hint_edit_text_name"
    android:padding="10dp"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

El hint para el campo de texto está definido en `strings.xml`:
```xml
<string name="hint_edit_text_name">Introduce tu nombre</string>
```

### 1.3. Botón (`Button`)
Se ha añadido un botón debajo del campo de texto. Este botón está inicialmente inhabilitado y solo se habilitará cuando el usuario introduzca al menos tres caracteres en el campo de texto.

Las propiedades del botón son:

```xml
<Button
    android:id="@+id/btnAceptar"
    android:layout_width="330dp"
    android:layout_height="127dp"
    android:layout_marginStart="30dp"
    android:layout_marginTop="450dp"
    android:minWidth="0dp"
    android:minHeight="0dp"
    android:padding="0dp"
    android:text="@string/button_send"
    android:textSize="60sp"
    android:enabled="false"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

El texto del botón está almacenado en `strings.xml`:
```xml
<string name="button_send">Enviar</string>
```

### 1.4. Responsividad del diseño
Para hacer que el diseño sea responsivo y se adapte a diferentes tamaños de pantalla, se crearon carpetas especializadas para diferentes resoluciones:
- **layout-sw600dp**: Para pantallas de 600dp o más (como tablets).
- **layout-sw720dp**: Para pantallas más grandes (como desktop).

En esas carpetas, el archivo `activity_main.xml` fue ajustado para aumentar el tamaño de los elementos y adaptarse mejor al espacio de las pantallas grandes.

## 2. Lógica en el archivo `MainActivity.kt`

### 2.1. Configuración del `TextWatcher`
En el archivo `MainActivity.kt`, se añadió un `TextWatcher` para monitorizar el campo de texto. Este vigila si el usuario ha introducido al menos tres caracteres, en cuyo caso se habilita el botón.

El código es el siguiente:

```kotlin
txtNombre.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Habilitar el botón si hay 3 o más caracteres
        btnAceptar.isEnabled = (s?.length ?: 0) >= 3
    }

    override fun afterTextChanged(s: Editable?) {}
})
```

### 2.2. Enlace de vistas en Kotlin
Las vistas (campo de texto y botón) fueron enlazadas con sus correspondientes IDs en `MainActivity.kt` de la siguiente manera:

```kotlin
val txtNombre = findViewById<EditText>(R.id.txtNombre)
val btnAceptar = findViewById<Button>(R.id.btnAceptar)
```

## 3. Conclusión
La `MainActivity` contiene una etiqueta de texto, un campo de texto y un botón que se ajustan a diferentes tamaños de pantalla. El botón se habilita dinámicamente a través de un `TextWatcher` basado en la longitud del texto introducido en el campo de nombre.
