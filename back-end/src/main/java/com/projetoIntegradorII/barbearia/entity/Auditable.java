package com.projetoIntegradorII.barbearia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowGetters = true)
public abstract class Auditable implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String IS_EXCLUIDO = "isExcluido";
    public static final String USUARIO_INC = "createdBy";
    public static final String USUARIO_ALT = "lastModifiedBy";
    public static final String DATA_HORA_USUARIO_INC = "createdDate";
    public static final String DATA_HORA_USUARIO_ALT = "lastModifiedDate";


    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "last_modified_date", nullable = false)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", updatable = false)
    protected String lastModifiedBy;

    @NotNull
    @Column(name = "active", nullable = false)
//    @Convert(converter = BooleanNotNullConverter.class)
    protected Boolean active = true;

}
