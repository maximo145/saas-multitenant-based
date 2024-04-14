INSERT INTO tenant(slug, name)
    VALUES('tenant1', 'El primer tenant');
INSERT INTO tenant(slug, name)
    VALUES('tenant2', 'El segundo tenant');


/* tenant1 / lars@somehost.com / lars / USER */
INSERT INTO application_user(tenant_id, user_name, password)
VALUES((SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant1'),
       'lars@somehost.com',
       '$2a$10$lHDYiGTTEg5XJ0Ff/FcZVuxKb8ACj5bJ2krZeypHuk3KEDEW/CQTe'); /* 2 is USER */
INSERT INTO application_user(tenant_id, user_name, password)
VALUES(NULL,
       'admin@somehost.com',
       '$2a$10$l.Y/.kFZ35v2Qm1DN5SXo.wYLcQx6aDP0GOWGw7q6/FEITMHtAn0S'); /* 0 is ADMINISTRATOR */
/* tenant2 / jef@somehost.com / jef / USER */
INSERT INTO application_user(tenant_id, user_name, password)
VALUES((SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant2'),
       'jef@somehost.com',
       '$2a$10$0gKyJKEVtTpPITLSNkXfwOuDFTef2MgXAQ60cyKNkRi2C2hYkjdsO'); /* 2 is USER */



INSERT INTO post(text, tenant_id, author_id)
VALUES('El tenant 1 es el mejor!',
       (SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant1'),
       (SELECT usr.id FROM application_user AS usr WHERE usr.user_name = 'lars@somehost.com'
                                                     AND usr.tenant_id = (SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant1')));
INSERT INTO post(text, tenant_id, author_id)
VALUES('Me encanta el tenant 1',
       (SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant1'),
       (SELECT usr.id FROM application_user AS usr WHERE usr.user_name = 'lars@somehost.com'
                                                     AND usr.tenant_id = (SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant1')));

INSERT INTO post(text, tenant_id, author_id)
VALUES('Yo soy el tenant 2',
       (SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant2'),
       (SELECT usr.id FROM application_user AS usr WHERE usr.user_name = 'jef@somehost.com'
                                                     AND usr.tenant_id = (SELECT ten.id FROM tenant AS ten WHERE ten.slug = 'tenant2')));
