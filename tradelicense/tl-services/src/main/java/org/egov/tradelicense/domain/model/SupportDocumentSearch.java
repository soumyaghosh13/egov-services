package org.egov.tradelicense.domain.model;

import org.egov.tradelicense.domain.model.AuditDetails;
import org.egov.tradelicense.domain.model.SupportDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupportDocumentSearch {

	public static final String TABLE_NAME = "egtl_support_document";
	public static final String SEQUENCE_NAME = "seq_egtl_support_document";

	private Long id;

	private Long licenseId;

	private Long documentTypeId;
	
	private String documentTypeName;

	private String fileStoreId;

	private String comments;

	private String createdBy;

	private String lastModifiedBy;

	private Long createdTime;

	private Long lastModifiedTime;

	public SupportDocument toDomain() {

		SupportDocument supportDocument = new SupportDocument();

		AuditDetails auditDetails = new AuditDetails();

		supportDocument.setId(this.id);

		supportDocument.setLicenseId(this.licenseId);

		supportDocument.setDocumentTypeId(this.documentTypeId);

		supportDocument.setFileStoreId(Long.valueOf(this.fileStoreId));

		supportDocument.setComments(this.comments);

		auditDetails.setCreatedBy(this.createdBy);
		auditDetails.setCreatedTime(this.createdTime);
		auditDetails.setLastModifiedBy(this.lastModifiedBy);
		auditDetails.setLastModifiedTime(this.lastModifiedTime);

		supportDocument.setAuditDetails(auditDetails);

		return supportDocument;
	}

	public SupportDocumentSearch toEntity(SupportDocument supportDocument) {

		AuditDetails auditDetails = supportDocument.getAuditDetails();

		this.id = supportDocument.getId();

		this.licenseId = supportDocument.getLicenseId();

		this.documentTypeId = supportDocument.getDocumentTypeId();

		if(supportDocument.getFileStoreId() != null){
			this.fileStoreId = supportDocument.getFileStoreId().toString();
		}

		this.comments = supportDocument.getComments();

		this.createdBy = (auditDetails == null) ? null : auditDetails.getCreatedBy();

		this.lastModifiedBy = (auditDetails == null) ? null : auditDetails.getLastModifiedBy();

		this.createdTime = (auditDetails == null) ? null : auditDetails.getCreatedTime();

		this.lastModifiedTime = (auditDetails == null) ? null : auditDetails.getLastModifiedTime();

		return this;
	}
}
