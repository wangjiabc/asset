package com.rmi.server.entity;

import java.io.File;
import java.io.Serializable;

public class ImageUploadResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean success;
    private String  message;
    private String  imageName;
    private Long    imageLength;
    private File    imageFile;
    
    public ImageUploadResult() {
    	
    }

    public ImageUploadResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getImageLength() {
        return imageLength;
    }

    public void setImageLength(Long imageLength) {
        this.imageLength = imageLength;
    }

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
}
