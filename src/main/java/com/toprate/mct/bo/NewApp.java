package com.toprate.mct.bo;

import com.viettel.service.base.dto.BaseFWDTOImpl;
import com.viettel.service.base.model.BaseFWModelImpl;

import javax.persistence.*;

@Table(name="new_app")
@Entity
public class NewApp extends BaseFWModelImpl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="pathImage")
    private String pathImage;

    @Column(name="content")
    private String content;

    public NewApp(Long id, String title, String pathImage, String content) {

        this.id = id;
        this.title = title;
        this.pathImage = pathImage;
        this.content = content;
    }

    public NewApp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public BaseFWDTOImpl toDTO() {
        return null;
    }
}
