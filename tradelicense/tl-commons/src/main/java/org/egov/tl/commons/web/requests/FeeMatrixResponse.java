package org.egov.tl.commons.web.requests;

import java.util.List;

import org.egov.tl.commons.web.contract.FeeMatrix;
import org.egov.tl.commons.web.contract.ResponseInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class describe the set of fields contained in FeeMatrixResponse
 * 
 * @author Pavan Kumar Kamma
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeMatrixResponse {

	private ResponseInfo responseInfo;

	private List<FeeMatrix> feeMatrices;
}