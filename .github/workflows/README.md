# Sala Master (app nativa)

Reproductor de señales propias (TV/radio) para Android, hecho en Kotlin con
ExoPlayer (vía Media3) para reproducir HLS (.m3u8) de forma nativa — sin
depender de un WebView, que es lo que daba problemas en la TV box.

## Cómo está organizado el proyecto

Piensa en esto como capas, de afuera hacia adentro:

- **`build.gradle.kts` (raíz) y `settings.gradle.kts`** — le dicen a Gradle
  (la herramienta que compila todo) qué plugins usar y qué módulos tiene el
  proyecto. Aquí casi nunca hay que tocar nada.
- **`app/build.gradle.kts`** — la configuración específica de la app: el
  nombre del paquete (`com.salamaster.tv`), qué versión mínima de Android
  soporta (`minSdk`), y las librerías que usa (`dependencies`). Aquí es
  donde agregarías una librería nueva si la necesitaras.
- **`app/src/main/AndroidManifest.xml`** — la "ficha de identidad" de la
  app: qué pantallas (Activities) tiene, qué permisos pide (en este caso,
  Internet), y el ícono/nombre.
- **`app/src/main/java/com/salamaster/tv/`** — el código Kotlin, o sea la
  lógica:
  - `Channel.kt` — la lista de señales (nombre + URL). **Para agregar un
    canal nuevo, solo edita este archivo** y agrega una entrada más a
    `Channels.ALL`.
  - `MainActivity.kt` — la pantalla de menú: arma la lista de tarjetas de
    canales a partir de `Channel.kt`.
  - `PlayerActivity.kt` — la pantalla del reproductor: crea el `ExoPlayer`,
    le pasa la URL, y maneja errores/estado.
- **`app/src/main/res/`** — los recursos que no son código:
  - `layout/` — los diseños de pantalla en XML (dónde va cada botón/texto).
  - `values/` — textos (`strings.xml`), colores (`colors.xml`) y estilos
    (`themes.xml`).
  - `drawable/` — formas simples (fondos redondeados, píldoras de color).
  - `mipmap-*/` — el ícono de la app en distintos tamaños (para distintas
    densidades de pantalla).
- **`.github/workflows/build.yml`** — el "robot" que compila la app
  automáticamente cada vez que subes cambios a GitHub. Corre en los
  servidores de GitHub (no en tu computadora), instala las herramientas de
  Android, y te deja el `.apk` listo para descargar en la pestaña
  **Actions** del repositorio.
- **`gradlew`, `gradlew.bat`, `gradle/`** — el "Gradle Wrapper": una copia
  autocontenida de la herramienta de compilación, para que no dependas de
  tener Gradle instalado en tu computadora. No hay que tocarlos.

## Cómo agregar una señal nueva

1. Abre `app/src/main/java/com/salamaster/tv/Channel.kt`.
2. Copia un bloque `Channel(...)` existente y pégalo dentro de la lista
   `Channels.ALL`, cambiando nombre, tipo y URL.
3. Sube el cambio a GitHub (o reemplaza el archivo si subes por la web) —
   el flujo de Actions compila automáticamente el nuevo .apk.

## Cómo funciona la compilación en GitHub Actions

Cada vez que subes (haces *push*) cambios a la rama `main`, GitHub:

1. Descarga tu código en una máquina temporal.
2. Instala Java y el SDK de Android.
3. Corre `./gradlew assembleDebug`, el mismo comando que compilarías en tu
   propia computadora si tuvieras Android Studio.
4. Deja el archivo `app-debug.apk` disponible para descargar en la pestaña
   **Actions** → la ejecución más reciente → sección **Artifacts**.

También puedes lanzarlo manualmente sin subir cambios: pestaña
**Actions** → **Compilar APK** → **Run workflow**.
