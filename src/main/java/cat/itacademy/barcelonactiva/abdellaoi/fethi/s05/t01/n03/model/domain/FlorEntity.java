package cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.model.domain;

import cat.itacademy.barcelonactiva.abdellaoi.fethi.s05.t01.n03.model.dto.FlorDTO;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FlorEntity {
	
	private Integer pk_FlorID;
	private String nomFlor;
	private String paisFlor;	

	public static FlorEntity toEntity(FlorDTO florDTO) {

		return FlorEntity.builder()
				.pk_FlorID(florDTO.getPk_FlorID())
				.nomFlor(florDTO.getNomFlor())
				.paisFlor(florDTO.getPaisFlor())
				.build();
	}

}
