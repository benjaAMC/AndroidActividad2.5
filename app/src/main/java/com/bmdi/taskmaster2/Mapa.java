package com.bmdi.taskmaster2;

import android.os.Bundle;
//Librerias a utilizar
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;



import androidx.appcompat.app.AppCompatActivity;


public class Mapa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Carga la configuración del mapa usando las preferencias predeterminadas.
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        // Obtiene la referencia al componente MapView del layout.
        MapView mapView = findViewById(R.id.mapView);
        // Establece la fuente de azulejos del mapa a MAPNIK (mapa estándar).
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        // Activa los controles de zoom en el mapa.
        mapView.setBuiltInZoomControls(true);
        // Activa el control multitáctil para el mapa (zoom con dos dedos).
        mapView.setMultiTouchControls(true);

        // Coordenadas del IP Santo Tomás, Chile.
        double ipSantoTomasLatitud = -33.4493141; // Latitud del IP Santo Tomás.
        double ipSantoTomasLongitud = -70.6624069; // Longitud del IP Santo Tomás.

        // Crear objetos GeoPoint para las coordenadas definidas.
        GeoPoint IPsantoTomasPoint = new GeoPoint(ipSantoTomasLatitud, ipSantoTomasLongitud);
        // Configura la vista inicial del mapa centrada en el IP Santo Tomas con un nivel de zoom de 15.
        mapView.getController().setZoom(15.0);
        // Centra el mapa en el punto de Santiago.
        mapView.getController().setCenter(IPsantoTomasPoint);

        // Crear un marcador para el IP Santo Tomás. y Creamos un marcador en el mapa
        Marker marcadorSantoTomas = new Marker(mapView);
        // Establece la posición del marcador en el punto de IP Santo Tomás.
        marcadorSantoTomas.setPosition(IPsantoTomasPoint);
        // Establece el ancla del marcador.
        marcadorSantoTomas.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        // Establece el título del marcador.
        marcadorSantoTomas.setTitle("Ip Santo Tomas, Chile");
        // Establece una descripción para el marcador.
        marcadorSantoTomas.setSnippet("Un Instituto Tomista");

        // Agregar los marcadores al mapa.
        mapView.getOverlays().add(marcadorSantoTomas);



        // Coordenadas Parque
        double parqueLatitud = -33.4625076; // Latitud del Parque.
        double parqueLongitud = -70.6600286; // Longitud del Parque.

        // Crear objetos GeoPoint para las coordenadas definidas.
        GeoPoint parquePoint = new GeoPoint(parqueLatitud, parqueLongitud);

        // Crear un marcador para el IP Santo Tomás. y Creamos un marcador en el mapa
        Marker marcadorParque = new Marker(mapView);
        // Establece la posición del marcador en el punto de IP Santo Tomás.
        marcadorParque.setPosition(parquePoint);
        // Establece el ancla del marcador.y los valores se pueden ajustar la imagen con el marcador
        marcadorParque.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        // Establece el título del marcador.
        marcadorParque.setTitle("Parque O'Higgins");
        // Establece una descripción para el marcador.
        marcadorParque.setSnippet("Un Parque");

        // Agregar los marcadores al mapa.
        mapView.getOverlays().add(marcadorParque);

        // Crear una línea que conecte los 2 Marcadores
        Polyline linea = new Polyline();
        // Añade el punto del Ip y el parque a la línea.
        linea.addPoint(IPsantoTomasPoint);
        linea.addPoint(parquePoint);
        // Establece el color de la línea (azul en formato ARGB).
        linea.setColor(0xFF0000FF);
        // Establece el ancho de la línea a 5 píxeles.
        linea.setWidth(5);
        // Añadir la línea al mapa.
        mapView.getOverlayManager().add(linea);

        // Configurar el Spinner para cambiar el tipo de mapa y Obtiene la referencia al componente Spinner del id del xml.
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        // Define un array con los tipos de mapas.
        String[] mapTypes = {"Mapa Normal", "Mapa de Transporte", "Mapa Topografico"};

        // Crear un ArrayAdapter para poblar el Spinner con los tipos de mapas.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        // Establece el layout para los elementos del Spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Asigna el adaptador al Spinner.
        mapTypeSpinner.setAdapter(adapter);

        // Listener para detectar cambios en la selección del Spinner.
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {   switch (position) {
                case 0:
                    mapView.setTileSource(TileSourceFactory.MAPNIK);
                    break;
                case 1:
                    mapView.setTileSource(new XYTileSource(
                            "PublicTransport",
                            0, 18, 256, ".png", new String[]{
                            "https://tile.memomaps.de/tilegen/"}));
                    break;
                case 2:
                    mapView.setTileSource(new XYTileSource(
                            "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                            "https://a.tile.opentopomap.org/",
                            "https://b.tile.opentopomap.org/",
                            "https://c.tile.opentopomap.org/"}));
                    break;
            }
            }

            // No se hace nada cuando no se selecciona ningún elemento.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
