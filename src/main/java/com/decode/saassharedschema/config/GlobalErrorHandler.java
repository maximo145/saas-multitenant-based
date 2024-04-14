package com.decode.saassharedschema.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.decode.saassharedschema.util.MetaDatosUtil;
import com.decode.saassharedschema.util.ResponseDto;

import java.sql.SQLException;
import java.util.UUID;

import static com.decode.saassharedschema.util.MensajeServicio.TipoEnum.WARN;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseDto<CustomErrorResponse> dataIntegrityViolationExceptionException(DataIntegrityViolationException ex, WebRequest wr) {
        ResponseDto<CustomErrorResponse> response = new ResponseDto<>();
        String idTransaccion = UUID.randomUUID().toString();
        String message = "";
        message = "La eliminación del elemento solicitado no es posible debido a su uso en otras entidades.";
        Throwable cause = ex.getRootCause();
        if (cause instanceof SQLException) {
            SQLException sqlException = (SQLException) cause;
            if(sqlException.getSQLState().equals("23505")){
                message = "Existen elementos duplicados en un campo.";
            }
            response.meta(MetaDatosUtil.buildMetadatos("1", message, WARN, idTransaccion)
                    .totalRegistros(0));
        }
        return response;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseDto<CustomErrorResponse> illegalArgumentExceptionException(IllegalArgumentException ex, WebRequest wr) {
        ResponseDto<CustomErrorResponse> response = new ResponseDto<>();
        String idTransaccion = UUID.randomUUID().toString();
        String message = "";
        message = ex.getMessage();
        response.meta(MetaDatosUtil.buildMetadatos("1", message, WARN, idTransaccion)
                .totalRegistros(0));
        return response;
    }

    @ExceptionHandler(SQLException.class)
    public ResponseDto<CustomErrorResponse> handlerSQLException(SQLException ex, WebRequest wr) {
        ResponseDto<CustomErrorResponse> response = new ResponseDto<>();
        String idTransaccion = UUID.randomUUID().toString();
        String message = "";
        log.info("ESTADO: "+ex.getSQLState());
        if(ex.getSQLState().equals("23503")){
            message = "Error: Violación de integridad referencial. No se puede realizar la operación debido a una restricción de clave foránea.";
        }else {
            message = ex.getMessage();
        }
        response.meta(MetaDatosUtil.buildMetadatos("1", message, WARN, idTransaccion)
                .totalRegistros(0));
        return response;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseDto<CustomErrorResponse> usernameNotFoundException(UsernameNotFoundException ex, WebRequest wr) {
        ResponseDto<CustomErrorResponse> response = new ResponseDto<>();
        String idTransaccion = UUID.randomUUID().toString();
        String message = "";
        message = ex.getMessage();
        response.meta(MetaDatosUtil.buildMetadatos("1", message, WARN, idTransaccion)
                .totalRegistros(0));
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseDto<CustomErrorResponse> exceptionException(Exception ex, WebRequest wr) {
        ResponseDto<CustomErrorResponse> response = new ResponseDto<>();
        String idTransaccion = UUID.randomUUID().toString();
        String message = "";
        message = ex.getMessage();
        response.meta(MetaDatosUtil.buildMetadatos("1", message, WARN, idTransaccion)
                .totalRegistros(0));
        return response;
    }
}