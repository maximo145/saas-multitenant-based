package com.decode.saassharedschema.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;

public class MetadatoServicio {
    @JsonProperty("mensajes")
    private List<MensajeServicio> mensajes = null;
    @JsonProperty("totalRegistros")
    private Integer totalRegistros = null;
    @JsonProperty("idTransaccion")
    private String idTransaccion = null;
    @JsonProperty("numeroPaginaSiguiente")
    private Integer numeroPaginaSiguiente = null;
    @JsonProperty("numeroTotalPaginas")
    private Integer numeroTotalPaginas = null;
    @JsonProperty("resultado")
    private Boolean resultado = null;
    @JsonProperty("atributosInvalidos")
    private Map<String, String> atributosInvalidos = null;
    @JsonProperty("codigoRespuesta")
    private String codigoRespuesta = null;
    @JsonProperty("mensaje")
    private String mensaje = null;
    @JsonProperty("datosAdicionales")
    private String datosAdicionales = null;

    public MetadatoServicio() {
    }

    public MetadatoServicio mensajes(List<MensajeServicio> mensajes) {
        this.mensajes = mensajes;
        return this;
    }

    public MetadatoServicio addMensajesItem(MensajeServicio mensajesItem) {
        if (this.mensajes == null) {
            this.mensajes = new ArrayList();
        }

        this.mensajes.add(mensajesItem);
        return this;
    }

    @ApiModelProperty("")
    public List<MensajeServicio> getMensajes() {
        return this.mensajes;
    }

    public void setMensajes(List<MensajeServicio> mensajes) {
        this.mensajes = mensajes;
    }

    public MetadatoServicio totalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
        return this;
    }

    @ApiModelProperty("Cantidad de registros encontrados como resultado de la búsqueda")
    public Integer getTotalRegistros() {
        return this.totalRegistros;
    }

    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }

    public MetadatoServicio idTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
        return this;
    }

    @ApiModelProperty("ID de la transacción para permitir la trazabilidad en los logs de aplicación.")
    public String getIdTransaccion() {
        return this.idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public MetadatoServicio numeroPaginaSiguiente(Integer numeroPaginaSiguiente) {
        this.numeroPaginaSiguiente = numeroPaginaSiguiente;
        return this;
    }

    @ApiModelProperty("Número de la página siguiente para paginación. Número máximo 2147483647 (Entero Java).")
    public Integer getNumeroPaginaSiguiente() {
        return this.numeroPaginaSiguiente;
    }

    public void setNumeroPaginaSiguiente(Integer numeroPaginaSiguiente) {
        this.numeroPaginaSiguiente = numeroPaginaSiguiente;
    }

    public MetadatoServicio numeroTotalPaginas(Integer numeroTotalPaginas) {
        this.numeroTotalPaginas = numeroTotalPaginas;
        return this;
    }

    @ApiModelProperty("Número total de páginas. Número máximo 2147483647 (Entero Java).")
    public Integer getNumeroTotalPaginas() {
        return this.numeroTotalPaginas;
    }

    public void setNumeroTotalPaginas(Integer numeroTotalPaginas) {
        this.numeroTotalPaginas = numeroTotalPaginas;
    }

    public MetadatoServicio resultado(Boolean resultado) {
        this.resultado = resultado;
        return this;
    }

    @ApiModelProperty("Se informa si resultado con éxito o no.")
    public Boolean isResultado() {
        return this.resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public MetadatoServicio atributosInvalidos(Map<String, String> atributosInvalidos) {
        this.atributosInvalidos = atributosInvalidos;
        return this;
    }

    public MetadatoServicio putAtributosInvalidosItem(String key, String atributosInvalidosItem) {
        if (this.atributosInvalidos == null) {
            this.atributosInvalidos = new HashMap();
        }

        this.atributosInvalidos.put(key, atributosInvalidosItem);
        return this;
    }

    @ApiModelProperty(
            example = "{\"attr1\":\"Atributo 1 inválido\",\"attr2\":\"Atributo 2 no informado\"}",
            value = "Estructura dinámica que contiene los atributos recibidos en formato inválido (status code 400). Puede ser utilizado por el frontend para indicar los campos que necesitan de atención."
    )
    public Map<String, String> getAtributosInvalidos() {
        return this.atributosInvalidos;
    }

    public void setAtributosInvalidos(Map<String, String> atributosInvalidos) {
        this.atributosInvalidos = atributosInvalidos;
    }

    public MetadatoServicio codigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
        return this;
    }

    @ApiModelProperty("Código de respuesta")
    public String getCodigoRespuesta() {
        return this.codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public MetadatoServicio mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    @ApiModelProperty("Descripción de mensaje")
    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public MetadatoServicio datosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
        return this;
    }

    @ApiModelProperty("Datos adicionales")
    public String getDatosAdicionales() {
        return this.datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MetadatoServicio metadatoServicio = (MetadatoServicio)o;
            return Objects.equals(this.mensajes, metadatoServicio.mensajes) && Objects.equals(this.totalRegistros, metadatoServicio.totalRegistros) && Objects.equals(this.idTransaccion, metadatoServicio.idTransaccion) && Objects.equals(this.numeroPaginaSiguiente, metadatoServicio.numeroPaginaSiguiente) && Objects.equals(this.numeroTotalPaginas, metadatoServicio.numeroTotalPaginas) && Objects.equals(this.resultado, metadatoServicio.resultado) && Objects.equals(this.atributosInvalidos, metadatoServicio.atributosInvalidos) && Objects.equals(this.codigoRespuesta, metadatoServicio.codigoRespuesta) && Objects.equals(this.mensaje, metadatoServicio.mensaje) && Objects.equals(this.datosAdicionales, metadatoServicio.datosAdicionales);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.mensajes, this.totalRegistros, this.idTransaccion, this.numeroPaginaSiguiente, this.numeroTotalPaginas, this.resultado, this.atributosInvalidos, this.codigoRespuesta, this.mensaje, this.datosAdicionales});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MetadatoServicio {\n");
        sb.append("    mensajes: ").append(this.toIndentedString(this.mensajes)).append("\n");
        sb.append("    totalRegistros: ").append(this.toIndentedString(this.totalRegistros)).append("\n");
        sb.append("    idTransaccion: ").append(this.toIndentedString(this.idTransaccion)).append("\n");
        sb.append("    numeroPaginaSiguiente: ").append(this.toIndentedString(this.numeroPaginaSiguiente)).append("\n");
        sb.append("    numeroTotalPaginas: ").append(this.toIndentedString(this.numeroTotalPaginas)).append("\n");
        sb.append("    resultado: ").append(this.toIndentedString(this.resultado)).append("\n");
        sb.append("    atributosInvalidos: ").append(this.toIndentedString(this.atributosInvalidos)).append("\n");
        sb.append("    codigoRespuesta: ").append(this.toIndentedString(this.codigoRespuesta)).append("\n");
        sb.append("    mensaje: ").append(this.toIndentedString(this.mensaje)).append("\n");
        sb.append("    datosAdicionales: ").append(this.toIndentedString(this.datosAdicionales)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}

