package com.music.record.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import com.music.record.enums.Gender;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "disc", schema = "music_record")
public class Disc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column
	private BigDecimal price;
}
