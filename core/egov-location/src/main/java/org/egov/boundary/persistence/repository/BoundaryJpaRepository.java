/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

package org.egov.boundary.persistence.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.egov.boundary.persistence.entity.Boundary;
import org.egov.boundary.persistence.entity.BoundaryType;
import org.egov.boundary.persistence.entity.HierarchyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoundaryJpaRepository extends JpaRepository<Boundary, Long> {

	// @QueryHints({@QueryHint(name = HINT_CACHEABLE, value = "true")})
	Boundary findByName(String name);

	List<Boundary> findByNameContainingIgnoreCase(String name);

	List<Boundary> findBoundariesByBoundaryType_IdAndBoundaryType_TenantIdAndTenantId(
			@Param("boundaryTypeId") Long boundaryTypeId, @Param("boundaryTypeTenantId") String boundaryTypeTenantId,
			@Param("tenantId") String tenantId);

	Boundary findBoundarieByBoundaryTypeAndBoundaryNum(@Param("boundaryType") BoundaryType boundaryType,
			@Param("boundaryNum") Long boundaryNum);

	@Query("select b from Boundary b where b.isHistory=false AND b.boundaryType.id =:boundaryTypeId order by b.name")
	List<Boundary> findActiveBoundariesByBoundaryTypeId(@Param("boundaryTypeId") Long boundaryTypeId);

	@Query("select b from Boundary b where b.isHistory=false AND b.boundaryType.hierarchyType = :hierarchyType AND b.boundaryType.hierarchy = :hierarchyLevel AND ((b.toDate IS NULL AND b.fromDate <= :asOnDate) OR (b.toDate IS NOT NULL AND b.fromDate <= :asOnDate AND b.toDate >= :asOnDate)) order by b.name")
	List<Boundary> findActiveBoundariesByHierarchyTypeAndLevelAndAsOnDate(
			@Param("hierarchyType") HierarchyType hierarchyType, @Param("hierarchyLevel") Long hierarchyLevel,
			@Param("asOnDate") Date asOnDate);

	@Query("select b from Boundary b where b.isHistory=false AND b.parent is not null AND b.parent.id = :parentBoundaryId AND ((b.toDate IS NULL AND b.fromDate <= :asOnDate) OR (b.toDate IS NOT NULL AND b.fromDate <= :asOnDate AND b.toDate >= :asOnDate)) order by b.name")
	List<Boundary> findActiveChildBoundariesByBoundaryIdAndAsOnDate(@Param("parentBoundaryId") Long parentBoundaryId,
			@Param("asOnDate") Date asOnDate);

	@Query("from Boundary BND where BND.isHistory=false AND BND.materializedPath like (select B.materializedPath from Boundary B where B.id=:parentId)||'%'")
	List<Boundary> findActiveChildrenWithParent(@Param("parentId") Long parentId);

	@Query("from Boundary BND where BND.isHistory=false AND BND.materializedPath in :mpath ")
	List<Boundary> findActiveBoundariesForMpath(@Param("mpath") final Set<String> mpath);

	@Query("select b from Boundary b where b.parent is not null AND b.parent.id = :parentBoundaryId AND ((b.toDate IS NULL AND b.fromDate <= :asOnDate) OR (b.toDate IS NOT NULL AND b.fromDate <= :asOnDate AND b.toDate >= :asOnDate)) order by b.name")
	List<Boundary> findChildBoundariesByBoundaryIdAndAsOnDate(@Param("parentBoundaryId") Long parentBoundaryId,
			@Param("asOnDate") Date asOnDate);

	@Query("select b from Boundary b where b.isHistory=false AND b.boundaryNum = :boundaryNum AND b.boundaryType.name = :boundaryType AND upper(b.boundaryType.hierarchyType.code) = :hierarchyTypeCode AND ((b.toDate IS NULL AND b.fromDate <= :asOnDate) OR (b.toDate IS NOT NULL AND b.fromDate <= :asOnDate AND b.toDate >= :asOnDate))")
	Boundary findActiveBoundaryByBndryNumAndTypeAndHierarchyTypeCodeAndAsOnDate(@Param("boundaryNum") Long boundaryNum,
			@Param("boundaryType") String boundaryType, @Param("hierarchyTypeCode") String hierarchyTypeCode,
			@Param("asOnDate") Date asOnDate);

	@Query("select b from Boundary b where b.isHistory=false AND upper(b.boundaryType.name) = upper(:boundaryTypeName) AND upper(b.boundaryType.hierarchyType.name) = upper(:hierarchyTypeName) order by b.name")
	List<Boundary> findActiveBoundariesByBndryTypeNameAndHierarchyTypeName(
			@Param("boundaryTypeName") String boundaryTypeName, @Param("hierarchyTypeName") String hierarchyTypeName);

	@Query("select b from Boundary b where upper(b.boundaryType.name) = UPPER(:boundaryTypeName) AND upper(b.boundaryType.hierarchyType.name) = UPPER(:hierarchyTypeName) order by b.id")
	List<Boundary> findBoundariesByBndryTypeNameAndHierarchyTypeName(@Param("boundaryTypeName") String boundaryTypeName,
			@Param("hierarchyTypeName") String hierarchyTypeName);

	@Query("select b from Boundary b where upper(b.boundaryType.name) = UPPER(:boundaryTypeName) AND upper(b.boundaryType.hierarchyType.name) = UPPER(:hierarchyTypeName) order by b.id")
	Boundary findBoundaryByBndryTypeNameAndHierarchyTypeName(@Param("boundaryTypeName") String boundaryTypeName,
			@Param("hierarchyTypeName") String hierarchyTypeName);

	@Query("select b from Boundary b where b.isHistory=false and UPPER(b.name) like UPPER(:boundaryName) and b.boundaryType.id=:boundaryTypeId order by b.boundaryNum asc")
	List<Boundary> findByNameAndBoundaryTypeOrderByBoundaryNumAsc(@Param("boundaryName") String boundaryName,
			@Param("boundaryTypeId") Long boundaryTypeId);

	@Query("select b from Boundary b where upper(b.boundaryType.name) = UPPER(:boundaryTypeName) AND upper(b.boundaryType.hierarchyType.name) = UPPER(:hierarchyTypeName) and b.boundaryType.tenantId = b.tenantId and b.boundaryType.hierarchyType.tenantId = b.boundaryType.tenantId and b.boundaryType.tenantId = :tenantId order by b.id")
	List<Boundary> getBoundariesByBndryTypeNameAndHierarchyTypeNameAndTenantId(
			@Param("boundaryTypeName") String boundaryTypeName, @Param("hierarchyTypeName") String hierarchyTypeName,
			@Param("tenantId") String tenantId);

	@Query("select b from Boundary b where b.boundaryType.name=:boundaryType and b.boundaryType.hierarchyType.name=:hierarchyType and b.boundaryType.hierarchy=:hierarchyLevel")
	Boundary findByBoundaryTypeNameAndHierarchyTypeNameAndLevel(@Param("boundaryType") String boundaryType,
			@Param("hierarchyType") String hierarchyType, @Param("hierarchyLevel") Long hierarchyLevel);

	@Query("select b from Boundary b where b.isHistory=false AND upper(b.boundaryType.name) = upper(:boundaryTypeName) AND upper(b.boundaryType.hierarchyType.name) = upper(:hierarchyTypeName) AND UPPER(b.name) like UPPER(:name)||'%' order by b.id")
	List<Boundary> findActiveBoundariesByNameAndBndryTypeNameAndHierarchyTypeName(
			@Param("boundaryTypeName") String boundaryTypeName, @Param("hierarchyTypeName") String hierarchyTypeName,
			@Param("name") String name);

	@Query("from Boundary BND where BND.isHistory=false AND BND.parent.id=:parentId)")
	List<Boundary> findActiveImmediateChildrenWithOutParent(@Param("parentId") Long parentId);

	@Query("from Boundary BND where BND.parent is null")
	List<Boundary> findAllParents();

	List<Boundary> findByBoundaryTypeOrderByBoundaryNumAsc(BoundaryType boundaryType);

	Boundary findByTenantIdAndBoundaryNum(String tenantId, String code);

	Boundary findByTenantIdAndId(String tenantId, Long id);

	List<Boundary> findAllByTenantId(String tenantId);
	
	@Query("from Boundary b where b.tenantId=:tenantId and b.id in :boundaryIds ")
	List<Boundary> findAllBoundariesByIdsAndTenant(@Param("tenantId") String tenantId,@Param("boundaryIds") final List<Long> boundaryIds);
	
	@Query("from Boundary b where b.tenantId=:tenantId and b.boundaryNum in :boundaryNum and b.boundaryType.id in :boundaryTypeIds ")
	List<Boundary> findAllBoundariesByNumberAndType(@Param("tenantId") String tenantId,@Param("boundaryNum") List<Long> boundaryNum,@Param("boundaryTypeIds") List<Long> boundaryTypeIds);
	
	@Query("from Boundary b where b.tenantId=:tenantId and b.boundaryNum in :boundaryNum ")
	List<Boundary> getAllBoundaryByTenantIdAndNumber(@Param("tenantId") String tenantId,@Param("boundaryNum") List<Long> boundaryNum);
	
	@Query("from Boundary b where b.tenantId=:tenantId and b.boundaryType.id in :boundaryTypeIds ")
	List<Boundary> getAllBoundaryByTenantIdAndTypeIds(@Param("tenantId") String tenantId,@Param("boundaryTypeIds") List<Long> boundaryTypeIds);
	
	@Query("from Boundary b where b.tenantId=:tenantId and b.boundaryNum in :boundaryNum and b.id in :boundaryIds and b.boundaryType.id in :boundaryTypeIds ")
	List<Boundary> getAllBoundaryByTenantAndNumAndTypeAndTypeIds(@Param("tenantId") String tenantId,@Param("boundaryNum") List<Long> boundaryNum,@Param("boundaryIds") List<Long> boundaryIds,@Param("boundaryTypeIds") List<Long> boundaryTypeIds);
}