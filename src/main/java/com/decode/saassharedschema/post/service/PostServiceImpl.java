package com.decode.saassharedschema.post.service;

import com.decode.saassharedschema.post.domain.entity.Post;
import com.decode.saassharedschema.post.domain.persistence.IPostRepository;
import com.decode.saassharedschema.security.domain.entities.Tenant;
import com.decode.saassharedschema.security.domain.persistence.TenantRepository;
import com.decode.saassharedschema.post.domain.service.IPostService;
import com.decode.saassharedschema.post.mapping.PostMapping;
import com.decode.saassharedschema.post.service.dto.NewPostDto;
import com.decode.saassharedschema.post.service.response.PostListResponse;
import com.decode.saassharedschema.post.service.response.PostResponse;
import com.decode.saassharedschema.security.mapping.TenantMapping;
import com.decode.saassharedschema.util.MetaDatosUtil;
import com.decode.saassharedschema.util.ResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static com.decode.saassharedschema.util.MensajeServicio.TipoEnum.INFO;
import static com.decode.saassharedschema.util.MensajeServicio.TipoEnum.WARN;

import java.util.*;

@Service
@Slf4j
public class PostServiceImpl implements IPostService {
    private static final String MESSAGE_INQUIRY_ROLE_SUCCESS = "La consulta de cliente fue exitoso";
    private static final String MESSAGE_INQUIRY_ROLE_WARN = "No se encontró ningún cliente registrado";
    private static final String MESSAGE_RETRIEVE_ROLE_SUCCESS = "La consulta de cliente fue exitoso";
    private static final String MESSAGE_RETRIEVE_ROLE_WARN = "No se encontró los datos del cliente";
    private static final String MESSAGE_REGISTER_ROLE_SUCCESS = "El registro de cliente fue exitoso";
    private static final String MESSAGE_REGISTER_ROLE_WARN = "Ocurrió un error al registrar el cliente";
    private static final String MESSAGE_UPDATE_ROLE_SUCCESS = "La actualización del cliente fue exitoso";
    private static final String MESSAGE_UPDATE_ROLE_WARN = "Ocurrió un error al actualizar el cliente";
    private static final String MESSAGE_DELETE_ROLE_SUCCESS = "El cliente se eliminó correctamente";
    private static final String MESSAGE_DELETE_ROLE_WARN = "Ocurrió un error al eliminar el cliente";

    private static final String CODE_SUCCESS = "0";
    private static final String CODE_WARN = "1";

    private final TenantRepository tenantRepository;
    private IPostRepository postRepository;
    private PostMapping postMapping;
    private TenantMapping tenantMapping;

    public PostServiceImpl(IPostRepository postRepository, TenantRepository tenantRepository, PostMapping postMapping, TenantMapping tenantMapping){
        this.postRepository = postRepository;
        this.tenantRepository = tenantRepository;
        this.postMapping = postMapping;
        this.tenantMapping = tenantMapping;
    }
    @Override
    public ResponseDto<PostListResponse> listPosts(Long tenantId) {
        ResponseDto<PostListResponse> response = new ResponseDto<>();
        try {
            String idTransaccion = UUID.randomUUID().toString();
            List<Post> postList = (tenantId != null) ? postRepository.findByTenantIdWithAuthor(tenantId) : postRepository.findByAdmin();

            if (postList.isEmpty()) {
                response.meta(
                        MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_INQUIRY_ROLE_WARN, WARN, idTransaccion)
                                .totalRegistros(0));
                return response;
            }

            response.meta(
                    MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_INQUIRY_ROLE_SUCCESS, INFO, idTransaccion)
                            .totalRegistros(postList.size()));
            response.setDatos(new PostListResponse().postListResponse(postMapping.modelList(postList)));

        } catch (Exception ex) {
            log.error(MESSAGE_INQUIRY_ROLE_WARN + " : " + ex);
            throw ex;
        }
        return response;
    }
    @Override
    public ResponseDto<PostResponse> retrievePost(long tenantId, Long id) {
        ResponseDto<PostResponse> response = new ResponseDto<>();
        try{
            String idTransaccion = UUID.randomUUID().toString();
            Optional<Post> post = postRepository.findByIdAndTenantId(id, tenantId);
            if (post.isEmpty()) {
                response.meta(MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_RETRIEVE_ROLE_WARN, WARN, idTransaccion)
                        .totalRegistros(0));
                return response;
            }

            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_RETRIEVE_ROLE_SUCCESS, INFO, idTransaccion)
                    .totalRegistros(1));
            response.setDatos(new PostResponse().post(postMapping.modelDto(post.get())));

        }catch (Exception ex){
            log.error(MESSAGE_RETRIEVE_ROLE_WARN + ": " + ex);
            throw ex;
        }
        return response;
    }

    @Override
    public ResponseDto<PostResponse> registerPost(long id, NewPostDto newPostDto) {
        ResponseDto<PostResponse> response = new ResponseDto<>();
        try{
            String idTransaccion = UUID.randomUUID().toString();
            Optional<Tenant> tenant = tenantRepository.findById(id);
            newPostDto.setTenant(tenantMapping.modelDto(tenant.get()));
            Post post = postRepository.save(postMapping.model(newPostDto));

            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_REGISTER_ROLE_SUCCESS, INFO, idTransaccion)
                    .totalRegistros(1));
            response.setDatos(new PostResponse().post(postMapping.modelDto(post)));

        }catch (Exception ex){
            log.error(MESSAGE_REGISTER_ROLE_WARN + ": " + ex);
            throw ex;
        }
        return response;
    }

    @Override
    public ResponseDto<PostResponse> updatePost(long tenantId, NewPostDto newPostDto) {
        ResponseDto<PostResponse> response = new ResponseDto<>();
        try{
            String idTransaccion = UUID.randomUUID().toString();

            Optional<Tenant> tenant = tenantRepository.findById(tenantId);
            newPostDto.setTenant(tenantMapping.modelDto(tenant.get()));
            Optional<Post> post = postRepository.findByIdAndTenantId(newPostDto.getId(), tenantId);
            if (post.isEmpty()) {
                response.meta(MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_UPDATE_ROLE_WARN, WARN, idTransaccion)
                        .totalRegistros(0));
                return response;
            }

            Post postSave = postRepository.save(postMapping.model(newPostDto));
            response.meta(MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_UPDATE_ROLE_SUCCESS, INFO, idTransaccion)
                    .totalRegistros(1));
            response.setDatos(new PostResponse().post(postMapping.modelDto(postSave)));

        }catch (Exception ex){
            log.error(MESSAGE_UPDATE_ROLE_WARN + ": " + ex);
            throw ex;
        }
        return response;
    }

    @Override
    public ResponseDto deletePost(long tenantId, Long id) {
        ResponseDto response = new ResponseDto<>();
        try {
            String idTransaccion = UUID.randomUUID().toString();

            Optional<Post> post = postRepository.findByIdAndTenantId(id, tenantId);
            if (post.isEmpty()) {
                response.meta(MetaDatosUtil.buildMetadatos(CODE_WARN, MESSAGE_DELETE_ROLE_WARN, WARN, idTransaccion)
                        .totalRegistros(0));
                return response;
            }

            postRepository.deleteById(id);

            response.meta(
                    MetaDatosUtil.buildMetadatos(CODE_SUCCESS, MESSAGE_DELETE_ROLE_SUCCESS, INFO, idTransaccion)
                            .totalRegistros(1));

        } catch (Exception ex) {
            log.error(MESSAGE_DELETE_ROLE_WARN + ": " + ex);
            throw ex;
        }

        return response;
    }


}
