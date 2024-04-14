package com.decode.saassharedschema.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class ResponseDto<T>  {
    @JsonProperty("meta")
    private MetadatoServicio meta = null;

    @JsonProperty("datos")
    private T datos = null;

    public ResponseDto() {
    }

    public ResponseDto(T mapList) {
    }

    public ResponseDto meta(MetadatoServicio meta) {
        this.meta = meta;
        return this;
    }

    @ApiModelProperty(required = true, value = "")
    public MetadatoServicio getMeta() {
        return this.meta;
    }

    public void setMeta(MetadatoServicio meta) {
        this.meta = meta;
    }

    public ResponseDto datos(T datos) {
        this.datos = datos;
        return this;
    }

    @ApiModelProperty(required = true, value = "")
    public T getDatos() {
        return this.datos;
    }

    public void setDatos(T datos) {
        this.datos = datos;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            ResponseDto postProductoResponseDto = (ResponseDto) o;
            return Objects.equals(this.meta, postProductoResponseDto.meta)
                    && Objects.equals(this.datos, postProductoResponseDto.datos);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[] { this.meta, this.datos });
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PostProductoResponse {\n");
        sb.append("    meta: ").append(this.toIndentedString(this.meta)).append("\n");
        sb.append("    datos: ").append(this.toIndentedString(this.datos)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
