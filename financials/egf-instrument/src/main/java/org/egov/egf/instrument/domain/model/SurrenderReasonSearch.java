package org.egov.egf.instrument.domain.model ;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SurrenderReasonSearch extends SurrenderReason{ private Integer pageSize; 
private Integer offset; 
} 