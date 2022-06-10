package com.example.demoProject.exception;

public class GenericNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String internalCode;
	private String entityId;

	public GenericNotFoundException(String entityId, String internalCode) {
		super(entityId + " not found");
		this.setInternalCode(internalCode);
		this.setEntityId(entityId);
	}

	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
}