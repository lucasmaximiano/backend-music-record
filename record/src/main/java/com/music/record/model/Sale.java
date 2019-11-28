package com.music.record.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@EqualsAndHashCode
@Table(name = "sale", schema = "music_record")
public class Sale implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String customerName;

	@Column
	private String customerEmail;

	@Column
	private BigDecimal totalPrice;

	@Column
	private BigDecimal cashBackTotalValue;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sale", fetch = FetchType.EAGER)
    @JsonManagedReference
	private List<SaleItem> itens;
	
	@Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date  createdDate;

}
