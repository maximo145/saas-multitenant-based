package com.decode.saassharedschema.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class MensajeServicio {
    @JsonProperty("codigo")
    private String codigo = null;
    @JsonProperty("mensaje")
    private String mensaje = null;
    @JsonProperty("titulo")
    private String titulo = null;
    @JsonProperty("regresar")
    private Boolean regresar = null;
    @JsonProperty("rutaRegreso")
    private String rutaRegreso = null;
    @JsonProperty("tipo")
    private TipoEnum tipo = null;

    public MensajeServicio() {
    }

    public MensajeServicio codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    @ApiModelProperty("Códigos de validación de reglas de negocio. El valor ESM00 indica succeso, el valor 'HTTP' indica que es un error ya considerado en los status code estándard del HTTP.")
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MensajeServicio mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    @ApiModelProperty("Mensaje de resultado asociado a la operación")
    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public MensajeServicio titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    @ApiModelProperty("Titulo")
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public MensajeServicio regresar(Boolean regresar) {
        this.regresar = regresar;
        return this;
    }

    @ApiModelProperty("Flag si permite regresar a la pantalla anterior")
    public Boolean isRegresar() {
        return this.regresar;
    }

    public void setRegresar(Boolean regresar) {
        this.regresar = regresar;
    }

    public MensajeServicio rutaRegreso(String rutaRegreso) {
        this.rutaRegreso = rutaRegreso;
        return this;
    }

    @ApiModelProperty("Ruta a donde se debe regresar")
    public String getRutaRegreso() {
        return this.rutaRegreso;
    }

    public void setRutaRegreso(String rutaRegreso) {
        this.rutaRegreso = rutaRegreso;
    }

    public MensajeServicio tipo(TipoEnum tipo) {
        this.tipo = tipo;
        return this;
    }

    @ApiModelProperty("Código del tipo de resultado asociado a la operación. Puede ser utilizado para tratar adecuadamente la exhibición de los alertas en el frontend o en las pruebas automatizadas.")
    public TipoEnum getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            MensajeServicio mensajeServicio = (MensajeServicio)o;
            return Objects.equals(this.codigo, mensajeServicio.codigo) && Objects.equals(this.mensaje, mensajeServicio.mensaje) && Objects.equals(this.titulo, mensajeServicio.titulo) && Objects.equals(this.regresar, mensajeServicio.regresar) && Objects.equals(this.rutaRegreso, mensajeServicio.rutaRegreso) && Objects.equals(this.tipo, mensajeServicio.tipo);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.codigo, this.mensaje, this.titulo, this.regresar, this.rutaRegreso, this.tipo});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MensajeServicio {\n");
        sb.append("    codigo: ").append(this.toIndentedString(this.codigo)).append("\n");
        sb.append("    mensaje: ").append(this.toIndentedString(this.mensaje)).append("\n");
        sb.append("    titulo: ").append(this.toIndentedString(this.titulo)).append("\n");
        sb.append("    regresar: ").append(this.toIndentedString(this.regresar)).append("\n");
        sb.append("    rutaRegreso: ").append(this.toIndentedString(this.rutaRegreso)).append("\n");
        sb.append("    tipo: ").append(this.toIndentedString(this.tipo)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }

    public static enum TipoEnum {
        ERROR("error"),
        WARN("warn"),
        INVALID("invalid"),
        FATAL("fatal"),
        INFO("info"),
        R("R");

        private String value;

        private TipoEnum(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        @JsonCreator
        public static TipoEnum fromValue(String text) {
            TipoEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                TipoEnum b = var1[var3];
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }

            return null;
        }
    }
}
