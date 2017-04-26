package org.egov.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.egov.user.domain.exception.InvalidUpdatePasswordRequestException;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class UpdatePassword {
	private Long userId;
	private String existingPassword;
	private String newPassword;

	public void validate() {
		if (isUserIdAbsent() || isExistingPasswordAbsent() || isNewPasswordAbsent()) {
			throw new InvalidUpdatePasswordRequestException(this);
		}
	}

	public boolean isUserIdAbsent() {
		return userId == null;
	}

	public boolean isExistingPasswordAbsent() {
		return isEmpty(existingPassword);
	}

	public boolean isNewPasswordAbsent() {
		return isEmpty(newPassword);
	}
}
