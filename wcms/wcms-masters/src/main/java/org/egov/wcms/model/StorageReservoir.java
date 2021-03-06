/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.wcms.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@ToString
@Builder
public class StorageReservoir {

    public static final String SEQ_STORAGE_RESERVOIR = "SEQ_EGWTR_STORAGE_RESERVOIR";

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String code;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private String reservoirType;

    @NotNull
    private String locationNum;

    private String locationName;

    @NotNull
    private String wardNum;

    private String wardName;

    @NotNull
    private String zoneNum;

    private String zoneName;

    @NotNull
    @Min(1)
    @Max(8)
    private double capacity;

    @NotNull
    @Size(min = 1, max = 124)
    private Long noOfSubLines;

    @NotNull
    @Size(min = 1, max = 124)
    private Long noOfMainDistributionLines;

    @NotNull
    @Size(min = 1, max = 124)
    private Long noOfConnection;

    @JsonIgnore
    private AuditDetails auditDeatils;

    @Size(max = 250)
    @NotNull
    private String tenantId;

}
