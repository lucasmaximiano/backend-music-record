package com.music.record.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.music.record.enums.Day;
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
@Table(name = "cashback", schema = "music_record")
public class Cashback implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private Day day;

	@Column
	private BigDecimal percentCashBack;
}
