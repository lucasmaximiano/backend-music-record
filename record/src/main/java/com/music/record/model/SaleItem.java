package com.music.record.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "sale_item", schema = "music_record")
public class SaleItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private Integer discId;

	@Column
	private String name;

	@Column
	private Integer quantity;

	@Column
	private BigDecimal price;

	@Column
	private BigDecimal totalPrice;

	@Column
	private BigDecimal cashBackValue;

	@JsonBackReference
	@JoinColumn(name = "sale_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Sale sale;
}
