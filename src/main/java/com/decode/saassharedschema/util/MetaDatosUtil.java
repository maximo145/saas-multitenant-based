package com.decode.saassharedschema.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MetaDatosUtil {
    public static MetadatoServicio buildMetadatos(String codigo, String mensaje, MensajeServicio.TipoEnum tipo,
            String idTransaccion) {
        MetadatoServicio meta = new MetadatoServicio();
        List<MensajeServicio> mensajes = new ArrayList();
        MensajeServicio m = new MensajeServicio();
        m.setCodigo((String) Optional.ofNullable(codigo).orElse("ESM00"));
        m.setMensaje((String) Optional.ofNullable(mensaje).orElse("Solicitud exitosa y generó una representación"));
        m.setTipo((MensajeServicio.TipoEnum) Optional.ofNullable(tipo).orElse(MensajeServicio.TipoEnum.INFO));
        mensajes.add(m);
        meta.setMensajes(mensajes);
        meta.setIdTransaccion(idTransaccion);
        return meta;
    }

    private MetaDatosUtil() {
    }
}
