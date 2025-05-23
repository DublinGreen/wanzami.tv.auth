package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class PayStackResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "reference", nullable = false)
	private String reference;
	
	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false, updatable = false)
	private Video video_id;
	
	@Column(name = "currency", nullable = false)
	private String currency;
	
	@Column(name = "message", nullable = true)
	private String message;
	
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;
	
	@Column(name = "transaction_status", nullable = false)
	private String transaction_status;

	@Column(name = "amount")
	private Integer amount;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;

}
