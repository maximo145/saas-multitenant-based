package com.decode.saassharedschema.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.decode.saassharedschema.util.ResponseDto;
import com.decode.saassharedschema.security.domain.entities.User;
import com.decode.saassharedschema.security.domain.persistence.IUserRepository;
import com.decode.saassharedschema.security.domain.service.IUserService;
import com.decode.saassharedschema.security.mapping.UserMapping;
import com.decode.saassharedschema.security.service.dto.UserDto;
import com.decode.saassharedschema.security.service.resources.UserListResponse;
import com.decode.saassharedschema.security.service.resources.UserResponse;
import com.decode.saassharedschema.util.MetaDatosUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.decode.saassharedschema.util.MensajeServicio.TipoEnum.INFO;
import static com.decode.saassharedschema.util.MensajeServicio.TipoEnum.WARN;

@Service
public class UserServiceImp implements IUserService {

    private static final String MESSAGE_INQUIRY_USER_SUCCESS = "La consulta de usuarios fue exitoso";
    private static final String MESSAGE_INQUIRY_USER_WARN = "No se encontró ningún usuario registrado";

    private static final String MESSAGE_REGISTER_USER_SUCCESS = "El registro del usuario fue exitoso";
    private static final String MESSAGE_REGISTER_USER_WARN = "Ocurrió un error al registrar el usuario";

    private static final String MESSAGE_UPDATE_USER_SUCCESS = "La actualización de datos del usuario fue exitoso";
    private static final String MESSAGE_UPDATE_USER_WARN = "Ocurrió un error al actualizar los datos del usuario";

    private static final String MESSAGE_RETRIEVE_USER_SUCCESS = "La consulta del usuario fue exitoso";
    private static final String MESSAGE_RETRIEVE_USER_WARN = "No se encontró los datos del usuario";

    private static final String MESSAGE_DELETE_USER_SUCCESS = "Se eliminó correctamente el usuario";

    private static final String MESSAGE_DELETE_USER_WARN = "Ocurrió un error al eliminar el usuario";

    private static final String CODE_SUCCESS = "0";

    private static final String CODE_WARN = "1";

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserMapping userMapping;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public ResponseDto<UserListResponse> listUsers() {
        ResponseDto<UserListResponse> response = new ResponseDto<>();
        try {
            String idTransaccion = UUID.randomUUID().toString();

            List<User> userList = userRepository.findAll();

            if (userList.isEmpty()) {
                response.meta(MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_INQUIRY_USER_WARN, WARN, idTransaccion)
                        .totalRegistros(0));
                return response;
            }

            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_INQUIRY_USER_SUCCESS, INFO, idTransaccion)
                    .totalRegistros(userList.size()));
            response.setDatos(new UserListResponse().user(userMapping.modelList(userList)));

        } catch (Exception ex) {
            //log.error(MESSAGE_INQUIRY_USER_WARN + ": " + ex);
            throw ex;
        }

        return response;
    }

    @Override
    public ResponseDto<UserResponse> retrieveUser(Long id) {
        ResponseDto<UserResponse> response = new ResponseDto<>();
        try {
            String idTransaccion = UUID.randomUUID().toString();

            Optional<User> userList = userRepository.findById(id);

            if (userList.isEmpty()) {
                response.meta(MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_RETRIEVE_USER_WARN, WARN, idTransaccion)
                        .totalRegistros(0));
                return response;
            }

            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_RETRIEVE_USER_SUCCESS, INFO, idTransaccion)
                    .totalRegistros(1));
            response.setDatos(new UserResponse().user(userMapping.modelDto(userList.get())));

        } catch (Exception ex) {
            //log.error(MESSAGE_RETRIEVE_USER_WARN + ": " + ex);
            throw ex;
        }

        return response;
    }

    @Override
    public ResponseDto<UserResponse> registerUser(UserDto userDto) {
        ResponseDto<UserResponse> response = new ResponseDto<>();

        try {
            String idTransaccion = UUID.randomUUID().toString();
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User userResponse = userRepository.save(userMapping.model(userDto));
            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_REGISTER_USER_SUCCESS, INFO, idTransaccion));
            response.setDatos(new UserResponse().user(userMapping.modelDto(userResponse)));
        } catch (Exception ex) {
            //log.error(MESSAGE_REGISTER_USER_WARN + ": " + ex);
            throw ex;
        }

        return response;
    }

    @Override
    public ResponseDto<UserResponse> updateUser(UserDto userDto) {
        ResponseDto<UserResponse> response = new ResponseDto<>();

        try {
            String idTransaccion = UUID.randomUUID().toString();

            Optional<User> userList = userRepository.findById(userDto.getId());

            if (userList.isEmpty()) {
                response.meta(MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_RETRIEVE_USER_WARN, WARN, idTransaccion)
                        .totalRegistros(0));
                return response;
            }

            User userResponse = userRepository.save(userMapping.model(userDto));
            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_UPDATE_USER_SUCCESS, INFO, idTransaccion));
            response.setDatos(new UserResponse().user(userMapping.modelDto(userResponse)));

        } catch (Exception ex) {
            //log.error(MESSAGE_UPDATE_USER_WARN + ": " + ex);
            throw ex;
        }

        return response;
    }

    @Override
    public ResponseDto deleteUser(Long id) {
        ResponseDto response = new ResponseDto<>();
        try {
            String idTransaccion = UUID.randomUUID().toString();
            userRepository.deleteById(id);
            response.meta(
                    MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_DELETE_USER_SUCCESS, INFO, idTransaccion)
                            .totalRegistros(1));

        } catch (Exception ex) {
            //log.error(MESSAGE_DELETE_USER_WARN + ": " + ex);
            throw ex;
        }

        return response;
    }
}
